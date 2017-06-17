package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Company;

import java.util.List;

public interface DaoCompanies {

    void create(String name);

    Company read(int id);

    void update(int id, String newName);

    void delete(int id);

    List<Company> getAll();

}
