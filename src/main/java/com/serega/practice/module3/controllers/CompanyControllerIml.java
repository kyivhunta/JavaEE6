package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.dao.CommonDao;
import com.serega.practice.module3.entity.Company;

import java.util.List;

public class CompanyControllerIml implements CompanyController {

    private CommonDao<Company, Integer> companyDao;

    public CompanyControllerIml(CommonDao<Company, Integer> companyDao) {
        this.companyDao = companyDao;
    }

    public boolean create(String name) {

        Company company = new Company();
        company.setName(name);

        companyDao.create(company);

        return true;
    }

    public Company read(int id) throws WrongId {


        return companyDao.read(id);

    }

    public boolean updade(int id, String newName) throws WrongId {
        if (companyDao.getAll().stream().noneMatch(company -> company.getIdCompany() == id))
            throw new WrongId("Компании с таким ID не существует");

        Company company = new Company();
        company.setIdCompany(id);
        company.setName(newName);

        companyDao.update(company);

        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (companyDao.getAll().stream().noneMatch(company -> company.getIdCompany() == id))
            throw new WrongId("Компании с таким ID не существует");

        Company company = read(id);

        companyDao.delete(company);

        return true;
    }

    public List<Company> getAllCompanies() {
        return companyDao.getAll();
    }
}
