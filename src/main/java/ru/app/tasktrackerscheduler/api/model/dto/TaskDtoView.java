package ru.app.tasktrackerscheduler.api.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * DTO class representing a view of a task.
 */

@Builder
@Getter
public class TaskDtoView {

    /**
     * The header of the task.
     */
    private String header;

    /**
     * The description of the task.
     */
    private String description;

    @Override
    public String toString() {
        return header + ": " + description;
    }
}
