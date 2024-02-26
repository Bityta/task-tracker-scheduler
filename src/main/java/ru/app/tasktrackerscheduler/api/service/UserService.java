package ru.app.tasktrackerscheduler.api.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.tasktrackerscheduler.api.model.User;
import ru.app.tasktrackerscheduler.api.repository.UserRepository;

import java.util.List;

/**
 * Service for managing users.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
