package com.codecool;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteToXml implements Serializable {

    public void writeToTeacherXml(ArrayList<Teacher> teacher, String type) {
        DocumentBuilder docBuilder = null;
        Document doc = null;
        Element rootElement = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("Teachers");

            doc.appendChild(rootElement);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        for (int i = 0; i < teacher.size(); i++) {
            try {
                // Teacher elements
                Element staff = doc.createElement("Teacher");

                rootElement.appendChild(staff);

                // set attribute to staff element
                //Attr attr = doc.createAttribute("id");
                //attr.setValue("1");
                //staff.setAttributeNode(attr);
                //No need for attribute id for my XML file

                //firstname elements
                Element firstName = doc.createElement("firstName");
                firstName.appendChild(doc.createTextNode(teacher.get(i).getFirstName()));
                staff.appendChild(firstName);

                // lastName elements
                Element lastName = doc.createElement("lastName");
                lastName.appendChild(doc.createTextNode(teacher.get(i).getLastName()));
                staff.appendChild(lastName);

                // yearOfBirth elements
                Element yearOfBirth = doc.createElement("yearOfBirth");
                yearOfBirth.appendChild(doc.createTextNode(String.valueOf(teacher.get(i).getYearOfBirth())));
                staff.appendChild(yearOfBirth);

                // gender elements
                Element gender = doc.createElement("gender");
                gender.appendChild(doc.createTextNode(String.valueOf(teacher.get(i).getGender())));
                staff.appendChild(gender);

                //wage elements
                Element wage = doc.createElement("wage");
                wage.appendChild(doc.createTextNode(String.valueOf(teacher.get(i).getWage())));
                staff.appendChild(wage);



                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = null;
                if (type.equals("Teacher")) {
                    result = new StreamResult(new File("./Teachers.xml"));
                } else if (type.equals("Student")) {
                    result = new StreamResult(new File("./Students.xml"));
                }


                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

                System.out.println(teacher.get(i).getFirstName() + " " + teacher.get(i).getLastName() + "'s data's sucessfully saved to file!");
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }
    }

    public void writeToStudentXml(ArrayList<Student> student, String type) {
        DocumentBuilder docBuilder = null;
        Document doc = null;
        Element rootElement = null;
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("Students");

            doc.appendChild(rootElement);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }

        for (int i = 0; i < student.size(); i++) {
            try {


                // Student elements
                Element staff = doc.createElement("Student");
                rootElement.appendChild(staff);

                Element firstNamee = doc.createElement("firstName");
                firstNamee.appendChild(doc.createTextNode(student.get(i).getFirstName()));
                staff.appendChild(firstNamee);

                // lastName elements
                Element lastName = doc.createElement("lastName");
                lastName.appendChild(doc.createTextNode(student.get(i).getLastName()));
                staff.appendChild(lastName);

                // yearOfBirth elements
                Element yearOfBirth = doc.createElement("yearOfBirth");
                yearOfBirth.appendChild(doc.createTextNode(String.valueOf(student.get(i).getYearOfBirth())));
                staff.appendChild(yearOfBirth);

                // gender elements
                Element gender = doc.createElement("gender");
                gender.appendChild(doc.createTextNode(String.valueOf(student.get(i).getGender())));
                staff.appendChild(gender);

                //which class
                Element whichClass = doc.createElement("whichClass");
                whichClass.appendChild(doc.createTextNode(student.get(i).getWhichClass()));
                staff.appendChild(whichClass);



                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = null;

                result = new StreamResult(new File("./Students.xml"));



                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

                System.out.println(student.get(i).getFirstName() + " " + student.get(i).getLastName() + "'s data's sucessfully saved to file!");
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }
    }
}
