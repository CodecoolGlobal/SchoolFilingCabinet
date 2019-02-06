package com.codecool;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlForStudents extends XMLParser {
    private List<Map> listFacts;
    private ArrayList<Student> students;

    public XmlForStudents(String xmlPath) {
        students = new ArrayList<Student>();
        loadXmlDocument(xmlPath);
        Element rootNode = doc.getDocumentElement();
        List<Element> studentNodes = getElements(rootNode);
        addStudents(studentNodes);
    }

    public  void addStudents(List<Element> studentNodes) {
        for (Element studentNode : studentNodes ) {
            List<Element> fieldNodes = getElements(studentNode);
            String firstName = getString(fieldNodes, "firstName");
            String lastName = getString(fieldNodes, "lastName");
            int yearOfBirth = Integer.valueOf(getString(fieldNodes, "yearOfBirth"));
            Gender gender = Gender.valueOf(getString(fieldNodes, "gender"));
            String whichClass = getString(fieldNodes, "whichClass");
            Student student = new Student(firstName, lastName, yearOfBirth, gender, whichClass);
            students.add(student);
        }
    }

    public String getString(List<Element> elements, String name) {
        for (Element element : elements) {
            if (element.getTagName().equals(name)) {
                return element.getTextContent();
            }
        }
        throw new IllegalStateException();
    }

    public List<Element> getElements(Element parentNode) {
        ArrayList<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node childnode = parentNode.getChildNodes().item(i);
            if (childnode instanceof Element) {
                elements.add((Element) childnode);
            }

        }
        return elements;
    }

    public ArrayList<Student> getStudentList() {
        return students;
    }



}
