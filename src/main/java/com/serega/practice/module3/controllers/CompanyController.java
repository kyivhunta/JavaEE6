package com.serega.practice.module3.controllers;


import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.entity.Company;

import java.util.List;

public interface CompanyController {


    boolean create(String name);

    Company read(int id) throws WrongId;

    boolean updade(int id, String newName) throws WrongId;

    boolean delete(int id) throws WrongId;

    List<Company> getAllCompanies();


}
