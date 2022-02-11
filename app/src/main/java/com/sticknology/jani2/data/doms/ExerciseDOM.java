package com.sticknology.jani2.data.doms;

import android.content.Context;

import androidx.annotation.NonNull;

import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_operations.ListMethods;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ExerciseDOM {

    public enum ExerciseFilePath {
        USER("user_exercises.xml"),
        DEFAULT("default_info/default_exercises.xml");

        public final String path;
        ExerciseFilePath(String path){
            this.path = path;
        }
    }

    public enum Tags {
        USER_EXERCISES,
        EXERCISE,
        NAME,
        ATTRIBUTES_LIST;

        @NonNull
        @Override
        public String toString() {
            return super.toString().toLowerCase(Locale.ROOT);
        }
    }

    public static void writeUserExercises(Context context, List<Exercise> exerciseList){

        try {

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Root Element
            Element default_exercises = doc.createElement(Tags.USER_EXERCISES.toString());
            doc.appendChild(default_exercises);

            for(int i = 0; i < exerciseList.size(); i++) {

                //Root Element
                Element exercise = doc.createElement(Tags.EXERCISE.toString());
                default_exercises.appendChild(exercise);

                //Name attr
                Attr name = doc.createAttribute(Tags.NAME.toString());
                name.setValue(exerciseList.get(i).getName());
                exercise.setAttributeNode(name);

                //Attribute list element
                Element attributeList = doc.createElement(Tags.ATTRIBUTES_LIST.toString());

                //Adds all items in attributes as attr
                for(EAttributeKeys attributeKey : EAttributeKeys.values()){

                    if(exerciseList.get(i).getAttributeItem(attributeKey) != null){

                        Attr attribute = doc.createAttribute(attributeKey.toString());
                        String payload = ListMethods.joinList(exerciseList.get(i).getAttributeItem(attributeKey), "@!@");
                        attribute.setValue(payload);
                        attributeList.setAttributeNode(attribute);
                    }
                }

                exercise.appendChild(attributeList);
            }

            //Write into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(context.openFileOutput(ExerciseFilePath.USER.path, Context.MODE_PRIVATE));
            transformer.transform(source, result);

            //To print output file to console
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(source, consoleResult);

        } catch (ParserConfigurationException | FileNotFoundException | TransformerException e){

            e.printStackTrace();
        }
    }

    public static ArrayList<Exercise> getExerciseList(Context context, String path){

        ArrayList<Exercise> exerciseList = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Object docObj = new Object();

            if(path.equals(ExerciseFilePath.USER.path)){
                File inputFile = context.getFileStreamPath(ExerciseFilePath.USER.path);
                docObj = dBuilder.parse(inputFile);
            } else if (path.equals(ExerciseFilePath.DEFAULT.path)){
                InputStream input = context.getAssets().open(ExerciseFilePath.DEFAULT.path);
                docObj = dBuilder.parse(input);
            }
            assert docObj instanceof Document;
            Document doc = (Document) docObj;

            doc.getDocumentElement().normalize();

            Element rootNode = doc.getDocumentElement();
            NodeList nodeList = rootNode.getElementsByTagName(Tags.EXERCISE.toString());

            for(int i = 0; i < nodeList.getLength(); i++){

                NamedNodeMap eAttrMap = nodeList.item(i).getAttributes();

                Exercise exerciseObject = new Exercise(eAttrMap.getNamedItem(Tags.NAME.toString()).getNodeValue(), new DataMap());

                Element exerciseElement = (Element) nodeList.item(i);
                Element attributeListElement = (Element) exerciseElement.getElementsByTagName(Tags.ATTRIBUTES_LIST.toString()).item(0);

                for(int u = 0; u < attributeListElement.getAttributes().getLength(); u++){

                    for(EAttributeKeys attributeKey : EAttributeKeys.values()){

                        if(attributeListElement.hasAttribute(attributeKey.toString())){

                            List<String> payload = Arrays.asList(attributeListElement.getAttribute(attributeKey.toString()).split("@!@"));
                            exerciseObject.putAttribute(attributeKey, payload);

                        }
                    }
                }

                exerciseList.add(exerciseObject);
            }

        } catch (ParserConfigurationException | SAXException | IOException e){

            e.printStackTrace();
        }

        //Only highlighted bc alternate in try
        if(exerciseList.size() != 0) {
            return exerciseList;
        } else {
            System.out.println("ExerciseDOM.java getExerciseList ------------ Returning null list");
            return null;
        }
    }
}
