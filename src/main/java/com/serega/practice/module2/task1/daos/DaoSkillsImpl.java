package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Skill;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  CRUD oprerations + getAll
 */


public class DaoSkillsImpl  extends ConnectingToDB implements DaoSkills {


    public DaoSkillsImpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String nameSkill) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO skill(skill) VALUE (?)");
            preparedStatement.setString(1, nameSkill);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Skill read(int id) {
        Skill skill = new Skill();
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM skill WHERE idSkill = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idSkill = resultSet.getInt("idSkill");
                String nameSkill = resultSet.getString("skill");

                skill.setId(idSkill);
                skill.setName(nameSkill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skill;
    }

    public void update(int id, String newName) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE skill SET skill = ? WHERE idSkill = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM skill WHERE idSkill = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM skill");

            while (resultSet.next()) {
                int idSkill = resultSet.getInt("idSkill");
                String skill1 = resultSet.getString("skill");

                Skill skill = new Skill();
                skill.setId(idSkill);
                skill.setName(skill1);

                skills.add(skill);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skills;
    }
}
