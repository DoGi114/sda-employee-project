package com.sda.view;

import com.sda.CompanyEntityManager;
import com.sda.dao.VacationsDAO;
import com.sda.dto.Vacation;
import com.sda.view.table.TablePrinter;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VacationsManager {

    private final VacationsDAO vacationsDAO = new VacationsDAO();

    public void consoleAddVacation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id pracownika:");
        long id = Long.parseLong(scanner.nextLine());

        System.out.println("Podaj poczatkowa date urlopu w formacie dd-MM-RRRR:");
        LocalDate start =LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.println("Podaj końcową date urlopu w formacie dd-MM-RRRR:");
        LocalDate end = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        vacationsDAO.addVacation(id, start, end);
    }

    public void consoleAllVacations() {
        consoleAllVacations(vacationsDAO.getAllVacations());
    }

    private void consoleAllVacations(List<Vacation> vacations){

        TablePrinter<Vacation> tablePrinter = new TablePrinter<Vacation>()
                .withData(vacations)
                .withColumn("Imię", vacation -> ((Vacation) vacation).getOwner().getFirstName())
                .withColumn("Nazwisko", vacation -> ((Vacation) vacation).getOwner().getLastName())
                .withColumn("Od", vacation -> ((Vacation) vacation).getStarts().toString())
                .withColumn("Do", vacation -> ((Vacation) vacation).getEnds().toString());

        tablePrinter.printTable();
    }

    public void consoleVacations(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id:");
        long id = Long.parseLong(scanner.nextLine());
        printVacation(id);
    }

    public void printVacation(long id) {
        try {
            consoleAllVacations(vacationsDAO.getVacations(id));
        }catch (NullPointerException e){
            System.err.println("There is no employee or vacations with given id!");
        }
    }
}
