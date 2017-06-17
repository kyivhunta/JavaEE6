package com.serega.practice.module2.task1.startprogram;

import com.serega.practice.module2.task1.api.API;
import com.serega.practice.module2.task1.api.Application;
import com.serega.practice.module2.task1.consolehelper.*;
import com.serega.practice.module2.task1.controllers.*;
import com.serega.practice.module2.task1.daos.*;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setInitialSize(5);
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/homework?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=UTC");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("serenya23");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        DaoDeveloper daoDeveloper = new DaoDeveloperImpl(basicDataSource);
        DaoSkills daoSkills = new DaoSkillsImpl(basicDataSource);
        DaoCustomers daoCustomers = new DaoCustomersImpl(basicDataSource);
        DaoCompanies daoCompanies = new DaoCompaniesImpl(basicDataSource);
        DaoProject daoProject = new DaoProjectImpl(basicDataSource);

        DeveloperController developerController = new DeveloperControllerImpl(daoDeveloper);
        SkillsController skillsController = new SkillsControllerImpl(daoSkills);
        CustomerController customerController = new CustomerControllerImpl(daoCustomers);
        CompanyController companyController = new CompanyControllerImpl(daoCompanies);
        ProjectController projectController = new ProjectControllerImpl(daoProject);

        API api = new Application(skillsController,
                developerController,
                customerController,
                companyController,
                projectController);

        Mainconsole mainconsole = new Mainconsole(bufferedReader,
                new ConsoleDeveloper(bufferedReader, api),
                new ConsoleSkill(bufferedReader, api),
                new ConsoleCompany(bufferedReader, api),
                new ConsoleCustomer(bufferedReader, api),
                new ConsoleProject(bufferedReader, api));

        mainconsole.chooseOperation();

        bufferedReader.close();


    }
}
