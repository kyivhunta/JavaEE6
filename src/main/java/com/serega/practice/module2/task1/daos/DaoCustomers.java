package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Customer;

import java.util.List;

public interface DaoCustomers {

    void create(String name, String secondName);

    Customer read(int id);

    void updade(int id, String newName, String newSecondName);

    void delete(int id);

    List<Customer> getAll();

}
