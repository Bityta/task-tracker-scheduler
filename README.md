# Task Tracker Scheduler

This is a Spring Boot application designed as a task scheduler with two modules: Spring Scheduler and Spring AMQP.

## Overview
The main purpose of this service is to iterate through all users once a day and generate reports for them regarding their tasks and any changes within the last 24 hours.

- For users who have unfinished tasks by the end of the day, an email is sent stating "You have N unfinished tasks." The email body contains the titles of these tasks (up to a reasonable limit, e.g., 5).
- For users who have completed 1 or more tasks in the last 24 hours, an email is sent stating "Today, you completed N tasks." The email body contains the titles of the completed tasks.
- For users who meet both conditions above, an email is sent with both lists: unfinished and completed tasks within the last 24 hours.

## Modules

### 1. Spring Scheduler
Spring Scheduler uses a cron expression to trigger a method every midnight. This method reads the list of users, analyzes their tasks, and generates emails for sending.

### 2. Spring AMQP
Spring AMQP is responsible for packaging the generated emails into model classes and sending them to a RabbitMQ queue for further processing.

## Getting Started

To run the application locally, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd task-tracker-scheduler`
3. Build the project: `./mvnw clean install`
4. Run the application: `./mvnw spring-boot:run`

Ensure you have RabbitMQ installed and running locally or configure the application to connect to your RabbitMQ instance.

## Configuration

### Spring Scheduler
- Cron expression: This can be configured in the `application.properties` file to adjust the schedule of the method invocation.

### Spring AMQP
- RabbitMQ Connection: Configure RabbitMQ connection details (host, port, credentials) in the `application.properties` file.

## Dependencies
- Spring Boot
- Spring Scheduler
- Spring AMQP
- RabbitMQ

## Contributing
Contributions are welcome! Feel free to open issues or pull requests.

## License
This project is licensed under the [MIT License](LICENSE).

