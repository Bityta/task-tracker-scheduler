package ru.app.tasktrackerscheduler.api.model.mapper;

import org.junit.jupiter.api.Test;
import ru.app.tasktrackerscheduler.api.model.Task;
import ru.app.tasktrackerscheduler.api.model.dto.TaskDtoView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    @Test
    public void testMap() {
        // Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task();
        task.setHeader("Task Header");
        task.setDescription("Task Description");

        // When
        TaskDtoView taskDtoView = taskMapper.map(task);

        // Then
        assertEquals("Task Header", taskDtoView.getHeader());
        assertEquals("Task Description", taskDtoView.getDescription());
    }
}
