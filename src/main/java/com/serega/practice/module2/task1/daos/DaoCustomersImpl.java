package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Customer;
import com.serega.practice.module2.task1.entity.Project;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCustomersImpl extends ConnectingToDB implements DaoCustomers {


    public DaoCustomersImpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String name, String secondName) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer(name, secondName) VALUES (?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, secondName);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Customer read(int id) {

        Customer customer = new Customer();
        ArrayList<Project> projectList = null;

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT customer.idCustomer,customer.name,secondName, GROUP_CONCAT(project.name SEPARATOR',') AS  projects\n" +
                            "FROM customer\n" +
                            "LEFT JOIN project ON customer.idCustomer=project.idCustomer WHERE customer.idCustomer = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer.setId(resultSet.getInt("idCustomer"));
                customer.setName(resultSet.getString("name"));
                customer.setSecondName(resultSet.getString("secondName"));

                String projects = resultSet.getString("projects");
                if (projects != null) {
                    projectList = new ArrayList<>();
                    for (String proj : projects.split(",")) {
                        Project project = new Project();
                        project.setName(proj);

                        projectList.add(project);

                    }
                    customer.setProjects(projectList);
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void updade(int id, String newName, String newSecondName) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET name = ?, secondName = ? WHERE idCustomer = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newSecondName);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE idCustomer = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Customer> getAll() {

        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT customer.idCustomer,customer.name,secondName,GROUP_CONCAT(project.name SEPARATOR',') AS projects FROM  customer " +
                    "LEFT JOIN project ON customer.idCustomer = project.idCustomer GROUP BY customer.idCustomer");

            while (resultSet.next()) {

                Customer customer = new Customer();
                ArrayList<Project> projects = new ArrayList<>();

                int idCustomer = resultSet.getInt("customer.idCustomer");
                String name = resultSet.getString("customer.name");
                String secondName = resultSet.getString("customer.secondName");
                String projectsName = resultSet.getString("projects");

                customer.setId(idCustomer);
                customer.setName(name);
                customer.setSecondName(secondName);

                if (projectsName != null) {

                    for (String proj : projectsName.split(",")) {
                        Project project = new Project();
                        project.setName(proj);
                        projects.add(project);

                        customer.setProjects(projects);
                    }
                }

                customers.add(customer);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
