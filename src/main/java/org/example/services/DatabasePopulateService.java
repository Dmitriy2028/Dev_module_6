package org.example;

import org.example.dbConfig.MysqlDatabase;

//import java.sql.Connection;

public class DatabasePopulateService {
    public static void main(String[] args) {
        //Connection connection = MysqlDatabase.getConnection();
        MysqlDatabase mysqlDatabase = MysqlDatabase.getInstance();
        mysqlDatabase.executeUpdate("src/main/resources/sql/populate_db.sql");
    }
}
