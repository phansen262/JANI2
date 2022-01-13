package com.sticknology.jani2.app_objects.trainingplan.exercises;

import android.content.Context;

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
        USER_EXERCISES("user_exercises"),
        EXERCISE("exercise"),
        NAME("name"),
        DESCRIPTION("description"),
        TYPE("type"),
        ATTRIBUTE_LIST("attributes_list"),
        ATTRIBUTE("attribute"),
        ATTRIBUTE_KEY("attribute_key"),
        ATTRIBUTE_PAYLOAD("attribute_payload");

        public final String tag;
        Tags(String tag){this.tag = tag;}
    }

    public static void writeUserExercises(Context context, List<Exercise> exerciseList){

        try {

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Root Element
            Element default_exercises = doc.createElement(Tags.USER_EXERCISES.tag);
            doc.appendChild(default_exercises);

            for(int i = 0; i < exerciseList.size(); i++) {

                //Root Element
                Element exercise = doc.createElement(Tags.EXERCISE.tag);
                default_exercises.appendChild(exercise);

                //Name attr
                Attr name = doc.createAttribute(Tags.NAME.tag);
                name.setValue(exerciseList.get(i).getName());
                exercise.setAttributeNode(name);
                //Description attr
                Attr description = doc.createAttribute(Tags.DESCRIPTION.tag);
                description.setValue(exerciseList.get(i).getDescription());
                exercise.setAttributeNode(description);
                //Type attr
                Attr type = doc.createAttribute(Tags.TYPE.tag);
                type.setValue(exerciseList.get(i).getType());
                exercise.setAttributeNode(type);

                //Attributes element
                Element attributes = doc.createElement(Tags.ATTRIBUTE_LIST.tag);
                exercise.appendChild(attributes);

                //Adds all items in attributes
                for(EAttributeKeys attributeKey : EAttributeKeys.values()){

                    if(exerciseList.get(i).getAttributeItem(attributeKey.getKey()) != null){

                        Element attribute = doc.createElement(Tags.ATTRIBUTE.tag);

                        StringBuilder build = new StringBuilder();
                        for(int u  = 0; u < exerciseList.get(i).getAttributeItem(attributeKey.getKey()).size(); u++){
                            build.append(exerciseList.get(i).getAttributeItem(attributeKey.getKey()).get(u));
                            System.out.println("inside for loop for string build attribute e dom:  " + attributeKey.getKey());
                            if(u < (exerciseList.get(i).getAttributeItem(attributeKey.getKey()).size() - 1)){
                                System.out.println("got inside the iff");
                                build.append("@!@");
                            }
                        }

                        attribute.setAttribute(attributeKey.getKey(), build.toString());

                        attributes.appendChild(attribute);
                    }
                }
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
            NodeList nodeList = rootNode.getElementsByTagName(Tags.EXERCISE.tag);

            for(int i = 0; i < nodeList.getLength(); i++){

                Exercise exerciseObject = new Exercise();

                NamedNodeMap eAttrMap = nodeList.item(i).getAttributes();

                exerciseObject.setName(eAttrMap.getNamedItem(Tags.NAME.tag).getNodeValue());
                exerciseObject.setDescription(eAttrMap.getNamedItem(Tags.DESCRIPTION.tag).getNodeValue());
                exerciseObject.setType(eAttrMap.getNamedItem(Tags.TYPE.tag).getNodeValue());

                Element exerciseElement = (Element) nodeList.item(i);
                Element attributeListElement = (Element) exerciseElement.getElementsByTagName(Tags.ATTRIBUTE_LIST.tag).item(0);

                for(int u = 0; u < attributeListElement.getElementsByTagName(Tags.ATTRIBUTE.tag).getLength(); u++){

                    Element attribute = (Element) attributeListElement.getElementsByTagName(Tags.ATTRIBUTE.tag).item(u);

                    for(EAttributeKeys attributeKey : EAttributeKeys.values()){
                        if(attribute.hasAttribute(attributeKey.getKey())){

                            List<String> payload = Arrays.asList(attribute.getAttribute(attributeKey.getKey()).split("@!@"));
                            exerciseObject.addAttribute(attributeKey.getKey(), payload);
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
