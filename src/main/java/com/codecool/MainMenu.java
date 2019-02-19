package com.codecool;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private Scanner reader = new Scanner(System.in);

    XmlForStudents students;
    XmlForTeachers teachers;
    ArrayList<Teacher> teacherList;
    ArrayList<Student> studentList;
    WriteToXml saveToXml;
    FilingCabinet cabinet;

    public MainMenu() {
        saveToXml = new WriteToXml();
        students = new XmlForStudents("./Students.xml");
        teachers = new XmlForTeachers("./Teachers.xml");
        cabinet = new FilingCabinet(20);
        teacherList = teachers.getTeachersList();
        studentList = students.getStudentList();
    }

    public void start() {
        String[] options = {"Dossiers menu",
                "Documents menu",
                "Teachers menu",
                "Students menu",
                "Save",
                "Load previous state",
                "Exit"};

        String chosen;

        while (true) {
            showMainMenu(options);
            System.out.println("Choose an option from the above listed: ");
            chosen = reader.nextLine();
            if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") || chosen.equals("5") || chosen.equals("6") || chosen.equals("0")) {
                break;
            }

        }

        int chosenInt = Integer.parseInt(chosen);
        switch (chosenInt) {
            case 1:
                dossiersMenu();
                break;
            case 2:
                documentMenu();
                break;
            case 3:
                teachersMenu();
                break;
            case 4:
                studentMenu();
                break;
            case 5:
                cabinet.saveMenu();
                break;
            case 6:
                cabinet = loadMenu();
                start();
            case 0:
                break;

        }
    }

    public FilingCabinet loadMenu() {
        FilingCabinet cabinet = null;
        try {
            FileInputStream fileIn = new FileInputStream("./FilingCabinet.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cabinet = (FilingCabinet) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Data loaded!");
        } catch (Exception e) {
            System.out.println("Load failed");
        }
        return cabinet;
    }

    public void showMainMenu(String[] options) {

        System.out.println("  ");

        for (int i = 0; i < options.length - 1; i++) {
            int num = i + 1;
            System.out.println(num + ". " + options[i]);
        }
        System.out.println(0 + ". " + options[options.length - 1]);
    }

    public void dossiersMenu() {
        String chosen;
        String[] options = {"List all dossiers", "Add dossier", "Throw out dossier", "Back to Main MainMenu"};
        int toQuit = 1;
        while (toQuit != 0) {
            showMainMenu(options);
            while (true) {
                System.out.println("Choose an option from the above listed: ");
                chosen = reader.nextLine();
                if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") || chosen.equals("5") || chosen.equals("0")) {
                    break;
                }
            }

            int chosenInt = Integer.parseInt(chosen);
            switch (chosenInt) {
                case 1:
                    listAllDossiers();
                    break;
                case 2:
                    addDossier();
                    break;
                case 3:
                    try {
                        removeDossier();
                    } catch (NoSuchDossierException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println("You can add one now from the main menu");
                        break;
                    }
                    break;
                case 0:
                    toQuit = 0;
                    start();
                    break;

            }
        }

    }

    public void documentMenu() {
        String chosen;
        String[] options = {"List all documents", "Add document", "Throw out document", "Kist documents by given person" ,"Back to Main MainMenu"};
        int toQuit = 1;
        while (toQuit != 0) {
            showMainMenu(options);
            while (true) {
                System.out.println("Choose an option from the above listed: ");
                chosen = reader.nextLine();
                if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") ||chosen.equals("4") || chosen.equals("0")) {
                    break;
                }
            }

            int chosenInt = Integer.parseInt(chosen);
            switch (chosenInt) {
                case 1:
                    listAllDocuments();
                    break;
                case 2:
                    addDocuments();
                    break;
                case 3:
                    removeDocuments();
                    break;
                case 4: personsDocs();
                case 0:
                    toQuit = 0;
                    start();
                    break;
            }
        }
    }

    public void personsDocs() {
        System.out.println("Is it a \n 1. teacher or a \n 2. student document:");
        String chosen;
        Person person = null;
        while (true) {
            chosen = reader.nextLine();
            if (chosen.equals("1") || chosen.equals("2")) {
                break;
            }
        }
        int num = Integer.parseInt(chosen);
        try {
            if (num == 1) {
                System.out.println("Give a teacher's name: ");
                String tName = reader.nextLine();
                person = findTeacher(tName);
            } else {
                System.out.println("Give a student's name: ");
                String tName = reader.nextLine();
                person = findStudent(tName);
            }
        } catch (NoPersonException ex) {
            System.out.println(ex.getMessage());
        }

        listSpecificDocs(person);

    }

    private void listSpecificDocs(Person person) {
        if (cabinet.getDossierList().size() == 0) {
            System.out.println("No dossiers/documents added yet, you can add one now.");
        } else {
            for (Dossier dossier : cabinet.getDossierList()) {
                System.out.println(dossier.toString());
                for (Document doc : dossier.getDocs()) {
                    String fullName1 = person.getFullName();
                    String fullName2 = doc.getPerson().getFullName();
                    if (fullName1.equalsIgnoreCase(fullName2)) {
                        System.out.println(doc.toString());
                    }
                }
                System.out.println("----------------");
            }
        }
    }

    public void teachersMenu() {
        String chosen;
        String[] options = {"List all teachers", "Add teacher", "Remove teacher", "Save changes to XML file", "Back to Main MainMenu"};
        int toQuit = 1;
        while (toQuit != 0) {
            showMainMenu(options);
            while (true) {
                System.out.println("Choose an option from the above listed: ");
                chosen = reader.nextLine();
                if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") || chosen.equals("4") || chosen.equals("0")) {
                    break;
                }
            }

            int chosenInt = Integer.parseInt(chosen);
            switch (chosenInt) {
                case 1:
                    listAllTeachers();
                    break;
                case 2:
                    try {
                        addPerson(reader, "Teacher");
                    } catch (PersonAlreadyExistsException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    removePerson(reader, "Teacher");
                    break;
                case 4:
                    saveToXml.writeToTeacherXml(teacherList, "Teacher");
                    break;
                case 0:
                    toQuit = 0;
                    start();
                    break;
            }
        }
    }

    public void studentMenu() {
        String chosen;
        String[] options = {"List all students", "Add student", "Remove teacher", "Save changes to XML file", "Back to Main MainMenu"};
        int toQuit = 1;
        while (toQuit != 0) {
            showMainMenu(options);
            while (true) {
                System.out.println("Choose an option from the above listed: ");
                chosen = reader.nextLine();
                if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") || chosen.equals("0")) {
                    break;
                }
            }

            int chosenInt = Integer.parseInt(chosen);
            switch (chosenInt) {
                case 1: listAllStudents();
                        break;
                case 2:
                    try {
                        addPerson(reader, "Student");
                    } catch (PersonAlreadyExistsException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3: removePerson(reader, "Student");
                        break;
                case 4: saveToXml.writeToStudentXml(studentList, "Student");
                     break;
                case 0:
                    toQuit = 0;
                    start();
                    break;
            }
        }
    }

    public void listAllDossiers() {
        if (cabinet.getDossierList().size() == 0) {
            System.out.println("No dossiers added yet, you can add one now.");
        } else {
            for (Dossier dossier : cabinet.getDossierList()) {
                System.out.println(dossier.toString());
                dossier.listAll();
                System.out.println("-------");
            }
        }
    }

    public void addDossier() {
        if (cabinet.getDossierList().size() < 50) {
            System.out.println("Label of the new dossier:");
            String label = reader.nextLine();
            System.out.println("How many documents pages can it hold: ");
            int capacity = Integer.parseInt(reader.nextLine());
            cabinet.addDossier(new Dossier(label, capacity));
        } else {
            System.out.println("The filing cabinet is full, you could throw out a dossier.");
        }
    }

    public void removeDossier() throws NoSuchDossierException {
        System.out.println("Label of the dossier you'd like to remove: ");
        String label = reader.nextLine();
        Dossier toRemove = null;
        for (Dossier element : cabinet.getDossierList()) {
            if (element.getLabel().equals(label)) {
                toRemove = element;
            }
        }

        if (toRemove != null) {
            cabinet.removeDossier(toRemove);
        } else {
            throw new NoSuchDossierException("No dossier called" + label + "was found");
        }
    }

    public void listAllDocuments() {
        for (Dossier element : cabinet.getDossierList()) {
            System.out.println(element.getLabel());
            element.allDocsInDossier();
        }
    }

    public void addDocuments() {
        String chosen;
        System.out.println("Which dossier do you want to put the document");
        String dossierName = reader.nextLine();
        System.out.println("Give a label");
        String label = reader.nextLine();
        System.out.println("How many pages is the document: ");
        int pages = Integer.parseInt(reader.nextLine());
        System.out.println("Is it a \n 1. teacher or a \n 2. student document:");
        while (true) {
            chosen = reader.nextLine();
            if (chosen.equals("1") || chosen.equals("2")) {
                break;
            }
        }
        int num = Integer.parseInt(chosen);
        try {
            Dossier dossier = findDossier(dossierName);
            if (num == 1) {
                System.out.println("Give a teacher's name: ");
                String tName = reader.nextLine();
                dossier.addDocs(new Document(label, pages, findTeacher(tName)));
            } else {
                System.out.println("Give a student's name: ");
                String tName = reader.nextLine();
                dossier.addDocs(new Document(label, pages, findStudent(tName)));
            }
        } catch (NoPersonException | NoSuchDossierException ex) {
            System.out.println(ex.getMessage());
            //System.out.println("Create one now: ");
            //addDossier();
        }
    }

    public Dossier findDossier(String dossierName) throws NoSuchDossierException {
        Dossier retDoss = null;
        for (Dossier element : cabinet.getDossierList()) {
            if (element.getLabel().equals(dossierName)) {
                retDoss = element;
            }
        }
        if (retDoss == null) {
            System.out.println("No dossier was found with the name: " + dossierName);
            System.out.println("You can add one now from the main menu.");
            ///start();
            throw new NoSuchDossierException("No dossier with the name of: " + dossierName + " was found");
        }
        return retDoss;
    }

    public Teacher findTeacher(String name) throws NoPersonException {
        Teacher person = null;
        for (Teacher element : teacherList) {
            String fullName = element.getFirstName() + " " + element.getLastName();
            if (fullName.equals(name)) {
                person = element;
            }
        }
        if (person == null) {
            throw new NoPersonException("No teacher was found with the name: " + name);
        }
        return person;
    }

    public Student findStudent(String name) throws NoPersonException {
        Student person = null;
        for (Student element : studentList) {

            String fullName = element.getFirstName() + " " + element.getLastName();
            if (fullName.equals(name)) {
                person = element;
            }
        }
        if (person == null) {
            throw new NoPersonException("No student was found with the name: " + name);
        }
        return person;
    }

    public void removeDocuments() {
        System.out.println("Label of the document you'd like to remove: ");
        String label = reader.nextLine();
        System.out.println("Which dossier do you want to remove the document from: ");
        String dossierName = reader.nextLine();
        try {
            Dossier dossier = findDossier(dossierName);
            dossier.removeDocFromDossier(label);
        } catch (NoSuchDossierException ex) {
            System.out.println("Create one now: ");
            addDossier();
        }
    }

    public void listAllTeachers() {
        for (Teacher element : teacherList) {
            System.out.println(element.toString());
        }
    }

    public void listAllStudents() {
        for (Student element : studentList) {
            System.out.println(element.toString());
        }
    }

    public void addPerson(Scanner reader, String kindOfPerson) throws PersonAlreadyExistsException {
        System.out.println("First name of the " + kindOfPerson + ":");
        String fName = reader.nextLine();
        System.out.println("Last name of the " + kindOfPerson + ":");
        String lName = reader.nextLine();
        System.out.println("Birth year:");
        int year = Integer.parseInt(reader.nextLine());
        System.out.println("Gender (FEMALE / MALE):");
        Gender gender = Gender.valueOf(reader.nextLine());
        if (kindOfPerson.equals("Teacher")) {
            System.out.println("Wage");
            int wage = Integer.parseInt(reader.nextLine());
            Teacher teacher = new Teacher(fName, lName, year, gender, wage);
            if (!teacherList.contains(teacher)) {
                teacherList.add(teacher);
            } else {
                throw new PersonAlreadyExistsException(teacher.toString() + " already exists.");
            }
        } else if (kindOfPerson.equals("Student")) {
            System.out.println("Which class: ");
            String whichClass = reader.nextLine();
            Student student = new Student(fName, lName, year, gender, whichClass);
            if (!studentList.contains(student)) {
                studentList.add(student);
            } else {
                throw new PersonAlreadyExistsException(student.toString() + " already exists.");
            }
        }
    }

    public void removePerson(Scanner reader, String kindOfPerson) {
        Teacher toRemove = null;
        Student toRem = null;
        System.out.println("Name of the person you want to remove from the list: ");
        String name = reader.nextLine();
        if (kindOfPerson.equals("Teacher")) {
            try {
                toRemove = findTeacher(name);
            } catch (NoPersonException ex) {

            }

            for (Teacher element : teacherList) {
                String fullName = element.getFirstName() + " " + element.getLastName();
                if (fullName.equals(name)) {
                    toRemove = element;
                }
            }
        } else if (kindOfPerson.equals("Student")) {
            try {
                toRem = findStudent(name);
            } catch (NoPersonException ex) {

            }
            for (Student element : studentList) {
                String fullName = element.getFirstName() + " " + element.getLastName();
                if (fullName.equals(name)) {
                    toRem = element;
                }
            }
        }
        if (kindOfPerson.equals("Teacher")) {
            teacherList.remove(toRemove);
        } else if (kindOfPerson.equals("Student")) {
            studentList.remove(toRem);
        }
    }


}
