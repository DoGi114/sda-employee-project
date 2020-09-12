package com.sda;

import com.sda.dao.EmployeeDAO;
import com.sda.dao.VacationsDAO;
import org.junit.*;
import org.junit.runners.MethodSorters;
import com.sda.CompanyEntityManager;
import com.sda.dto.Employee;
import com.sda.dto.Vacation;
import com.sda.view.EmployeeManager;
import com.sda.view.VacationsManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestVacationsManager {
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
    public void Test1AddVacation(){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee e = new Employee();
        e.setFirstName("Damian");
        e.setLastName("Nguyen");
        e.setPosition("Programista");
        e.setBirthYear(LocalDate.parse("08-07-1993", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        e.setSalary(5000D);
        employeeDAO.addEmployee(e);

        Employee e2 = new Employee();
        e2.setFirstName("Paulina");
        e2.setLastName("Buczkowska");
        e2.setPosition("Grafik");
        e2.setBirthYear(LocalDate.parse("04-02-1994", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        e2.setSalary(4000D);
        employeeDAO.addEmployee(e2);

        VacationsDAO vacationsDAO = new VacationsDAO();
        Vacation v1 =  new Vacation();
        v1.setOwner(e);
        v1.setStarts(LocalDate.of(2020,7,6));
        v1.setEnds(LocalDate.of(2020,7,10));
        Vacation v2 =  new Vacation();
        v2.setOwner(e);
        v2.setStarts(LocalDate.of(2020,10,6));
        v2.setEnds(LocalDate.of(2020,10,10));
        Vacation v3 =  new Vacation();
        v3.setOwner(e2);
        v3.setStarts(LocalDate.of(2020,7,6));
        v3.setEnds(LocalDate.of(2020,7,10));

        vacationsDAO.addVacation(v1.getOwner().getId(), v1.getStarts(), v1.getEnds());
        vacationsDAO.addVacation(v2.getOwner().getId(), v2.getStarts(), v2.getEnds());
        vacationsDAO.addVacation(v3.getOwner().getId(), v3.getStarts(), v3.getEnds());
        Assert.assertEquals(3, vacationsDAO.getAllVacations().size());
    }

    @Test
    public void Test2GetVacations(){
        Test1AddVacation();
        VacationsDAO vacationsDAO = new VacationsDAO();
        Assert.assertEquals(2, vacationsDAO.getVacations(1L).size());
    }

    @Test
    public void Test3GetAllVacations(){
        Test2GetVacations();
        VacationsDAO vacationsDAO = new VacationsDAO();
        Assert.assertEquals(3, vacationsDAO.getAllVacations().size());
    }
}
