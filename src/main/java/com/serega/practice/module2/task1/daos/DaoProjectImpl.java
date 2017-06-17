package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Company;
import com.serega.practice.module2.task1.entity.Customer;
import com.serega.practice.module2.task1.entity.Developer;
import com.serega.practice.module2.task1.entity.Project;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProjectImpl extends ConnectingToDB implements DaoProject {


    public DaoProjectImpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String nameProj, int cost, int idCompany, int idCustomer) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO project(name, cost, idCompany, idCustomer) VALUES (?,?,?,?)");
            preparedStatement.setString(1, nameProj);
            preparedStatement.setInt(2, cost);
            preparedStatement.setInt(3, idCompany);
            preparedStatement.setInt(4, idCustomer);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Project read(int id) {

        Project project = null;
        Company company = new Company();
        Customer customer = new Customer();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idProject,project.name,cost,company.name AS company,customer.name AS customerName, customer.secondName AS customerSecondName FROM project" +
                    " JOIN company USING (idCompany) JOIN customer USING (idCustomer) WHERE idProject = ?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt("idProject"));
                project.setName(resultSet.getString("project.name"));
                project.setCost(resultSet.getInt("cost"));

                customer.setName(resultSet.getString("customerName"));
                customer.setSecondName(resultSet.getString("customerSecondName"));
                project.setCustomer(customer);

                company.setName(resultSet.getString("company"));
                project.setCompany(company);

            }
            if (project != null) {
                ArrayList<Developer> developers = new ArrayList<>();

                preparedStatement = connection.prepareStatement("SELECT id, name, secondName FROM developer WHERE idProject = ?");
                preparedStatement.setInt(1, id);
                ResultSet resultSet1 = preparedStatement.executeQuery();

                while (resultSet1.next()) {
                    Developer developer = new Developer();
                    developer.setId(resultSet1.getInt("id"));
                    developer.setName(resultSet1.getString("name"));
                    developer.setSecondName(resultSet1.getString("secondName"));

                    developers.add(developer);
                }
                project.setDevelopers(developers);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }

    public void update(int id, String newName, int cost) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE project SET project.name = ?, cost = ? WHERE idProject = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, cost);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM project WHERE idProject = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Project> getAll() {

        List<Project> projects = new ArrayList<>();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT project.idProject,project.name,project.cost,company.name,customer.name,customer.secondName, GROUP_CONCAT(developer.secondName SEPARATOR ',') AS developers FROM  project JOIN company USING (idCompany) JOIN customer USING (idCustomer) LEFT JOIN developer ON (project.idProject=developer.idProject) GROUP BY idProject");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                Customer customer = new Customer();
                Company company = new Company();
                ArrayList<Developer> developers = new ArrayList<>();

                customer.setName(resultSet.getString("customer.name"));
                customer.setSecondName(resultSet.getString("customer.secondName"));

                company.setName(resultSet.getString("company.name"));

                String developersSecondName = resultSet.getString("developers");
                if (developersSecondName != null) {
                    for (String s : developersSecondName.split(",")) {
                        Developer developer = new Developer();
                        developer.setSecondName(s);

                        developers.add(developer);
                    }
                }

                project.setId(resultSet.getInt("idProject"));
                project.setName(resultSet.getString("project.name"));
                project.setCost(resultSet.getInt("cost"));
                project.setCustomer(customer);
                project.setCompany(company);
                project.setDevelopers(developers);

                projects.add(project);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
