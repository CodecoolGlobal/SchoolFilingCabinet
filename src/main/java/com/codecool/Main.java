package com.codecool;
import java.io.*;
import java.util.Scanner;



public class Main {

    public static void main(String[] args){
        MainMenu mainMenu1 = null;
        Scanner reader = new Scanner(System.in);
        System.out.println("Do you want to load the saved data?");
        String answer = reader.nextLine();
        if (answer.equals("no")) {
            mainMenu1 = new MainMenu();
        } else if (answer.equals("yes")) {
            mainMenu1 = loadGame();
        }
        mainMenu1.start(reader);
    }

    public static MainMenu loadGame() {
        MainMenu mainMenu = null;
        try {
            FileInputStream fileIn = new FileInputStream("./FilingCabinet.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            mainMenu = (MainMenu) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Game loaded!");
        } catch (Exception e) {
            System.out.println("Load failed");
        }
        return mainMenu;
    }


}
