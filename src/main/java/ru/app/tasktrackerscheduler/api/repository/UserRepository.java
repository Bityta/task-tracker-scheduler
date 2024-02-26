package ru.app.tasktrackerscheduler.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.tasktrackerscheduler.api.model.User;

/**
 * Repository interface for managing users.
 * Provides CRUD operations for User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
