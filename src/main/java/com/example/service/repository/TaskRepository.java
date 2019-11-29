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
        logger.debug("Connection to Postgres: " + configuration.getDb_url() + ", " +  configuration.getDb_username() + ", " + configuration.getDb_password());

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
            logger.error("Failed to connect to Postgres", e);
            throw new TransferException("Failed to connect to Postgres", e);
        }
    }


    public String getProjectID(int taskId){
        logger.debug("Retrieving ProjectID from Postgres");
        String projectIDFromTasks = "";
        String idFromProjects = "";
        try {
            String queryTask = "SELECT \"ProjectID\" FROM \"homework\".\"Task\" WHERE \"InternalID\" = " + taskId + " and \"ProjectVersion\" = 1"; // returns '56371'
            projectIDFromTasks = getStringFromResultSet(queryTask);
            String queryProject = "SELECT \"ID\" FROM \"homework\".\"Project\" WHERE \"InternalID\" = " + projectIDFromTasks + " and \"Version\" = 1"; // Returns '17023'
            idFromProjects = getStringFromResultSet(queryProject);
        }catch (TransferException e) {
            logger.error("Failed to get field from PG", e);
        }
        return idFromProjects;
    }


    public String findTask(int taskId) {
        logger.info("find Task started");
        Assert.notNull(taskId, "The task's taskId must not be null");
        return getProjectID(taskId);
    }
}

