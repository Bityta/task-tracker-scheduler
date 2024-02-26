package ru.app.tasktrackerscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main class to start the Task Tracker Scheduler application.
 */
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class TaskTrackerSchedulerApplication {

    /**
     * The main method to start the Spring Boot application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerSchedulerApplication.class, args);
    }
}
