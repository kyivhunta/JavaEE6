package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.entity.Customer;

import java.util.List;


public interface CustomerController {

    boolean create(String name, String secondName);

    Customer read(int id) throws WrongId;

    boolean updade(int id, String newName, String newSecondName) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Customer> getAllCustomers();

}
