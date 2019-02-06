package com.codecool;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner reader;
    private ArrayList<Dossier> dossiers;
    XmlForStudents students;
    XmlForTeachers teachers;

    public Menu() {
        students = new XmlForStudents("./Students.xml");
        teachers = new XmlForTeachers("./Teachers.xml");
        FilingCabinet cabinet = new FilingCabinet(20);
        reader = new Scanner(System.in);
        dossiers = new ArrayList<Dossier>();
    }

    public void start() {
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
            chosen = reader.nextLine();
            if (chosen.equals("1") || chosen.equals("2") || chosen.equals("3") || chosen.equals("4") || chosen.equals("5") || chosen.equals("0")) {
                break;
            }

        }

        int chosenInt = Integer.parseInt(chosen);
        switch (chosenInt) {
            case 1: dossiersMenu();
                    break;
            case 2: documentMenu();
                    break;
            case 3: //teachersMenu();
                    break;
            case 4: //studentMenu();
                    break;
            case 5: //saveMenu();
                    break;
            case 0:
                    break;

        }
    }

    public void showMainMenu(String[] options) {

        System.out.println("  ");

        for (int i = 0; i < options.length - 1; i++ ) {
            int num = i + 1;
            System.out.println(num + ". " + options[i]);
        }
        System.out.println(0 + ". " + options[options.length-1]);
    }

    public void dossiersMenu() {
        String chosen;
        String[] options = {"List all dossiers", "Add dossier", "Throw out dossier", "Back to Main Menu"};
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
                case 2: addDossier();
                        break;
                case 3: removeDossier();
                        break;
                case 0: toQuit = 0;
                        start();
                        break;

            }
        }

    }

    public void documentMenu() {
        String chosen;
        String[] options = {"List all documents", "Add document", "Throw out document", "Back to Main Menu"};
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
                case 2: addDocuments();
                        break;
                case 3: removeDocuments();
                        break;
                case 0: toQuit = 0;
                        start();
                        break;
            }
        }
    }

    public void teachersMenu() {
        String chosen;
        String[] options = {"List all students", "Add document", "Throw out document", "Back to Main Menu"};
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
                case 1: listAllStudents();
                    break;
                case 2: //addStudents();
                    break;
                case 3: //removeStudents();
                    break;
                case 0: toQuit = 0;
                    start();
                    break;
            }
        }
    }

    public void studentMenu() {

    }

    public void saveMenu() {

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

    public void addDossier() {
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

    public void removeDossier() {
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

    public void addDocuments() {
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
            return null;
        }
        return retDoss;
    }

    public Teacher findTeacher(String name) {
        Teacher person = null;
        for (Teacher element : teachers.getTeachersList()) {
            String fullName = element.getFirstName() + " " + element.getLastName();
            if (fullName.equals(name)) {

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

            }
        }
        if (person == null) {
            System.out.println("No dossier was found with the name: " + name );
            return null;
        }
        return person;
    }

    public void removeDocuments() {
        System.out.println("Label of the document you'd like to remove: ");
        String label = reader.nextLine();
        System.out.println("Which dossier do you want to remove the document from: ");
        String dossierName = reader.nextLine();
        Dossier dossier = findDossier(dossierName);
        dossier.removeDocFromDossier(label);
    }

    public void listAllStudents() {
        for (Student element : students.getStudentList()) {
            element.toString();
        }
    }



}
