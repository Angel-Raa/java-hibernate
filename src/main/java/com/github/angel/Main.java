package com.github.angel;


import com.github.angel.entity.Customer;
import com.github.angel.repository.CustomerRepository;
import com.github.angel.repository.impl.CustomerRepositoryImpl;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Angel ", "Aguero", "angel@example.com",
                "123-456-7890", LocalDateTime.now(),
                "123 Main St", null, "CA");

        CustomerRepository repository = new CustomerRepositoryImpl();

//        repository.findAll().stream().forEach(System.out::println);
        System.out.println("Hola Mundo");

//        repository.save(customer);
        //repository.deleteById(40L);

        Customer newCustomer = new Customer("Angel Rafael ", "Aguero Aquino", "angel@example.com",
                "123-456-7890", LocalDateTime.now(),
                "123 Main St", null, "CA");

//        repository.update(newCustomer, 41L);

        Customer customer1 = repository.findById(41L);
        System.out.println(customer1);



    }
}