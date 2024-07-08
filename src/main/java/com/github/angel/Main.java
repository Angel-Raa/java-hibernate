package com.github.angel;


import com.github.angel.entity.Customer;
import com.github.angel.repository.CustomerRepository;
import com.github.angel.repository.impl.CustomerRepositoryImpl;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Minerva ", "Bael", "minerva123@example.com",
                "111-222-3333", LocalDateTime.now(),
                "123 Main St", null, "CA");

        CustomerRepository repository = new CustomerRepositoryImpl();

//        repository.findAll().stream().forEach(System.out::println);
        System.out.println("Hola Mundo");
        repository.save(customer);
        //repository.deleteById(40L);

        Customer newCustomer = new Customer("Angel Rafael ", "Aguero Aquino", "angel@example.com",
                "123-456-7890", LocalDateTime.now(),
                "123 Main St", null, "CA");

//        repository.update(newCustomer, 41L);

        //Customer customer1 = repository.findById(41L);
        //System.out.println(customer1);
//        Customer customer2 = repository.findByEmail("juan.perez@example.com");
//        System.out.println(customer2);
        LocalDateTime startDate = LocalDateTime.of(1990, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(1999, 1, 1, 0, 0);
//        var o = repository.findByDateOfBirthBetween(startDate, endDate);
//        o.stream().forEach(System.out::println);

//        var o = repository.findByCityAndStreet("Sabaneta", "Calle del Sur #474");
//        o.stream().forEach(System.out::println);

//        var o = repository.findByCityContaining("S");
//        o.stream().forEach(System.out::println);

//        var o  = repository.findByNotesContaining("P");
//        o.stream().forEach(System.out::println);

//        var o = repository.findAllByOrderByLastNameAsc();
//      o.stream().forEach(System.out::println);

        System.out.println("=========== Consulta con nombre y apellido conatenados");
//        List<String> fullName = repository.fullName();
//        fullName.stream().forEach(System.out::println);

        int count = repository.count();
        System.out.println(count);

        int max = repository.max();
        System.out.println(max);
        int min = repository.min();
        System.out.println(min);

        int sum = repository.sum();
        System.out.println(sum);

    }
}