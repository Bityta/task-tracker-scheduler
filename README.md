# Task Tracker Scheduler

## Overview

This Spring Boot application, named "task-tracker-scheduler", serves as a scheduler for task tracking within a larger system. It is configured to run with different profiles depending on the environment: `ide` for local development and `prod` for production.

The main purpose of this service is to iterate through all users once a day and generate reports for them regarding their tasks and any changes within the last 24 hours.

- For users who have unfinished tasks by the end of the day, an email is sent stating "You have N unfinished tasks." The email body contains the titles of these tasks.
- For users who have completed 1 or more tasks in the last 24 hours, an email is sent stating "Today, you completed N tasks." The email body contains the titles of the completed tasks.
- For users who meet both conditions above, an email is sent with both lists: unfinished and completed tasks within the last 24 hours.

## Modules

### 1. Spring Scheduler
Spring Scheduler uses a cron expression to trigger a method every midnight. This method reads the list of users, analyzes their tasks, and generates emails for sending.

### 2. Spring AMQP
Spring AMQP is responsible for packaging the generated emails into model classes and sending them to a RabbitMQ queue for further processing.

## Getting Started

Before starting the `task-tracker-scheduler` microservice, ensure that the `task-tracker-email-sender` microservice is up and running, as it is required for proper functionality.

You can find the `task-tracker-email-sender` microservice [here](https://github.com/Bityta/task-tracker-email-sender).


To run the application locally using Docker Compose, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd task-tracker-scheduler`
3. Configure your desired profile by uncommenting the corresponding section in the application.yml file.
4. Build the Docker image: `docker-compose build`
5. Run the Docker container: `docker-compose up`

This will build the Docker image for the application and start the container.

Once the container is up and running, the application will be accessible at `http://localhost:8083`.

## Configuration

### Profiles

- **ide**: This profile is used for local development. It is configured to run on port `8083` with an PostgreSQL database. The database URL is `jdbc:postgresql://localhost:15432/task-tracker-backend`, and the username and password are both set to `admin`.
  
- **prod**: This profile is used for production deployment. It runs on port `8083` as well but connects to a PostgreSQL database. You will need to provide the database URL, username, and password.

To select a profile, go to application.yml and change the spring:
```yaml
application:
    profiles:
      active: your-profile
  ```
Replace your-profile with an ide or prod

Here's an example configuration:

```yaml
spring:
  config:
    activate:
      on-profile: prod

  datasource:
    url: jdbc:postgresql://your-database-url:5432/task-tracker-backend
    username: your-username
    password: your-password
```
Replace your-database-url, your-username, and your-password with the actual URL, username, and password for your PostgreSQL database.

### Spring Scheduler
- Cron expression: This can be configured in the `application.yml` file to adjust the schedule of the method invocation.


## Dependencies
- Spring Boot
- Spring Scheduler
- Spring Data JPA
- Spring Cloud Eureka Client

## Contributing
Contributions are welcome! Feel free to open issues or pull requests.


