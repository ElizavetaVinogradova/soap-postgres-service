package com.example.service.repository;

import com.example.service.config.SourceDatabaseConfiguration;
import com.example.service.exception.TransferException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.*;

@Component
public class TaskRepository {

  private final static Logger logger = Logger.getLogger(TaskRepository.class);

    @Autowired
    private SourceDatabaseConfiguration configuration;

    public String getStringFromResultSet(String query) throws TransferException {
        System.out.println("PROPERTIES Postgres: " + configuration.getDb_url() + ", " +  configuration.getDb_username() + ", " + configuration.getDb_password());
        try (Connection conn = DriverManager.getConnection(
                configuration.getDb_url(),
                configuration.getDb_username(),
                configuration.getDb_password());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)
        ){
            String value = "";
            try{
                while (rs.next()) {
                    value = rs.getString(1);
                }
            }catch (SQLException e){
                logger.error("Cannot retrieve value from ResultSet");
            }
            return value;
        }catch (SQLException e) {
            logger.error("Failed to connect to PG", e);
            throw new TransferException("Failed to connect to PG", e);
        }
    }



    public String getProjectID(int taskId){
        String projectIDromTasks = "";
        String idFromProjects = "";
        try {
            String queryTask = "SELECT  \"ProjectID\" FROM \"public\".\"Task\" WHERE \"InternalID\" = " + taskId + " and \"ProjectVersion\" = 1"; // returns '56371'
            projectIDromTasks = getStringFromResultSet(queryTask);
            String queryProject = "SELECT \"ID\" FROM \"public\".\"Project\" WHERE \"InternalID\" = " + projectIDromTasks + " and \"Version\" = 1"; // Returns '17023'
            idFromProjects = getStringFromResultSet(queryProject);
        }catch (TransferException e) {
            logger.error("Failed to get field from PG", e);
        }
        return idFromProjects;
    }


    public String findTask(int taskId) {
        logger.info("find Task started");
        String valueToReturn = "";
        String projectID = getProjectID(taskId);
        Assert.notNull(taskId, "The task's taskId must not be null");
        return valueToReturn;
    }
}

