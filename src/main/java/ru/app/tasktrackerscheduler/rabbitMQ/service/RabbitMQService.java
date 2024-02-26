package ru.app.tasktrackerscheduler.rabbitMQ.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.tasktrackerscheduler.rabbitMQ.model.dto.EmailDto;
import ru.app.tasktrackerscheduler.rabbitMQ.repository.RabbitMQRepository;



import org.springframework.stereotype.Service;
import ru.app.tasktrackerscheduler.rabbitMQ.model.dto.EmailDto;
import ru.app.tasktrackerscheduler.rabbitMQ.repository.RabbitMQRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service for sending messages through RabbitMQ.
 */
@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final RabbitMQRepository rabbitMQRepository;

    /**
     * Sends a message via RabbitMQ.
     *
     * @param emailDto DTO containing the information to send the message.
     * @throws FeignException if an error occurs during the message sending process.
     */
    public void sendMessage(EmailDto emailDto) throws FeignException {
        rabbitMQRepository.sendMessage(emailDto);
    }
}
