package com.github.angel.repository;

import com.github.angel.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends GenericRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByFirstName(String firstName);
    List<Customer> findByCityContaining(String city);
    List<Customer> findByCityAndStreet(String city, String street);
    List<Customer> findAllByOrderByLastNameAsc();
    List<Customer> findByNotesContaining(String keyword);
    List<Customer> findByDateOfBirthBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Customer> findAllByOrderByFirstNameAsc();
    String fullName (String name);





}
