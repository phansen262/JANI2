package com.sticknology.jani2.data.doms;

import android.content.Context;

import androidx.annotation.NonNull;

import com.sticknology.jani2.app_objects.trainingplan.edata.EData;
import com.sticknology.jani2.app_objects.trainingplan.edata.EDataKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.app_objects.trainingplan.sessions.SAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_operations.ListMethods;
import com.sticknology.jani2.data.servers.ExerciseServer;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class SessionDOM {

    public enum Tags{
        SESSION,
        SESSION_NAME,
        ATTRIBUTES,
        EDATA_LIST,
        EDATA_ITEM,
        EDATA_VALUES,
        EXERCISE_NAME,
        EDATA_PAYLOAD;

        @NonNull
        @Override
        public String toString() {
            return super.toString().toLowerCase(Locale.ROOT);
        }
    }

    public static void writeSession(Context context, Session session){

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Root Element and attributes
            Element sessionElement = doc.createElement(Tags.SESSION.toString());

            Attr name = doc.createAttribute(Tags.SESSION_NAME.toString());
            name.setValue(session.getName());
            sessionElement.setAttributeNode(name);

            doc.appendChild(sessionElement);

            //Session Attributes
            Element sessionAttributes = doc.createElement(Tags.ATTRIBUTES.toString());
            for(Enum<?> key : session.getUsedAttributes()){
                Attr attribute = doc.createAttribute(key.toString());
                attribute.setValue(ListMethods.joinList(session.getAttributeItem(key), "@!@"));
                sessionAttributes.setAttributeNode(attribute);
            }
            sessionElement.appendChild(sessionAttributes);

            //EdataList Element Add
            Element edataList = doc.createElement(Tags.EDATA_LIST.toString());
            //Iterate through exercises/edata in session
            for(EData edata : session.getEDataList()){
                //Initialize element and add name
                Element eDataElement = doc.createElement(Tags.EDATA_ITEM.toString());
                Attr eName = doc.createAttribute(Tags.EXERCISE_NAME.toString());
                eName.setValue(edata.getName());
                eDataElement.setAttributeNode(eName);
                Element eDataAttributes = doc.createElement(Tags.EDATA_VALUES.toString());
                //Iterate through eData and add corresponding attributes
                for(Enum<?> eDataKey : edata.getUsedAttributes()){
                    Attr eDataValue = doc.createAttribute(eDataKey.toString());
                    eDataValue.setValue(edata.getAttributeString(eDataKey));
                    eDataAttributes.setAttributeNode(eDataValue);
                }
                eDataElement.appendChild(eDataAttributes);

                //Adds edata payload information
                Element payloadElement = doc.createElement(Tags.EDATA_PAYLOAD.toString());
                System.out.println(edata.getPayloadKeys().size() + ":  This is size of payload keys");
                for(Enum<?> eDataKey : edata.getPayloadKeys()){
                    Attr payloadAttr = doc.createAttribute(eDataKey.toString());
                    payloadAttr.setValue(ListMethods.joinList(edata.getPayloadItem(eDataKey), "@!@"));
                    payloadElement.setAttributeNode(payloadAttr);
                }
                eDataElement.appendChild(payloadElement);

                edataList.appendChild(eDataElement);
            }

            //Add edataList and tie off document
            sessionElement.appendChild(edataList);

            //Write to xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            File dir = new File(context.getFilesDir(), "session");
            if(!dir.exists()){
                System.out.println("recreating directory for session");
                dir.mkdir();
            }

            File file = new File(dir, session.getPath());
            FileOutputStream fos = new FileOutputStream(file);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | IOException e) {

            e.printStackTrace();
        }
    }

    public static Session readSession(Context context, String filepath){

        Session session = new Session("", filepath, new ArrayList<>(), new DataMap());

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            File dir = new File(context.getFilesDir(), "session");
            if(!dir.exists()){
                System.out.println("recreating directory for session");
                dir.mkdir();
            }
            File file = new File(dir, session.getPath());

            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            //Root node data (Session tag attributes)
            Element sessionElement = doc.getDocumentElement();
            session.setName(sessionElement.getAttributes().getNamedItem(Tags.SESSION_NAME.toString()).getNodeValue());

            //Get session attributes
            Element sessionAttributes = (Element) sessionElement.getElementsByTagName(Tags.ATTRIBUTES.toString()).item(0);
            for(SAttributeKeys key : SAttributeKeys.values()){
                if(sessionAttributes.hasAttribute(key.toString())){
                    List<String> payload = Arrays.asList(
                            sessionAttributes.getAttributes().getNamedItem(key.toString()).getNodeValue().split("@!@"));
                    session.putAttribute(key, payload);
                }
            }

            //Add edata
            Element eDataList = (Element) sessionElement.getElementsByTagName(Tags.EDATA_LIST.toString()).item(0);
            for(int i = 0; i < eDataList.getElementsByTagName(Tags.EDATA_ITEM.toString()).getLength(); i++){

                Element eDataElement = (Element) eDataList.getElementsByTagName(Tags.EDATA_ITEM.toString()).item(i);
                Exercise keyExercise = ExerciseServer.getNamedExercise(eDataElement.getAttribute(Tags.EXERCISE_NAME.toString()));
                EData eDataObject = new EData(keyExercise, new DataMap());

                Element eDataAttributes = (Element) eDataElement.getElementsByTagName(Tags.EDATA_VALUES.toString()).item(0);
                Element eDataPayload = (Element) eDataElement.getElementsByTagName(Tags.EDATA_PAYLOAD.toString()).item(0);

                for(int u = 0; u < eDataAttributes.getAttributes().getLength(); u++){
                    Attr eDataItem = (Attr) eDataAttributes.getAttributes().item(u);
                    eDataObject.putAttribute(EAttributeKeys.valueOf(eDataItem.getName().toUpperCase(Locale.ROOT)), Arrays.asList(eDataItem.getValue().split("@!@")));
                }

                for(int u = 0; u < eDataPayload.getAttributes().getLength(); u++){
                    Attr eDataItem = (Attr) eDataPayload.getAttributes().item(u);
                    eDataObject.putAttribute(EDataKeys.valueOf(eDataItem.getName().toUpperCase(Locale.ROOT)), Arrays.asList(eDataItem.getValue().split("@!@")));
                }

                session.addEData(eDataObject);
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
