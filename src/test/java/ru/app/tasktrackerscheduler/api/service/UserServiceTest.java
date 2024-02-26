package ru.app.tasktrackerscheduler.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.app.tasktrackerscheduler.api.model.User;
import ru.app.tasktrackerscheduler.api.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void getAll_TwoUser() {
        User user1 = User.builder()
                .email("test1@gmail.com")
                .password("password")
                .firstName("Test")
                .build();
        User user2 = User.builder()
                .email("test2@gmail.com")
                .password("password")
                .firstName("Test")
                .build();

        when(this.userRepository.findAll()).thenReturn(List.of(user1, user2));
        assertEquals(List.of(user1, user2), this.userService.getAll());
        verify(this.userRepository, times(1)).findAll();

    }

    @Test
    public void getAll_OneUser() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("password")
                .firstName("Test")
                .build();

        when(this.userRepository.findAll()).thenReturn(List.of(user));
        assertEquals(List.of(user), this.userService.getAll());
        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    public void getAll_Empty() {
        when(this.userRepository.findAll()).thenReturn(List.of());
        assertEquals(List.of(), this.userService.getAll());
        verify(this.userRepository, times(1)).findAll();

    }


}