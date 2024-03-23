package ru.app.tasktrackerscheduler.api.model.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDtoViewTest {

    @Test
    public void testToString() {
        // Given
        TaskDtoView taskDtoView = TaskDtoView.builder()
                .header("Task Header")
                .description("Task Description")
                .build();

        // When
        String toStringResult = taskDtoView.toString();

        // Then
        assertEquals("Task Header: Task Description", toStringResult);
    }

    @Test
    public void testBuilder() {
        // Given
        TaskDtoView taskDtoView = TaskDtoView.builder()
                .header("Task Header")
                .description("Task Description")
                .build();

        // When
        String header = taskDtoView.getHeader();
        String description = taskDtoView.getDescription();

        // Then
        assertEquals("Task Header", header);
        assertEquals("Task Description", description);
    }
}
