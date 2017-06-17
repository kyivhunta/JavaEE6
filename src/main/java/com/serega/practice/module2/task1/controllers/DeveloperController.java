package com.serega.practice.module2.task1.controllers;

import com.serega.practice.module2.task1.entity.Developer;
import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.util.List;

public interface DeveloperController {

    boolean create (String name, String secondName, int salary,String projectName, List<String> skills) throws SomeSkillsDontExist;

    boolean  update(int id, String newName, String newSecondName, int newSalary, List<String> skills) throws WrongId;

    Developer read(int id) throws WrongId;

    boolean  delete (int id) throws WrongId;

    void showAllDevelopers();

}
