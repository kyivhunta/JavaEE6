package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.daos.DaoDeveloper;
import com.serega.practice.module2.task1.entity.Developer;
import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.util.List;

public class DeveloperControllerImpl implements DeveloperController {


    private DaoDeveloper daoDeveloper;

    public DeveloperControllerImpl(DaoDeveloper daoDeveloper) {
        this.daoDeveloper = daoDeveloper;
    }

    public boolean create(String name, String secondName, int salary, String projectName, List<String> skills) throws SomeSkillsDontExist {
        daoDeveloper.create(name, secondName, salary, projectName, skills);
        return true;
    }

    public boolean update(int id, String newName, String newSecondName, int newSalary, List<String> skills) throws WrongId {
        if (daoDeveloper.getAll().stream().map(Developer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод!");
        daoDeveloper.update(id, newName, newSecondName, newSalary, skills);
        return true;
    }

    public Developer read(int id) throws WrongId {
        if (daoDeveloper.getAll().stream().map(Developer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод!");
        return daoDeveloper.read(id);
    }

    public boolean delete(int id) throws WrongId {
        if (daoDeveloper.getAll().stream().map(Developer::getId).noneMatch(integer -> integer == id))
            throw new WrongId("Разработчика с таким ID не существует, повторите ввод!");
        daoDeveloper.delete(id);
        return true;
    }

    public void showAllDevelopers() {
        daoDeveloper.getAll().forEach(System.out::println);
    }


}
