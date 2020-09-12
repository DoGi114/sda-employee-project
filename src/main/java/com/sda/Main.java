package com.sda;

import com.sda.view.ConsoleManager;

public class Main {

    public static void main(String[] args) {
        CompanyEntityManager.open();
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.start();
        CompanyEntityManager.close();
    }


}
