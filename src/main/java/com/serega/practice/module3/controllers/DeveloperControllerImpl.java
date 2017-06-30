package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.dao.CommonDao;
import com.serega.practice.module3.entity.Developer;
import com.serega.practice.module3.entity.Project;
import com.serega.practice.module3.entity.Skill;

import java.util.List;

public class DeveloperControllerImpl implements DeveloperController {

    CommonDao<Developer, Integer> developerDao;

    public DeveloperControllerImpl(CommonDao<Developer, Integer> commonDaoImpl) {
        this.developerDao = commonDaoImpl;
    }

    public boolean create(String name, String secondName, int salary, Project project, List<Skill> skills) {
        Developer developer = new Developer();
        developer.setSalary(salary);
        developer.setName(name);
        developer.setSecondName(secondName);
        developer.setProject(project);
        developer.setSkills(skills);

        developerDao.create(developer);

        return true;
    }

    public boolean update(int id, String newName, String newSecondName, int newSalary, Project project, List<Skill> skills) throws WrongId {
        if (developerDao.getAll().stream().noneMatch(developer -> developer.getId() == id))
            throw new WrongId("Разработчика с таким ID не существует!");

        Developer developer = new Developer();
        developer.setId(id);
        developer.setSalary(newSalary);
        developer.setName(newName);
        developer.setSecondName(newSecondName);
        developer.setSkills(skills);
        developer.setProject(project);

        developerDao.update(developer);

        return true;
    }

    public Developer read(int id) throws WrongId {
        if (developerDao.getAll().stream().noneMatch(developer -> developer.getId() == id))
            throw new WrongId("Разработчика с таким ID не существует!");

        return developerDao.read(id);
    }

    public boolean delete(int id) throws WrongId {
        if (developerDao.getAll().stream().noneMatch(developer -> developer.getId() == id))
            throw new WrongId("Разработчика с таким ID не существует!");

        Developer developer = developerDao.read(id);

        developerDao.delete(developer);

        return true;
    }

    public List<Developer> getAllDevelopers() {
        return developerDao.getAll();
    }
}
