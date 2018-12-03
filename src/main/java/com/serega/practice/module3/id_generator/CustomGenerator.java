package com.serega.practice.module3.id_generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomGenerator implements IdentifierGenerator {

    private static final int BEGINNER_DIGIT = 100;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        int newId = 0;
        try {
            PreparedStatement preparedStatement = sharedSessionContractImplementor.getJdbcConnectionAccess().obtainConnection().prepareStatement("SELECT MAX(hibernate.skill.idSkill) as cur_id FROM hibernate.skill");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                int cur_id = resultSet.getInt("cur_id");
                cur_id = Integer.valueOf(String.valueOf(cur_id).substring(3));
                newId = Integer.valueOf(String.valueOf(BEGINNER_DIGIT).concat(String.valueOf(++cur_id)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newId;
    }
}
