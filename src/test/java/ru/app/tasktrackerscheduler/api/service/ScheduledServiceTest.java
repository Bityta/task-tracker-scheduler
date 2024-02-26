package ru.app.tasktrackerscheduler.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.app.tasktrackerscheduler.api.model.Task;
import ru.app.tasktrackerscheduler.api.model.User;
import ru.app.tasktrackerscheduler.api.model.dto.TaskDtoView;
import ru.app.tasktrackerscheduler.api.model.mapper.TaskMapper;
import ru.app.tasktrackerscheduler.rabbitMQ.model.dto.EmailDto;
import ru.app.tasktrackerscheduler.rabbitMQ.service.RabbitMQService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ScheduledServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private RabbitMQService rabbitMQService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private ScheduledService scheduledService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sendDailyStatsEmails_NoUsers() {
        when(userService.getAll()).thenReturn(Collections.emptyList());

        scheduledService.sendDailyStatsEmails();

        verify(rabbitMQService, never()).sendMessage(any());
    }

    @Test
    void sendDailyStatsEmails_WithUsers() {
        User user = new User();
        user.setEmail("test@example.com");
        List<User> users = Collections.singletonList(user);

        when(userService.getAll()).thenReturn(users);

        TaskDtoView taskDto = TaskDtoView.builder()
                .header("Task Header")
                .description("Task Description")
                .build();

        List<TaskDtoView> tasks = Collections.singletonList(taskDto);

        when(taskMapper.map(any())).thenReturn(taskDto);

        scheduledService.sendDailyStatsEmails();

        verify(rabbitMQService, times(1)).sendMessage(any(EmailDto.class));
    }


    @Test
    void testSendDailyStatsEmails_NoUsers() {
        // Arrange
        when(userService.getAll()).thenReturn(Collections.emptyList());

        // Act
        scheduledService.sendDailyStatsEmails();

        // Assert
        verify(rabbitMQService, never()).sendMessage(any());
    }

    @Test
    void testSendDailyStatsEmails_CompletedTasks() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        Task task = new Task();
        task.setCompleted(true);
        task.setDateCompleted(LocalDate.now());
        user.setTasks(List.of(task));
        when(userService.getAll()).thenReturn(List.of(user));
        when(taskMapper.map(any())).thenReturn(TaskDtoView.builder().build());

        // Act
        scheduledService.sendDailyStatsEmails();

        // Assert
        verify(rabbitMQService, times(1)).sendMessage(any());
    }

    @Test
    void testSendDailyStatsEmails_UnfinishedTasks() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        Task unfinishedTask = new Task();
        unfinishedTask.setCompleted(false);
        user.setTasks(List.of(unfinishedTask));
        when(userService.getAll()).thenReturn(List.of(user));
        when(taskMapper.map(any())).thenReturn(TaskDtoView.builder().build());

        // Act
        scheduledService.sendDailyStatsEmails();

        // Assert
        verify(rabbitMQService, times(1)).sendMessage(any());
    }


}
