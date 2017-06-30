package com.serega.practice.module3.api;

import com.serega.practice.module2.task1.exceptions.SomeNameNotFound;
import com.serega.practice.module2.task1.exceptions.ThisSkillAlreadyExisted;
import com.serega.practice.module3.controllers.*;
import com.serega.practice.module3.entity.*;
import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;
import com.serega.practice.module2.task1.exceptions.WrongId;

import java.util.List;

public class Application implements API {

    private SkillsController skillsController;
    private DeveloperController developerController;
    private CustomerController customerController;
    private CompanyController companyController;
    private ProjectController projectController;

    public Application(SkillsController skillsController, DeveloperController developerController, CustomerController customerController, CompanyController companyController, ProjectController projectController) {
        this.skillsController = skillsController;
        this.developerController = developerController;
        this.customerController = customerController;
        this.companyController = companyController;
        this.projectController = projectController;
    }

    @Override
    public boolean createDeveloper(String name, String secondName, int salary, Project project, List<Skill> skills) throws SomeSkillsDontExist {
        return developerController.create(name, secondName, salary, project, skills);
    }

    @Override
    public boolean updateDeveloper(int id, String newName, String newSecondName, int newSalary, Project project, List<Skill> skills) throws WrongId {
        return developerController.update(id, newName, newSecondName, newSalary, project, skills);
    }

    @Override
    public Developer readDeveloper(int id) throws WrongId {
        return developerController.read(id);
    }

    @Override
    public boolean deleteDeveloper(int id) throws WrongId {
        return developerController.delete(id);
    }

    @Override
    public void showAllDevelopers() {
        developerController.getAllDevelopers().forEach(System.out::println);
    }

    @Override
    public boolean createSkill(String nameSkill) throws ThisSkillAlreadyExisted {
        return skillsController.create(nameSkill);
    }

    @Override
    public Skill readSkill(int id) throws WrongId {
        return skillsController.read(id);
    }

    @Override
    public boolean updateSkill(int id, String newName) throws WrongId {
        return skillsController.update(id, newName);
    }

    @Override
    public boolean deleteSkill(int id) throws WrongId {
        return skillsController.delete(id);
    }

    @Override
    public void ShowAllSkills() {
        skillsController.getAllSkills().forEach(System.out::println);
    }

    @Override
    public boolean createCustomer(String name, String secondName) {
        return customerController.create(name, secondName);
    }

    @Override
    public Customer readCustomer(int id) throws WrongId {
        return customerController.read(id);
    }

    @Override
    public boolean updateCustomer(int id, String newName, String newSecondName) throws WrongId {
        return customerController.updade(id, newName, newSecondName);
    }

    @Override
    public boolean deleteCustomer(int id) throws WrongId {
        return customerController.delete(id);
    }

    @Override
    public void showAllCustomers() {
        customerController.getAllCustomers().forEach(System.out::println);
    }

    @Override
    public boolean createCompany(String name) {
        return companyController.create(name);
    }

    @Override
    public Company readCompany(int id) throws WrongId {
        return companyController.read(id);
    }

    @Override
    public boolean updateCompany(int id, String newName) throws WrongId {
        return companyController.updade(id, newName);
    }

    @Override
    public boolean deleteCompany(int id) throws WrongId {
        return companyController.delete(id);
    }

    @Override
    public void showAllCompanies() {
        companyController.getAllCompanies().forEach(System.out::println);
    }

    @Override
    public boolean createProject(String name, int cost, Company company, Customer customer) throws WrongId {
        return projectController.create(name, cost, company, customer);
    }

    @Override
    public Project readProject(int id) throws WrongId {
        return projectController.read(id);
    }

    @Override
    public boolean updateProject(int id, String newName, int newCost) throws WrongId {
        return projectController.update(id, newName, newCost);
    }

    @Override
    public boolean deleteProject(int id) throws WrongId {
        return projectController.delete(id);
    }

    @Override
    public void showAllProjects() {
        projectController.getAllProject().forEach(System.out::println);
    }

    @Override
    public Project findProjectByName(String nameProject) throws SomeNameNotFound {

        Project project1 = projectController.getAllProject().stream()
                .filter(project -> project.getName().equalsIgnoreCase(nameProject)).findFirst().orElse(null);

        if (project1 == null) throw new SomeNameNotFound("Компании с именем " + nameProject + " не найдено!");

        return project1;
    }

    @Override
    public Skill findSkillByName(String skillName) throws SomeNameNotFound {

        Skill skill = skillsController.getAllSkills().stream()
                .filter(project -> project.getSkill().equalsIgnoreCase(skillName)).findFirst().orElse(null);

        if (skill == null) throw new SomeNameNotFound("Навыка с именем " + skillName + " не найдено!");

        return skill;
    }

    public void showAllSkill() {
        skillsController.getAllSkills().forEach(System.out::println);
    }
}
