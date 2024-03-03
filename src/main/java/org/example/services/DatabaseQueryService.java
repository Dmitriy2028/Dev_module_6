package org.example.services;

import org.example.dbConfig.MysqlDatabase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    static MysqlDatabase mysqlDatabase = MysqlDatabase.getInstance();

    public static void main(String[] args) throws SQLException {
        List<MaxProjectCountClient> maxProjectCountClients = findMaxProjectsClient();

        List<MaxSalaryWorker> maxSalaryWorker = findMaxSalaryWorker();

        List<YoungestEldestWorkers> youngestEldestWorkers =findYoungestEldestWorkers();

        List<LongestProject> longestProject =findLongestProject();
    }

    public static class MaxProjectCountClient {
        private String name;
        private int projectCount;

        public MaxProjectCountClient(String name, String projectCount) {
            this.name = name;
            this.projectCount = Integer.parseInt(projectCount);
        }
    }

    public static List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String fileName = "src/main/resources/sql/find_max_projects_client.sql";
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            for (MysqlDatabase.ExecuteResultObjects objects : mysqlDatabase.executeResult(query, "client_name", "project_count")) {
                result.add(new MaxProjectCountClient(objects.object1, objects.object2));
            }
        }
        return result;
    }

    public static class MaxSalaryWorker {
        private String name;
        private int salary;

        public MaxSalaryWorker(String name, String salary) {
            this.name = name;
            this.salary = Integer.parseInt(salary);
        }
    }

    public static List<MaxSalaryWorker> findMaxSalaryWorker() throws SQLException {
        List<MaxSalaryWorker> result = new ArrayList<>();
        String fileName = "src/main/resources/sql/find_max_salary_worker.sql";
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            for (MysqlDatabase.ExecuteResultObjects objects : mysqlDatabase.executeResult(query, "name", "salary")) {
                result.add(new MaxSalaryWorker(objects.object1, objects.object2));
            }
        }
        return result;
    }

    public static class YoungestEldestWorkers {
        private String name;
        private String type;

        public YoungestEldestWorkers(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    public static List<YoungestEldestWorkers> findYoungestEldestWorkers() throws SQLException {
        List<YoungestEldestWorkers> result = new ArrayList<>();
        String fileName = "src/main/resources/sql/find_youngest_eldest_workers.sql";
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            for (MysqlDatabase.ExecuteResultObjects objects : mysqlDatabase.executeResult(query, "name", "TYPE")) {
                result.add(new YoungestEldestWorkers(objects.object1, objects.object2));
            }
        }
        return result;
    }

    public static class LongestProject {
        private int id;
        private int monthCount;

        public LongestProject(String id, String monthCount) {
            this.id = Integer.parseInt(id);
            this.monthCount = Integer.parseInt(monthCount);
        }
    }

    public static List<LongestProject> findLongestProject() throws SQLException {
        List<LongestProject> result = new ArrayList<>();
        String fileName = "src/main/resources/sql/find_longest_project.sql";
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] queries = content.toString().split(";");
        for (String query : queries) {
            for (MysqlDatabase.ExecuteResultObjects objects : mysqlDatabase.executeResult(query, "id", "month_count")) {
                result.add(new LongestProject(objects.object1, objects.object2));
            }
        }
        return result;
    }
}
