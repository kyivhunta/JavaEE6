package com.serega.practice.module2.task1.daos;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectingToDB {

    private DataSource dataSource;

    public ConnectingToDB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
