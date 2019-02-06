package com.codecool;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlForTeachers extends XMLParser {
    private List<Map> listFacts;
    private ArrayList<Teacher> teachers;

    public XmlForTeachers(String xmlPath) {
        teachers = new ArrayList<Teacher>();
        loadXmlDocument(xmlPath);
        Element rootNode = doc.getDocumentElement();
        List<Element> teacherNodes = getElements(rootNode);
        addTeacher(teacherNodes);
    }

    public  void addTeacher(List<Element> teacherNodes) {
        for (Element teacherNode : teacherNodes ) {
            List<Element> fieldNodes = getElements(teacherNode);
            String firstName = getString(fieldNodes, "firstName");
            String lastName = getString(fieldNodes, "lastName");
            int yearOfBirth = Integer.valueOf(getString(fieldNodes, "yearOfBirth"));
            Gender gender = Gender.valueOf(getString(fieldNodes, "gender"));
            int wage = Integer.valueOf(getString(fieldNodes, "wage"));
            Teacher teacher = new Teacher(firstName, lastName, yearOfBirth, gender, wage);
            teachers.add(teacher);
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

    public ArrayList<Teacher> getTeachersList() {
        return teachers;
    }



}
