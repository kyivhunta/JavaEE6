package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.daos.DaoProject;
import com.serega.practice.module2.task1.entity.Project;
import com.serega.practice.module2.task1.exceptions.WrongId;

public class ProjectControllerImpl implements ProjectController {

    private DaoProject daoProject;

    public ProjectControllerImpl(DaoProject daoProject) {
        this.daoProject = daoProject;
    }

    public boolean create(String name, int cost, int idCompany, int idCustomers) {
        daoProject.create(name, cost, idCompany, idCustomers);
        return true;
    }

    public Project read(int id) throws WrongId {
        if (daoProject.getAll().stream().map(Project::getId).noneMatch(integer -> id == integer))
            throw new WrongId("Проекта с таким id не существует, повторите ввод!");
        return daoProject.read(id);
    }

    public boolean update(int id, String newName, int newCost) throws WrongId {
        if (daoProject.getAll().stream().map(Project::getId).noneMatch(integer -> id == integer))
            throw new WrongId("Проекта с таким id не существует, повторите ввод!");

        daoProject.update(id, newName, newCost);
        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (daoProject.getAll().stream().map(Project::getId).noneMatch(integer -> id == integer))
            throw new WrongId("Проекта с таким id не существует, повторите ввод!");
        daoProject.delete(id);
        return true;
    }


    public void showAll() {
        daoProject.getAll().forEach(System.out::println);
    }
}
