package ru.app.tasktrackerscheduler.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.tasktrackerscheduler.api.model.Task;
import ru.app.tasktrackerscheduler.api.repository.TaskRepository;

import java.util.List;

/**
 * Service for managing tasks.
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Returns a list of tasks for the user with the specified email.
     *
     * @param email The email of the user.
     * @return The list of user tasks.
     */
    public List<Task> getByOwnerEmail(String email) {
        return this.taskRepository.getByOwner_Email(email);
    }
}
