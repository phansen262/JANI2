package com.sticknology.jani2.app_objects.trainingplan.sessions;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.base_operations.ListMethods;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
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

public class SessionDOM {

    public enum Tags{
        SESSION("session"),
        SESSION_NAME("session_name"),
        SESSION_DESCRIPTION("session_description"),
        SESSION_TYPE("session_type"),
        ATTRIBUTES("attributes"),
        EDATA_LIST("edata_list"),
        EDATA_ITEM("edata_item");

        public final String tag;
        Tags(String tag){this.tag = tag;}
    }

    public static void writeSession(Context context,Session session){

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Root Element and attributes
            Element sessionElement = doc.createElement(Tags.SESSION.tag);

            Attr name = doc.createAttribute(Tags.SESSION_NAME.tag);
            name.setValue(session.getName());
            sessionElement.setAttributeNode(name);

            Attr description = doc.createAttribute(Tags.SESSION_DESCRIPTION.tag);
            description.setValue(session.getDescription());
            sessionElement.setAttributeNode(description);

            Attr type = doc.createAttribute(Tags.SESSION_TYPE.tag);
            type.setValue(session.getType());
            sessionElement.setAttributeNode(type);

            doc.appendChild(sessionElement);

            //Session Attributes
            Element sessionAttributes = doc.createElement(Tags.ATTRIBUTES.tag);
            for(String key : session.getAttributes().keySet()){
                Attr attribute = doc.createAttribute(key);
                attribute.setValue(ListMethods.joinList(session.getAttributes().get(key), "@!@"));
                sessionAttributes.setAttributeNode(attribute);
            }
            sessionElement.appendChild(sessionAttributes);

            //EdataList Element Add
            Element edataList = doc.createElement(Tags.EDATA_LIST.tag);
            //Iterate through exercises/edata in session
            for(EData edata : session.getEDataList()){
                //Initialize element and add name
                Element eDataElement = doc.createElement(Tags.EDATA_ITEM.tag);
                Attr eName = doc.createAttribute("name");
                eName.setValue(edata.getKey().getName());
                eDataElement.setAttributeNode(eName);
                //Iterate through eData and add corresponding attributes
                for(String key : edata.getData().keySet()){
                    Attr data = doc.createAttribute(key);
                    data.setValue(ListMethods.joinList(edata.getData().get(key), "@!@"));
                    eDataElement.setAttributeNode(data);
                }
            }

            //Add edataList and tie off document
            sessionElement.appendChild(edataList);

            //Write to xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(context.openFileOutput("", Context.MODE_PRIVATE));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    public static Session readSession(Context context, String filepath){

        Session session = new Session();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(context.getFileStreamPath(filepath));
            doc.getDocumentElement().normalize();

            //Root node data (Session tag attributes)
            Element sessionNode = doc.getDocumentElement();
            session.setName(sessionNode.getAttributes().getNamedItem(Tags.SESSION_NAME.tag).getNodeValue());
            session.setDescription(sessionNode.getAttributes().getNamedItem(Tags.SESSION_DESCRIPTION.tag).getNodeValue());
            session.setType(sessionNode.getAttributes().getNamedItem(Tags.SESSION_TYPE.tag).getNodeValue());

            //Get Root node list from session
            NodeList rootNodeList = sessionNode.getElementsByTagName(Tags.SESSION.tag);

            //Get session attributes
            Element sessionAttributes = (Element) rootNodeList.item(0);
            for(SAttributeKeys key : SAttributeKeys.values()){
                if(sessionAttributes.hasAttribute(key.getKey())){
                    List<String> payload = Arrays.asList(
                            sessionAttributes.getAttributes().getNamedItem(key.getKey()).getNodeValue().split("@!@"));
                    session.addAttribute(key.getKey(), payload);
                }
            }

            //Add edata
            Element sessionEData = (Element) rootNodeList.item(1);
            for(int i = 0; i < sessionEData.getElementsByTagName(Tags.EDATA_ITEM.tag).getLength(); i++){

                Element eData = (Element) sessionEData.getElementsByTagName(Tags.EDATA_ITEM.tag).item(i);
                for(EData.EDataKeys key : EData.EDataKeys.values()){
                    if(sessionAttributes.hasAttribute(key.getKey())){
                        //TODO:  Finish adding session attributes
                    }
                }
            }


        } catch (ParserConfigurationException | SAXException | IOException e){

            e.printStackTrace();
        }

        //Only highlighted bc alternate in try
        if(session.getName() != null) {
            return session;
        } else {
            System.out.println("SessionDOM.java getSession ------------ Returning null Session");
            return null;
        }
    }
}
