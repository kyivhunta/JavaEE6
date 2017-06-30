package com.serega.practice.module3.controllers;

import com.serega.practice.module2.task1.exceptions.WrongId;
import com.serega.practice.module3.dao.CommonDao;
import com.serega.practice.module3.entity.Company;
import com.serega.practice.module3.entity.Customer;
import com.serega.practice.module3.entity.Project;

import java.util.List;

public class ProjectControllerImpl implements ProjectController {

    private CommonDao<Project, Integer> projectDao;

    public ProjectControllerImpl(CommonDao<Project, Integer> projectDao) {
        this.projectDao = projectDao;
    }

    public boolean create(String name, int cost, Company company, Customer customer) {

        Project project = new Project();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(company);
        project.setCustomer(customer);

        projectDao.create(project);

        return true;
    }

    public Project read(int id) throws WrongId {
        if (projectDao.getAll().stream().noneMatch(project -> project.getId() == id))
            throw new WrongId("Проекта с таким ID не существует!");

        return projectDao.read(id);
    }

    public boolean update(int id, String newName, int newCost) throws WrongId {
        if (projectDao.getAll().stream().noneMatch(project -> project.getId() == id))
            throw new WrongId("Проекта с таким ID не существует!");

        Project project = read(id);
        project.setId(id);
        project.setName(newName);
        project.setCost(newCost);

        projectDao.update(project);

        return true;
    }

    public boolean delete(int id) throws WrongId {
        if (projectDao.getAll().stream().noneMatch(project -> project.getId() == id))
            throw new WrongId("Проекта с таким ID не существует!");

        Project project = projectDao.read(id);

        projectDao.delete(project);

        return true;
    }

    public List<Project> getAllProject() {
        return projectDao.getAll();
    }
}
