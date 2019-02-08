package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu implements Serializable  {
    private transient Scanner reader;
    private ArrayList<Dossier> dossiers;
    XmlForStudents students;
    XmlForTeachers teachers;
    ArrayList<Teacher> teacherList;
    ArrayList<Student> studentList;
    WriteToXml saveToXml;


    public MainMenu() {
        saveToXml = new WriteToXml();
        students = new XmlForStudents("./Students.xml");
        teachers = new XmlForTeachers("./Teachers.xml");
        FilingCabinet cabinet = new FilingCabinet(20);
        this.reader = reader;
        dossiers = new ArrayList<Dossier>();
        teacherList = teachers.getTeachersList();
        studentList = students.getStudentList();
    }

    public void start(Scanner reader1) {
        String[] options = {"Dossiers menu",
                "Documents menu",
                "Teachers menu",
                "Students menu",
                "Save",
                "Exit"};

        String chosen;
        showMainMenu(options);
        while (true) {
            System.out.println("Choose an option from the above listed: ");
            chosen = reader1.nextLine();
            if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") || chosen.equals("5") || chosen.equals("0")) {
                break;
            }

        }

        int chosenInt = Integer.parseInt(chosen);
        switch (chosenInt) {
            case 1: dossiersMenu( reader1);
                    break;
            case 2: documentMenu(reader1);
                    break;
            case 3: teachersMenu(reader1);
                    break;
            case 4: studentMenu(reader1);
                    break;
            case 5: saveMenu();
                    break;
            case 0:
                    break;

        }
    }

    private void saveMenu() {
        try {
            save();
            System.out.println("Data saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("./FilingCabinet.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    public void showMainMenu(String[] options) {

        System.out.println("  ");

        for (int i = 0; i < options.length - 1; i++ ) {
            int num = i + 1;
            System.out.println(num + ". " + options[i]);
        }
        System.out.println(0 + ". " + options[options.length-1]);
    }

    public void dossiersMenu(Scanner reader) {
        String chosen;
        String[] options = {"List all dossiers", "Add dossier", "Throw out dossier", "Back to Main MainMenu"};
        showMainMenu(options);
        int toQuit = 1;
        while (toQuit != 0) {
            while (true) {
                System.out.println("Choose an option from the above listed: ");
                chosen = reader.nextLine();
                if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") || chosen.equals("5") || chosen.equals("0")) {
                    break;
                }
            }

            int chosenInt = Integer.parseInt(chosen);
            switch (chosenInt) {
                case 1: listAllDossiers();
                        break;
                case 2: addDossier(reader);
                        break;
                case 3: removeDossier(reader);
                        break;
                case 0: toQuit = 0;
                        start(reader);
                        break;

            }
        }

    }

    public void documentMenu(Scanner reader) {
        String chosen;
        String[] options = {"List all documents", "Add document", "Throw out document", "Back to Main MainMenu"};
        showMainMenu(options);
        int toQuit = 1;
        while (toQuit != 0) {
            while (true) {
                System.out.println("Choose an option from the above listed: ");
                chosen = reader.nextLine();
                if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("0")) {
                    break;
                }
            }

            int chosenInt = Integer.parseInt(chosen);
            switch (chosenInt) {
                case 1: listAllDocuments();
                        break;
                case 2: addDocuments(reader);
                        break;
                case 3: removeDocuments(reader);
                        break;
                case 0: toQuit = 0;
                        start(reader);
                        break;
            }
        }
    }

    public void teachersMenu(Scanner reader) {
        String chosen;
        String[] options = {"List all teachers", "Add teacher", "Remove teacher", "Save changes to XML file", "Back to Main MainMenu"};
        showMainMenu(options);
        int toQuit = 1;
        while (toQuit != 0) {
            while (true) {
                System.out.println("Choose an option from the above listed: ");
                chosen = reader.nextLine();
                if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") ||  chosen.equals("0")) {
                    break;
                }
            }

            int chosenInt = Integer.parseInt(chosen);
            switch (chosenInt) {
                case 1: listAllTeachers();
                        break;
                case 2: addPerson(reader, "Teacher");
                        break;
                case 3: removePerson(reader, "Teacher");
                        break;
                case 4: saveToXml.writeToTeacherXml(teacherList, "Teacher");
                        break;
                case 0: toQuit = 0;
                        start(reader);
                        break;
            }
        }
    }

    public void studentMenu(Scanner reader) {
        String chosen;
        String[] options = {"List all students", "Add document", "Throw out document", "Back to Main MainMenu"};
        showMainMenu(options);
        int toQuit = 1;
        while (toQuit != 0) {
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
                case 2: addPerson(reader, "Student");
                        break;
                case 3: removePerson(reader, "Student");
                        break;
                case 4: saveToXml.writeToStudentXml(studentList, "Student");
                        break;
                case 0: toQuit = 0;
                        start(reader);
                        break;
            }
        }
    }

    public void listAllDossiers() {
        if (dossiers.size() == 0) {
            System.out.println("No dossiers added yet, you can add one now.");
        } else {
            for (Dossier dossier : dossiers) {
                System.out.println(dossier.toString());
            }
        }
    }

    public void addDossier(Scanner reader) {
        if (dossiers.size() < 50) {
            System.out.println("Label of the new dossier:");
            String label = reader.nextLine();
            System.out.println("How many documents pages can it hold: ");
            int capacity = Integer.parseInt(reader.nextLine());
            dossiers.add(new Dossier(label, capacity));
        } else {
            System.out.println("The filing cabinet is full, you could throw out a dossier.");
        }
    }

    public void removeDossier(Scanner reader) {
        System.out.println("Label of the dossier you'd like to remove: ");
        String label = reader.nextLine();
        Dossier toRemove = null;
        for (Dossier element : dossiers) {
            if ( element.getLabel().equals(label)) {
                toRemove = element;
            }
        }

        if (toRemove != null) {
            dossiers.remove(toRemove);
        } else {
            System.out.println("No such a dossier found.");
        }
    }

    public void listAllDocuments() {
        for ( Dossier element : dossiers) {
            System.out.println(element.getLabel());
            element.allDocsInDossier();
        }
    }

    public void addDocuments(Scanner reader) {
        String chosen;
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
        System.out.println("Which dossier do you want to put the document");
        String dossierName = reader.nextLine();
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
    }

    public Dossier findDossier(String dossierName) {
        Dossier retDoss = null;
        for (Dossier element : dossiers) {
            if (element.getLabel().equals(dossierName)) {
                retDoss = element;
            }
        }
        if (retDoss == null) {
            System.out.println("No dossier was found with the name: " + dossierName );
            System.out.println("You can add one now from the main menu.");
            ///start();
            return null;
        }
        return retDoss;
    }

    public Teacher findTeacher(String name) {
        Teacher person = null;
        for (Teacher element : teachers.getTeachersList()) {
            String fullName = element.getFirstName() + " " + element.getLastName();
            if (fullName.equals(name)) {
                person = element;
            }
        }
        if (person == null) {
            System.out.println("No dossier was found with the name: " + name );
            return null;
        }
        return person;
    }

    public Student findStudent(String name) {
        Student person = null;
        for (Student element : students.getStudentList()) {

            String fullName = element.getFirstName() + " " + element.getLastName();
            if (fullName.equals(name)) {
                person = element;
            }
        }
        if (person == null) {
            System.out.println("No student was found with the name: " + name );
            return null;
        }
        return person;
    }

    public void removeDocuments(Scanner reader) {
        System.out.println("Label of the document you'd like to remove: ");
        String label = reader.nextLine();
        System.out.println("Which dossier do you want to remove the document from: ");
        String dossierName = reader.nextLine();
        Dossier dossier = findDossier(dossierName);
        dossier.removeDocFromDossier(label);
    }

    public void listAllTeachers() {
        for (Teacher element : teachers.getTeachersList()) {
            System.out.println(element.toString());
        }
    }

    public void listAllStudents() {
        for (Student element : students.getStudentList()) {
            System.out.println(element.toString());
        }
    }

    public void addPerson(Scanner reader, String kindOfPerson){
        System.out.println("First name of the "+ kindOfPerson + ":");
        String fName = reader.nextLine();
        System.out.println("Last name of the "+ kindOfPerson + ":");
        String lName = reader.nextLine();
        System.out.println("Birth year:");
        int year = Integer.parseInt(reader.nextLine());
        System.out.println("Gender (FEMALE / MALE):");
        Gender gender = Gender.valueOf(reader.nextLine());
        if (kindOfPerson.equals("Teacher")) {
            System.out.println("Wage");
            int wage = Integer.parseInt(reader.nextLine());
            teacherList.add(new Teacher(fName, lName, year, gender, wage));
        } else if (kindOfPerson.equals("Student")) {
            System.out.println("Which class: ");
            String whichClass = reader.nextLine();
            studentList.add(new Student(fName, lName, year, gender, whichClass));
        }
    }

    public void removePerson(Scanner reader, String kindOfPerson) {
        Teacher toRemove = null;
        Student toRem = null;
        System.out.println("Name of the person you want to remove from the list: ");
        String name = reader.nextLine();
        if (kindOfPerson.equals("Teacher")) {
            toRemove = findTeacher(name);
            for (Teacher element : teacherList) {
                String fullName = element.getFirstName() + " " + element.getLastName();
                if (fullName.equals(name)) {
                    toRemove = element;
                }
            }
        } else if (kindOfPerson.equals("Student")) {
            toRem = findStudent(name);
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
