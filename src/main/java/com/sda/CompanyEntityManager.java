package com.sda;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CompanyEntityManager {
    private static final CompanyEntityManager INSTANCE = new CompanyEntityManager();
    @Getter
    private static EntityManagerFactory entityManagerFactory ;
    @Getter
    private static EntityManager entityManager;
    private static boolean bTest = false;

    private CompanyEntityManager() {

    }

    public static CompanyEntityManager getInstance(){
        return INSTANCE;
    }

    public static void close(){
        entityManagerFactory.close();
        entityManager.close();
    }

    public static void setTest(boolean test){
        bTest = test;
    }

    public static void open() {
        if(bTest){
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa.company.test");
        }else {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa.company");
        }
        entityManager = entityManagerFactory.createEntityManager();
    }
}
