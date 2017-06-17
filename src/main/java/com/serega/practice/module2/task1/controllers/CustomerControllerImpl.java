package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.daos.DaoCustomers;
import com.serega.practice.module2.task1.entity.Customer;
import com.serega.practice.module2.task1.exceptions.WrongId;

public class CustomerControllerImpl implements CustomerController {

    private DaoCustomers daoCustomers;

    public CustomerControllerImpl(DaoCustomers daoCustomers) {
        this.daoCustomers = daoCustomers;
    }

    public boolean create(String name, String secondName) {
        daoCustomers.create(name, secondName);
        return true;
    }

    public Customer read(int id) throws WrongId {
        if (daoCustomers.getAll().stream().map(Customer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод!");
        return daoCustomers.read(id);
    }

    public boolean updade(int id, String newName, String newSecondName) throws WrongId {
        if (daoCustomers.getAll().stream().map(Customer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод!");
        daoCustomers.updade(id, newName, newSecondName);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (daoCustomers.getAll().stream().map(Customer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Заказчика с таким ID не существует, повторите ввод!");
        daoCustomers.delete(id);
        return true;
    }

    public void showAllCustomers() {
        daoCustomers.getAll().forEach(System.out::println);
    }
}
