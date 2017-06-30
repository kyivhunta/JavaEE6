package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;
import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.entity.Developer;
import com.serega.practice.module3.entity.Project;
import com.serega.practice.module3.entity.Skill;

import java.util.List;

public interface DeveloperController {

    boolean create(String name, String secondName, int salary, Project project, List<Skill> skills);

    boolean  update(int id, String newName, String newSecondName, int newSalary,Project project, List<Skill> skills) throws WrongId;

    Developer read(int id) throws WrongId;

    boolean  delete(int id) throws WrongId;

    List<Developer> getAllDevelopers();

}
