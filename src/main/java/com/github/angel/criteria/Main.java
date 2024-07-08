package com.github.angel.criteria;

import com.github.angel.entity.Customer;
import com.github.angel.utils.JpaFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager= JpaFactoryProvider.getEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> from = query.from(Customer.class);
        query.select(from);
        List<Customer> customers = entityManager.createQuery(query).getResultList();

        customers.forEach(System.out::println);



        entityManager.close();
    }
}
