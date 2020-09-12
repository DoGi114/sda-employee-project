package com.sda.dao;

import com.sda.CompanyEntityManager;
import com.sda.dto.Vacation;
import com.sda.view.EmployeeManager;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class VacationsDAO {

    public void addVacation(long id, LocalDate start, LocalDate end){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        Vacation vacation = new Vacation();
        vacation.setOwner(new EmployeeDAO().getEmployee(id));
        vacation.setStarts(start);
        vacation.setEnds(end);

        entityManager.getTransaction().begin();
        entityManager.persist(vacation);
        entityManager.getTransaction().commit();
    }

    public List<Vacation> getAllVacations(){
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        return entityManager.createQuery("SELECT e FROM Vacation e", Vacation.class).getResultList();
    }

    public List<Vacation> getVacations(long id) {
        EntityManager entityManager = CompanyEntityManager.getEntityManager();

        return entityManager.createQuery("SELECT e FROM Vacation e WHERE employeeId = " + id, Vacation.class).getResultList();
    }
}
