package com.sda.view;

import com.sda.CompanyEntityManager;
import com.sda.dto.Vacation;
import com.sda.view.table.TablePrinter;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VacationsManager {

    public void consoleAddVacation() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id pracownika:");
        long id = Long.parseLong(scanner.nextLine());

        System.out.println("Podaj poczatkowa date urlopu w formacie dd-MM-RRRR:");
        LocalDate start =LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.println("Podaj końcową date urlopu w formacie dd-MM-RRRR:");
        LocalDate end = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        addVacation(id, start, end);
    }

    public void addVacation(long id, LocalDate start, LocalDate end){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        Vacation vacation = new Vacation();
        vacation.setOwner(new EmployeeManager().getEmployee(id));
        vacation.setStarts(start);
        vacation.setEnds(end);

        entityManager.getTransaction().begin();
        entityManager.persist(vacation);
        entityManager.getTransaction().commit();


    }

    public List<Vacation> getAllVacations(){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        List<Vacation> vacationList = entityManager.createQuery("SELECT e FROM Vacation e", Vacation.class).getResultList();




        return vacationList;
    }

    public void consoleAllVacations() {
        consoleAllVacations(getAllVacations());
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

    public List<Vacation> getVacations(long id) {
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        List<Vacation> vacationList = entityManager.createQuery("SELECT e FROM Vacation e WHERE employeeId = " + id, Vacation.class).getResultList();



        return vacationList;
    }

    public void consoleVacations(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id:");
        long id = Long.parseLong(scanner.nextLine());
        printVacation(id);
    }

    public void printVacation(long id) {
        try {
            consoleAllVacations(getVacations(id));
        }catch (NullPointerException e){
            System.err.println("There is no employee or vacations with given id!");
        }
    }
}
