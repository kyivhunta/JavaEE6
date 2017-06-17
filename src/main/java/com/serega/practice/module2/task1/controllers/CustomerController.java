package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.entity.Customer;
import com.serega.practice.module2.task1.exceptions.WrongId;


public interface CustomerController {

    boolean create(String name, String secondName);

    Customer read(int id) throws WrongId;

    boolean updade(int id, String newName, String newSecondName) throws WrongId;

    boolean delete(int id) throws WrongId;

    void showAllCustomers();

}
