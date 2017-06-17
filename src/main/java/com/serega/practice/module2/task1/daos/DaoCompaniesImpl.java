package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Company;
import com.serega.practice.module2.task1.entity.Project;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCompaniesImpl extends ConnectingToDB implements DaoCompanies {

    public DaoCompaniesImpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String name) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO company (name) VALUES (?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company read(int id) {

        Company company = new Company();


        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT company.idCompany,company.name, GROUP_CONCAT(project.name  SEPARATOR',') AS projects" +
                    " FROM company JOIN project USING (idCompany) WHERE idCompany = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ArrayList<Project> projects = null;

                company.setName(resultSet.getString("company.name"));
                company.setId(resultSet.getInt("company.idCompany"));

                String projs = resultSet.getString("projects");
                if (projs != null) {
                    projects = new ArrayList<>();
                    for (String s : projs.split(",")) {
                        Project project = new Project();

                        project.setName(s);
                        projects.add(project);

                    }
                }

                company.setProjects(projects);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }

    public void update(int id, String newName) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE company SET name = ? WHERE idCompany = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM company WHERE idCompany = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Company> getAll() {

        List<Company> companies = new ArrayList<>();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT company.idCompany,company.name, GROUP_CONCAT(project.name  SEPARATOR',') AS projects " +
                    "FROM company LEFT JOIN project USING (idCompany) GROUP BY idCompany");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Company company = new Company();
                ArrayList<Project> projects = new ArrayList<>();

                company.setId(resultSet.getInt("company.idCompany"));
                company.setName(resultSet.getString("company.name"));

                String projectsName = resultSet.getString("projects");
                if (projectsName != null) {
                    for (String name : projectsName.split(",")) {
                        Project project = new Project();
                        project.setName(name);
                        projects.add(project);

                        company.setProjects(projects);
                    }
                }


                companies.add(company);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
