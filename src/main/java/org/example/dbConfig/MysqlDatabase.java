package org.example.dbConfig;

//import org.example.elements.TestData;
import org.example.PropertyReader;
import org.example.TestData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private static final Database INSTANCE = new Database();

    private static Connection mysqlConnection;
    private Database () {

        try {
            String mysqlConnectionUrl = PropertyReader.getConnectionUrlForMysql();
            this.mysqlConnection = DriverManager.getConnection(mysqlConnectionUrl,
                    PropertyReader.getUserForMysql(),
                    PropertyReader.getPasswordForMysql());
        } catch (SQLException e) {
            throw new RuntimeException("Create connection exception ==> " + e.getMessage());
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public static Connection getConnection() {
        return mysqlConnection;
    }

    public int executeUpdate(String query) {
        try(Statement statement = mysqlConnection.createStatement()) {
            return statement.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }

    public void executeResult(String query) {
        try(Statement statement = mysqlConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                TestData td = new TestData(resultSet.getInt("id"), resultSet.getString("name"));
                System.out.println("mysql ------>>>> " + td.toString());
            }
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }

    public void execute(String fileName) {
        try(Statement statement = mysqlConnection.createStatement()) {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            statement.execute(content);
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            mysqlConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



