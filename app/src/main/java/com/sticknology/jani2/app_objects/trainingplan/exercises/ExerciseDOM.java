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
import java.util.ArrayList;
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

    public static void makeExerciseXML(Context context, List<Exercise> exerciseList){

        try {

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Root Element
            Element default_exercises = doc.createElement("user_exercises");
            doc.appendChild(default_exercises);

            for(int i = 0; i < exerciseList.size(); i++) {

                //Root Element
                Element exercise = doc.createElement("exercise");
                default_exercises.appendChild(exercise);

                //Name attr
                Attr name = doc.createAttribute("name");
                name.setValue(exerciseList.get(i).getName());
                exercise.setAttributeNode(name);
                //Description attr
                Attr description = doc.createAttribute("edescription");
                description.setValue(exerciseList.get(i).getDescription());
                exercise.setAttributeNode(description);
                //Type attr
                Attr type = doc.createAttribute("type");
                type.setValue(exerciseList.get(i).getType());
                exercise.setAttributeNode(type);

                //mGroup attr
                if(exerciseList.get(i).getAttributeItem("MGROUP") != null) {
                    Attr mGroups = doc.createAttribute("mGroups");
                    mGroups.setValue(exerciseList.get(i).getAttributeItem("MGROUP").get(0));
                    exercise.setAttributeNode(mGroups);
                }
            }

            //Write into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(context.openFileOutput("user_exercises.xml", Context.MODE_PRIVATE));
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (ParserConfigurationException | FileNotFoundException | TransformerException e){

            e.printStackTrace();
        }
    }

    public static ArrayList<Exercise> getUserExercises(Context context){

        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();

        try {

            File inputFile = context.getFileStreamPath("user_exercises.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element rootNode = doc.getDocumentElement();
            NodeList nodeList = rootNode.getElementsByTagName("exercise");

            for(int i = 0; i < nodeList.getLength(); i++){

                NamedNodeMap attributeMap = nodeList.item(i).getAttributes();
                Exercise exerciseObject = new Exercise();
                exerciseObject.setName(attributeMap.getNamedItem("name").getNodeValue());
                exerciseObject.setDescription(attributeMap.getNamedItem("edescription").getNodeValue());
                exerciseObject.setType(attributeMap.getNamedItem("type").getNodeValue());
                if(attributeMap.getNamedItem("mGroups") != null){
                    List<String> eAttributes = new ArrayList<String>();
                    eAttributes.add(attributeMap.getNamedItem("mGroups").getNodeValue());
                    exerciseObject.addAttribute("MGROUP", eAttributes);
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
            return null;
        }
    }
}
