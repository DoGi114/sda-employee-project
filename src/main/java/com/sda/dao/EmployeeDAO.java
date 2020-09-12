package com.sda.dao;

import com.sda.CompanyEntityManager;
import com.sda.dto.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeDAO {


    public List<Employee> getAllEmployees() {
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public Employee getEmployee(long id) {
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        return entityManager.find(Employee.class, id);
    }

    public void addEmployee(Employee employee){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
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
