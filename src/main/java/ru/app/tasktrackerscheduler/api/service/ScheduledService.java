package ru.app.tasktrackerscheduler.api.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.app.tasktrackerscheduler.api.model.Task;
import ru.app.tasktrackerscheduler.api.model.User;
import ru.app.tasktrackerscheduler.api.model.dto.TaskDtoView;
import ru.app.tasktrackerscheduler.api.model.mapper.TaskMapper;
import ru.app.tasktrackerscheduler.rabbitMQ.model.dto.EmailDto;
import ru.app.tasktrackerscheduler.rabbitMQ.service.RabbitMQService;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Service for sending daily statistical email reports.
 */
@Service
@RequiredArgsConstructor
public class ScheduledService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledService.class);
    private final UserService userService;
    private final RabbitMQService rabbitMQService;
    private final TaskMapper taskMapper;

    /**
     * Method for sending daily statistical email reports.
     */
    @Scheduled(cron = "${cron.value}")
    public void sendDailyStatsEmails() {
        List<User> users = userService.getAll();

        users.forEach(user -> {
            StringBuilder bodyBuilder = new StringBuilder(); // Создание нового экземпляра перед каждым пользователем
            LOGGER.info("Processing user: {}", user.getEmail());

            List<TaskDtoView> tasksCompletedToday = filterTasks(user.getTasks(), task -> task.isCompleted() && task.getDateCompleted().isEqual(LocalDate.now()))
                    .stream().map(taskMapper::map).toList();

            List<TaskDtoView> tasksNotDone = filterTasks(user.getTasks(), task -> !task.isCompleted())
                    .stream().map(taskMapper::map).toList();

            EmailDto emailDto = new EmailDto();
            emailDto.setEmail(user.getEmail());

            if (!tasksCompletedToday.isEmpty()) {
                emailDto.setHeader("Congratulations, you have completed " + tasksCompletedToday.size() + " tasks today");
                appendTasksToStringBuilder(bodyBuilder, "Completed tasks", tasksCompletedToday);
            }

            if (!tasksNotDone.isEmpty()) {
                emailDto.setHeader("Oops, you still have " + tasksNotDone.size() + " unfinished tasks");
                appendTasksToStringBuilder(bodyBuilder, "Unfulfilled tasks", tasksNotDone);
            }

            if (emailDto.getHeader() == null && bodyBuilder.isEmpty()) {
                emailDto.setHeader("No tasks for today");
                appendTasksToStringBuilder(bodyBuilder, "You don't have any tasks for today. Enjoy your day!", List.of());
            }


            emailDto.setBody(bodyBuilder.toString().trim());

            LOGGER.info("Sending email to user: {}", user.getEmail());
            rabbitMQService.sendMessage(emailDto);
            LOGGER.info("Email sent successfully to user: {}", user.getEmail());
        });
    }


    /**
     * Filters the tasks based on the given predicate.
     *
     * @param tasks     The list of tasks to filter.
     * @param predicate The predicate to filter the tasks.
     * @return The filtered list of tasks.
     */
    private List<Task> filterTasks(List<Task> tasks, Predicate<Task> predicate) {
        return tasks.stream().filter(predicate).toList();
    }

    /**
     * Appends the tasks to the string builder with the specified header.
     *
     * @param builder The string builder to append the tasks to.
     * @param header  The header for the tasks.
     * @param tasks   The list of tasks to append.
     */
    private void appendTasksToStringBuilder(StringBuilder builder, String header, List<TaskDtoView> tasks) {
        builder.append(header).append(":\n");
        tasks.forEach(task -> {
            LOGGER.debug("- {}", task);
            builder.append("- ").append(task).append("\n");
        });
    }

}
