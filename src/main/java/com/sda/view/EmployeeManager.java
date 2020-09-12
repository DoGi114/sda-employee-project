package com.sda.view;

import com.sda.CompanyEntityManager;
import com.sda.dto.Employee;
import com.sda.view.table.TablePrinter;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager {


    public void consoleAllEmployees() {
        consoleAllEmployees(getAllEmployees());
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
            consoleAllEmployees(List.of(getEmployee(id)));
        }catch (NullPointerException e){
            System.err.println("There is no employee with given id!");
        }
    }

    public List<Employee> getAllEmployees() {

        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        List<Employee> employeeList = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        

        return employeeList;
    }

    public Employee getEmployee(long id) {

        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        Employee employee = entityManager.find(Employee.class, id);

        

        return employee;
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

    public void addEmployee(Employee employee){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        
    }

    public void consoleRemoveEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id:");

        long id = Long.parseLong(scanner.nextLine());

        removeEmployee(id);
    }

    public void removeEmployee(long id){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        entityManager.getTransaction().begin();

        Employee employee = getEmployee(id);

        if (!entityManager.contains(employee)) {
            employee = entityManager.getReference(employee.getClass(), employee.getId());
        }

        entityManager.remove(employee);
        entityManager.getTransaction().commit();


        
    }

    public void consoleUpdateEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id:");
        long id = Long.parseLong(scanner.nextLine());
        System.out.println("Podaj nowe stanowisko:");
        String newPosition = scanner.nextLine();
        System.out.println("Podaj nowe wyngrodzenie:");
        double newSalary = Double.parseDouble(scanner.nextLine());
        updateEmployee(id, newPosition, newSalary);
    }

    public void updateEmployee(Long id, String newPosition, double newSalary){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();
        entityManager.getTransaction().begin();

        Employee employee = getEmployee(id);

        if (!entityManager.contains(employee)) {
            employee = entityManager.getReference(employee.getClass(), employee.getId());
        }

        employee.setPosition(newPosition);
        employee.setSalary(newSalary);

        entityManager.getTransaction().commit();
        
    }
}
