package ru.app.tasktrackerscheduler.api.model.mapper;

import org.springframework.stereotype.Component;
import ru.app.tasktrackerscheduler.api.model.Task;
import ru.app.tasktrackerscheduler.api.model.dto.TaskDtoView;

/**
 * Component responsible for mapping between Task entities and Task DTOs.
 */
@Component
public class TaskMapper {

    /**
     * Maps a Task entity to a TaskDtoView DTO.
     *
     * @param task The Task entity to map
     * @return The mapped TaskDtoView DTO
     */
    public TaskDtoView map(Task task) {
        return TaskDtoView.builder()
                .header(task.getHeader())
                .description(task.getDescription())
                .build();
    }

}
