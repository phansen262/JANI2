package com.sticknology.jani2.app_objects.trainingplan.exercises;

import android.content.Context;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ExerciseDOM {

    public static void makeExerciseXML(Context context){

        try {

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Root Element
            Element exercise = doc.createElement("exercise");
            doc.appendChild(exercise);

            //Name attr
            Attr name = doc.createAttribute("name");
            name.setValue("Bench");
            exercise.setAttributeNode(name);
            //Description attr
            Attr description = doc.createAttribute("edescription");
            description.setValue("This is a description for bench");
            exercise.setAttributeNode(description);
            //Type attr
            Attr type = doc.createAttribute("type");
            type.setValue("Weights");
            exercise.setAttributeNode(type);
            //mGroup attr
            Attr mGroups = doc.createAttribute("mGroups");
            mGroups.setValue("Chest");
            exercise.setAttributeNode(mGroups);

            //Write into xml file
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(context.openFileOutput("exercise_xml.txt", Context.MODE_PRIVATE));
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (ParserConfigurationException | FileNotFoundException | TransformerException e){

            System.out.println("DIDN'T WORK IDIOT");
        }
    }

    public static void printFile(Context context){

        try {

            File inputFile = context.getFileStreamPath("exercise_xml.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("exercise");
            System.out.println("----------------------------");



        } catch (ParserConfigurationException | SAXException | IOException e){

            e.printStackTrace();
        }
    }
}
