package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Developer;
import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;

import java.util.ArrayList;
import java.util.List;

public interface DaoDeveloper {

    void create(String name, String secondName, int salary, String projectName, List<String> skills) throws SomeSkillsDontExist;

    void update(int id, String newName, String newSecondName, int newSalary, List<String> skills);

    Developer read(int id);

    void delete(int id);

    List<Developer> getAll();

}
