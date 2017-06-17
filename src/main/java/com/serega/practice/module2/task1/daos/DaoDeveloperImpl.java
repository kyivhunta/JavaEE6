package com.serega.practice.module2.task1.daos;

import com.serega.practice.module2.task1.entity.Developer;
import com.serega.practice.module2.task1.entity.Project;
import com.serega.practice.module2.task1.entity.Skill;
import com.serega.practice.module2.task1.exceptions.SomeSkillsDontExist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD oprerations and getAll
 */

public class DaoDeveloperImpl extends ConnectingToDB implements DaoDeveloper {

    public DaoDeveloperImpl(DataSource dataSource) {
        super(dataSource);
    }

    public void create(String name, String secondName, int salary, String projectName, List<String> skills) throws SomeSkillsDontExist {
        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idProject FROM project WHERE name = ?");
            preparedStatement.setString(1, projectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            int idProject = 0;
            while (resultSet.next()) {
                idProject = resultSet.getInt(1);
            }

            preparedStatement = connection.prepareStatement("INSERT INTO developer (name, secondName, salary, idProject) VALUES (?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, secondName);
            preparedStatement.setInt(3, salary);
            preparedStatement.setInt(4, idProject);
            preparedStatement.execute();


            preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM  developer LIMIT 1");
            ResultSet resultSet0 = preparedStatement.executeQuery();
            int lastIdDeveloper = 0;
            while (resultSet0.next()) {

                lastIdDeveloper = resultSet0.getInt(1);
            }

            String dontExistSkills = "";
            for (String oneSkill : skills) {
                int skillID = 0;

                Statement statement = connection.createStatement();
                ResultSet resultSet1 = statement.executeQuery("SELECT idSkill FROM skill WHERE skill ='" + oneSkill + "'");

                while (resultSet1.next()) {
                    skillID = resultSet1.getInt(1);

                }

                statement = connection.createStatement();
                try {
                    statement.execute("INSERT INTO developer_has_skill VALUES (" + lastIdDeveloper + "," + skillID + ")");
                } catch (SQLIntegrityConstraintViolationException s) {
                    dontExistSkills += oneSkill + ", ";
                }

            }

            if (dontExistSkills.length() > 0)
                throw new SomeSkillsDontExist("[" + dontExistSkills + "] - этих навыков нет в списке \"Skills\"");
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, String newName, String newSecondName, int newSalary, List<String> skills) {
        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE developer SET name =?, secondName=?, salary=? WHERE  id = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newSecondName);
            preparedStatement.setInt(3, newSalary);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();


            preparedStatement = connection.prepareStatement("DELETE FROM developer_has_skill WHERE Developer_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            for (String skill : skills) {
                int idSkill = 0;

                preparedStatement = connection.prepareStatement("SELECT idSkill FROM skill WHERE skill = ?");
                preparedStatement.setString(1, skill);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    idSkill = resultSet.getInt(1);
                }

                preparedStatement = connection.prepareStatement("INSERT INTO developer_has_skill VALUES (?,?)");
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, idSkill);
                preparedStatement.execute();

            }


            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Developer read(int id) {

        Developer developer = new Developer();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT developer.id, developer.name, developer.salary, developer.secondName, project.name ,GROUP_CONCAT(skill.skill SEPARATOR ', ') AS skills  FROM developer JOIN developer_has_skill ON developer.id = developer_has_skill.Developer_id JOIN skill ON developer_has_skill.Skill_idSkill = skill.idSkill LEFT JOIN project USING (idProject)  WHERE developer.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int idRes = resultSet.getInt("id");
                String name = resultSet.getString("developer.name");
                String secondName = resultSet.getString("secondName");
                int salary = resultSet.getInt("salary");
                String projName = resultSet.getString("project.name");
                Project project = new Project();
                project.setName(projName);

                developer.setId(idRes);
                developer.setName(name);
                developer.setSecondName(secondName);
                developer.setSalary(salary);
                developer.setProject(project);


                String skills = resultSet.getString("skills");
                String[] split = skills.split(",");

                for (String s : split) {
                    Skill skill = new Skill();
                    skill.setName(s);
                    developer.getSkills().add(skill);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    public void delete(int id) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM developer WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Developer> getAll() {

        List<Developer> developers = new ArrayList<>();

        try (Connection connection = getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT developer.id,developer.name,secondName,salary,project.name, GROUP_CONCAT(skill.skill SEPARATOR ', ') AS skills" +
                    " FROM developer LEFT JOIN developer_has_skill ON developer.id = developer_has_skill.Developer_id LEFT JOIN skill ON developer_has_skill.Skill_idSkill = skill.idSkill LEFT JOIN project USING (idProject) GROUP BY id");

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("developer.name");
                String secondName = resultSet.getString("secondName");
                int salary = resultSet.getInt("salary");
                String nameProj = resultSet.getString("project.name");
                String skills = resultSet.getString("skills");

                Developer developer = new Developer();

                Project project = new Project();
                project.setName(nameProj);
                developer.setProject(project);
                developer.setId(id);
                developer.setName(name);
                developer.setSecondName(secondName);
                developer.setSalary(salary);
                developer.getProject().setName(nameProj);
                if (skills == null) developer.setSkills(null);
                else {
                    String[] splitSkills = skills.split(",");
                    for (String skill : splitSkills) {
                        Skill skill1 = new Skill();
                        skill1.setName(skill);
                        developer.getSkills().add(skill1);
                    }
                }
                developers.add(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }
}
