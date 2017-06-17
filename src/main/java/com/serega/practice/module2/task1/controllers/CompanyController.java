package com.serega.practice.module2.task1.controllers;


import com.serega.practice.module2.task1.entity.Company;
import com.serega.practice.module2.task1.exceptions.WrongId;

public interface CompanyController {


    boolean create(String name);

    Company read(int id) throws WrongId;

    boolean updade(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    void showAllCompanies();


}
