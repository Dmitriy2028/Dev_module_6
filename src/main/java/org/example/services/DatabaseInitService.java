package org.example;

import org.example.dbConfig.MysqlDatabase;

//import java.sql.Connection;

public class DatabaseInitService {
    public static void main(String[] args) {
        //Connection connection = MysqlDatabase.getConnection();
        MysqlDatabase mysqlDatabase = MysqlDatabase.getInstance();
        mysqlDatabase.executeUpdate("src/main/resources/sql/init_db.sql");
    }
}
