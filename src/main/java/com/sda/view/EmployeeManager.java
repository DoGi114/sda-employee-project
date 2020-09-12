package com.sda.view;

import com.sda.CompanyEntityManager;
import com.sda.dao.EmployeeDAO;
import com.sda.dto.Employee;
import com.sda.view.table.TablePrinter;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    public void consoleAllEmployees() {
        consoleAllEmployees(employeeDAO.getAllEmployees());
    }

    private void consoleAllEmployees(List<Employee> employees) {
        TablePrinter<Employee> tablePrinter = new TablePrinter<Employee>()
                .withData(employees)
                .withColumn("Imię", employee -> ((Employee) employee).getFirstName())
                .withColumn("Nazwisko", employee -> ((Employee) employee).getLastName())
                .withColumn("Stanowisko", employee -> ((Employee) employee).getPosition())
                .withColumn("Rok ur.", employee -> ((Employee) employee).getBirthYear().toString())
                .withColumn("Wynagrodzenie", employee -> ((Employee) employee).getSalary().toString());

        tablePrinter.printTable();

    }

    public void consoleEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id:");
        long id = Long.parseLong(scanner.nextLine());

        try {
            consoleAllEmployees(List.of(employeeDAO.getEmployee(id)));
        }catch (NullPointerException e){
            System.err.println("There is no employee with given id!");
        }
    }

    public void consoleAddEmployee() {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();

        System.out.println("Podaj imię:");
        employee.setFirstName(scanner.nextLine());

        System.out.println("Podaj nazwisko:");
        employee.setLastName(scanner.nextLine());

        System.out.println("Podaj stanowisko:");
        employee.setPosition(scanner.nextLine());

        System.out.println("Podaj rok urodzenia w formacie dd-MM-RRRR:");
        employee.setBirthYear(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        System.out.println("Podaj wyngrodzenie:");
        employee.setSalary(Double.parseDouble(scanner.nextLine()));
    }

    public void consoleRemoveEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id:");

        long id = Long.parseLong(scanner.nextLine());

        employeeDAO.removeEmployee(id);
    }

    public void consoleUpdateEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id:");
        long id = Long.parseLong(scanner.nextLine());
        System.out.println("Podaj nowe stanowisko:");
        String newPosition = scanner.nextLine();
        System.out.println("Podaj nowe wyngrodzenie:");
        double newSalary = Double.parseDouble(scanner.nextLine());
        employeeDAO.updateEmployee(id, newPosition, newSalary);
    }
}
