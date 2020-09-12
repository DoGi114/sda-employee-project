package com.sda.view;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {

    private final EmployeeManager employeeManager = new EmployeeManager();
    private final VacationsManager vacationsManager = new VacationsManager();

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    public void start() {
        char userChoice = ' ';
        while (userChoice != 'q') {
            printMenu();
            userChoice = readChar();
            executeAction(userChoice);
        }

    }

    private void printMenu() {
        clrscr();
        System.out.println("Menu:");
        System.out.println("1 - Lista pracowników");
        System.out.println("2 - Dodaj pracownika");
        System.out.println("3 - Wyświetl pracownika");
        System.out.println("4 - Usuń pracownika");
        System.out.println("5 - Odświerz dane pracownika");
        System.out.println("6 - Dodaj urlop");
        System.out.println("7 - Lista urlopów");
        System.out.println("8 - Lista urlopów danego pracownika");
        System.out.println();
        System.out.println("q - wyjście");

    }

    private void executeAction(char userChoice) {
        switch (userChoice) {
            case '1':
                employeeManager.consoleAllEmployees();
                pressEnterKeyToContinue();
                break;
            case '2':
                employeeManager.consoleAddEmployee();
                break;
            case '3':
                employeeManager.consoleEmployee();
                pressEnterKeyToContinue();
                break;
            case '4':
                employeeManager.consoleRemoveEmployee();
                break;
            case '5':
                employeeManager.consoleUpdateEmployee();
                break;
            case '6':
                vacationsManager.consoleAddVacation();
                break;
            case '7':
                vacationsManager.consoleAllVacations();
                pressEnterKeyToContinue();
                break;
            case '8':
                vacationsManager.consoleVacations();
                pressEnterKeyToContinue();
                break;
        }
    }

    public void pressEnterKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        readChar();
    }

    private char readChar() {
        Scanner s = new Scanner(System.in);
        return (char) s.nextLine().chars().findFirst().orElse(0);

    }
}
