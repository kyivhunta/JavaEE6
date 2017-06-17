package com.serega.practice.module2.task1.controllers;


import com.serega.practice.module2.task1.daos.DaoCompanies;
import com.serega.practice.module2.task1.entity.Company;
import com.serega.practice.module2.task1.entity.Customer;
import com.serega.practice.module2.task1.exceptions.WrongId;

public class CompanyControllerImpl implements CompanyController {

    DaoCompanies daoCompanies;

    public CompanyControllerImpl(DaoCompanies daoCompanies) {
        this.daoCompanies = daoCompanies;
    }

    public boolean create(String name) {
        daoCompanies.create(name);
        return true;
    }

    public Company read(int id) throws WrongId {
        if (daoCompanies.getAll().stream().map(Company::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод!");
        return daoCompanies.read(id);
    }

    public boolean updade(int id, String newName) throws WrongId {
        if (daoCompanies.getAll().stream().map(Company::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод!");
        daoCompanies.update(id, newName);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (daoCompanies.getAll().stream().map(Company::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Компании с таким ID не существует, повторите ввод!");
        daoCompanies.delete(id);
        return true;
    }

    public void showAllCompanies() {
        daoCompanies.getAll().forEach(System.out::println);
    }
}
