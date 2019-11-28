package com.example.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class SourceDatabaseConfiguration {
    @Value("${db.url}")
    private String db_url;
    public String getDb_url() {
        return db_url;
    }
    public void setDb_url(String db_url) {
        this.db_url = db_url;
    }

    @Value("${db.username}")
    private String db_username;
    public String getDb_username() {
        return db_username;
    }
    public void setDb_username(String db_username) {
        this.db_username = db_username;
    }

    @Value("${db.password}")
    private String db_password;
    public String getDb_password() {
        return db_password;
    }
    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }
}
