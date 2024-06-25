package com.github.angel.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaFactoryProvider {
    private static final EntityManagerFactory createEntityManager = factory();
    private static EntityManagerFactory factory(){
      return Persistence.createEntityManagerFactory("java-hibernate");
    }

    private JpaFactoryProvider() {}

    public static EntityManager getEntityManager(){
        return createEntityManager.createEntityManager();
    }
}
