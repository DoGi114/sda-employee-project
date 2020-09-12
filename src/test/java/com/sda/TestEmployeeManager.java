package com.sda;

import com.sda.dao.EmployeeDAO;
import org.junit.*;
import org.junit.runners.MethodSorters;
import com.sda.CompanyEntityManager;
import com.sda.dto.Employee;
import com.sda.view.EmployeeManager;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeManager {

    @Before
    public void before() throws Exception {
        CompanyEntityManager.setTest(true);
        CompanyEntityManager.open();
    }

    @After
    public void after() throws Exception {
        CompanyEntityManager.setTest(false);
        CompanyEntityManager.close();
    }

    @Test
    public void Test1AddEmployee() throws Exception {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee e = new Employee();
        e.setFirstName("Damian");
        e.setLastName("Nguyen");
        e.setPosition("Programista");
        e.setBirthYear(LocalDate.parse("08-07-1993", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        e.setSalary(5000D);
        employeeDAO.addEmployee(e);

        EntityManager entityManager = CompanyEntityManager.getEntityManager();
        Employee e2 = entityManager.find(Employee.class, 1L);

        Assert.assertEquals(e, e2);

        Employee e3 = new Employee("Paulina", "Buczkowska", "Grafik", 4000D, LocalDate.of(1994,2,4));
        employeeDAO.addEmployee(e3);
        Employee e4 = entityManager.find(Employee.class, 2L);

        Assert.assertEquals(e3, e4);
    }

    @Test
    public void Test2GetEmployee() throws Exception {
        Test1AddEmployee();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.getEmployee(2L);
        Assert.assertEquals("Paulina", employee.getFirstName());
        Assert.assertEquals("Buczkowska", employee.getLastName());
        Assert.assertEquals("Grafik", employee.getPosition());

    }

    @Test
    public void Test3GetAllEmployees() throws Exception {
        Test2GetEmployee();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        Assert.assertEquals(2, allEmployees.size());
    }

    @Test
    public void Test4UpdateEmployee() throws Exception {
        Test3GetAllEmployees();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.updateEmployee(1L, "Mid Dev", 8000);
        Employee employee = employeeDAO.getEmployee(1L);
        Assert.assertEquals("Mid Dev", employee.getPosition());
        Assert.assertEquals(8000.0D, employee.getSalary(), 1);
    }

    @Test
    public void Test5RemoveEmployee()throws Exception {
        Test4UpdateEmployee();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.removeEmployee(2L);
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        Assert.assertEquals(1, allEmployees.size());
    }
}
