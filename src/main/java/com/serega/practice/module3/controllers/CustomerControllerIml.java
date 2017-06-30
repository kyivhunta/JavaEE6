package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.dao.CommonDao;
import com.serega.practice.module3.entity.Customer;

import java.util.List;

public class CustomerControllerIml implements CustomerController {

    private CommonDao<Customer, Integer> customerDao;

    public CustomerControllerIml(CommonDao<Customer, Integer> customerDao) {
        this.customerDao = customerDao;
    }

    public boolean create(String name, String secondName) {

        Customer customer = new Customer();
        customer.setName(name);
        customer.setSecondName(secondName);

        customerDao.create(customer);

        return true;
    }

    public Customer read(int id) throws WrongId {
        if (customerDao.getAll().stream().noneMatch(customer -> customer.getIdCustomer() == id))
            throw new WrongId("Заказчика с таким ID не существует");

        return customerDao.read(id);
    }

    public boolean updade(int id, String newName, String newSecondName) throws WrongId {
        if (customerDao.getAll().stream().noneMatch(customer -> customer.getIdCustomer() == id))
            throw new WrongId("Заказчика с таким ID не существует");

        Customer customer = new Customer();
        customer.setIdCustomer(id);
        customer.setName(newName);
        customer.setSecondName(newSecondName);

        customerDao.update(customer);

        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (customerDao.getAll().stream().noneMatch(customer -> customer.getIdCustomer() == id))
            throw new WrongId("Заказчика с таким ID не существует");

        Customer customer = read(id);

        customerDao.delete(customer);

        return true;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAll();
    }
}
