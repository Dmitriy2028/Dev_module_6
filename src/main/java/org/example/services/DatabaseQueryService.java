package org.example;

import org.example.dbConfig.MysqlDatabase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DatabaseQueryService {
    static MysqlDatabase mysqlDatabase = MysqlDatabase.getInstance();

    public static void main(String[] args) throws SQLException {
        findMaxProjectsClient("src/main/resources/sql/find_max_projects_client.sql");
        System.out.println();
        findMaxSalaryWorker("src/main/resources/sql/find_max_salary_worker.sql");
        System.out.println();
        findYoungestEldestWorkers("src/main/resources/sql/find_youngest_eldest_workers.sql");
        System.out.println();
        findLongestProject("src/main/resources/sql/find_longest_project.sql");
    }

    public static void findMaxProjectsClient(String fileName) throws SQLException {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            mysqlDatabase.executeResult(query, "client_name", "project_count");
        }
    }

    public static void findMaxSalaryWorker(String fileName) throws SQLException {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            mysqlDatabase.executeResult(query, "name", "salary");
        }
    }

    public static void findYoungestEldestWorkers(String fileName) throws SQLException {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            mysqlDatabase.executeResult(query, "name", "TYPE");
        }
    }

    public static void findLongestProject(String fileName) throws SQLException {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            mysqlDatabase.executeResult(query, "id", "month_count");
        }
    }



}
