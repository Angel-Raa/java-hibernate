package com.github.angel.repository.impl;

import com.github.angel.entity.Customer;
import com.github.angel.exception.CustomerNotFoundException;
import com.github.angel.repository.CustomerRepository;
import com.github.angel.utils.JpaFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final EntityManager entityManager = JpaFactoryProvider.getEntityManager();

    @Override
    public Customer findByEmail(String email) {
        Customer customer;
        final String sql = "SELECT * FROM customers WHERE email = :email";
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            query.setParameter("email", email);
            customer = (Customer) query.getSingleResult();
            entityManager.getTransaction().commit();
            return customer;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Customer not found with: " + email, e);
        }

    }

    @Override
    public Customer findByFirstName(String firstName) {
        Customer customer = new Customer();
        final String sql = "SELECT * FROM customers WHERE first_name = :firstName";
        try {
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            customer = (Customer) query.getSingleResult();
            return customer;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("There are no customers with that name", e);
        }
    }

    @Override
    public List<Customer> findByCityContaining(String city) {
        String s = "%" + city.toUpperCase();
        List<Customer> customers;
        final String sql = "SELECT * FROM customers AS c WHERE UPPER(c.city) LIKE :city";
        try {
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            query.setParameter("city", s);
            customers = query.getResultList();
            return customers;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Error retrieving customers", e);
        }

    }

    @Override
    public List<Customer> findByCityAndStreet(String city, String street) {
        List<Customer> customers;
        final String sql = "SELECT * FROM customers AS c WHERE c.city=:city AND c.street=:street ";
        try {
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            query.setParameter("city", city);
            query.setParameter("street", street);
            customers = query.getResultList();
            return customers;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Error retrieving customers", e);
        }

    }

    @Override
    public List<Customer> findAllByOrderByLastNameAsc() {
        List<Customer> customers;
        final String sql = "SELECT *FROM customers AS c ORDER BY c.last_name ASC ";
        try {
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            customers = query.getResultList();
            return customers;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Error retrieving customers", e);
        }

    }

    @Override
    public List<Customer> findByNotesContaining(String keyword) {
        final String s = keyword.toUpperCase() + "%";
        List<Customer> customers;
        final String sql = "SELECT * FROM customers AS c WHERE UPPER(c.notes) LIKE :keyword";
        try {
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            query.setParameter("keyword", s);
            customers = query.getResultList();
            return customers;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Error retrieving customers", e);
        }
    }

    @Override
    public List<Customer> findByDateOfBirthBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<Customer> customers;
        final String sql = "SELECT * FROM customers AS c WHERE  c.date_of_birth BETWEEN :startDate AND :endDate";
        try {
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            customers = query.getResultList();
            return customers;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Error retrieving customers", e);

        }
    }

    @Override
    public List<Customer> findAllByOrderByFirstNameAsc() {
        return List.of();
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers;
        try {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
            customers = query.getResultList();
            return customers;
        } catch (Exception e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Error retrieving customers", e);
        }
    }

    @Override
    public Customer findById(Long id) {
        Customer customer;
        final String sql = "SELECT * FROM customers WHERE customer_id =:customerId ";
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            query.setParameter("customerId", id).getFirstResult();
            customer = (Customer) query.getSingleResult();
            entityManager.getTransaction().commit();
            return customer;
        } catch (NoResultException e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Customer not found with: " + id.toString(), e);
        }
    }

    @Override
    public void save(Customer entity) {
        final String sql = "INSERT INTO customers (first_name, last_name, email, phone_number, date_of_birth, street, notes, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            entityManager.getTransaction().begin();
            Query nativeQuery = entityManager.createNativeQuery(sql, Customer.class);
            nativeQuery.setParameter(1, entity.getFirstName());
            nativeQuery.setParameter(2, entity.getLastName());
            nativeQuery.setParameter(3, entity.getEmail());
            nativeQuery.setParameter(4, entity.getPhoneNumber());
            nativeQuery.setParameter(5, entity.getDateOfBirth());
            nativeQuery.setParameter(6, entity.getStreet());
            nativeQuery.setParameter(7, entity.getNotes());
            nativeQuery.setParameter(8, entity.getCity());
            nativeQuery.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Could not save", e);
        }

    }

    @Override
    public void deleteById(Long id) {
        final String sql = "DELETE FROM customers AS c WHERE c.customer_id = :customerId";
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery(sql, Customer.class);
            query.setParameter("customerId", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Client delete error", e);

        }
    }

    @Override
    public void update(Customer entity, Long id) {
        final String sql = "UPDATE customers SET first_name = :firstName, last_name = :lastName, email = :email, " +
                "phone_number = :phoneNumber, date_of_birth = :dateOfBirth, street = :street, notes = :notes, city = :city " +
                "WHERE customer_id = :customerId";
        try {
            entityManager.getTransaction().begin();
            Query nativeQuery = entityManager.createNativeQuery(sql, Customer.class);
            nativeQuery.setParameter("customerId", id);
            nativeQuery.setParameter("firstName", entity.getFirstName());
            nativeQuery.setParameter("lastName", entity.getLastName());
            nativeQuery.setParameter("email", entity.getEmail());
            nativeQuery.setParameter("phoneNumber", entity.getPhoneNumber());
            nativeQuery.setParameter("dateOfBirth", entity.getDateOfBirth());
            nativeQuery.setParameter("street", entity.getStreet());
            nativeQuery.setParameter("notes", entity.getNotes());
            nativeQuery.setParameter("city", entity.getCity());
            nativeQuery.executeUpdate();
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().getRollbackOnly();
            throw new CustomerNotFoundException("Client update error", e);

        }

    }
}
