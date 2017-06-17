package com.serega.practice.module2.task1.daos;


import com.serega.practice.module2.task1.entity.Project;

import java.util.List;

public interface DaoProject {

    void create(String nameProj, int cost, int idCompany, int idCustomer);

    Project read(int id);

    void update(int id, String newName, int cost);

    void delete(int id);

    List<Project> getAll();

}
