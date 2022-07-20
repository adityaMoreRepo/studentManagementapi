package com.example.crud.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("clean")
@Configuration
public class DbClean {

    @Bean
    public FlywayMigrationStrategy clean(){
        //Here we gonna perform flyway goals to clean i.e. drop
        // all the db tables and rebuild it using migration goal
        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }

}
