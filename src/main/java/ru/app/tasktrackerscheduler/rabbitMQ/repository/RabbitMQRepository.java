package ru.app.tasktrackerscheduler.rabbitMQ.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.app.tasktrackerscheduler.rabbitMQ.model.dto.EmailDto;


/**
 * Feign repository for sending messages through RabbitMQ.
 */
@FeignClient("task-tracker-email-sender")
public interface RabbitMQRepository {

    /**
     * Sends a message using RabbitMQ.
     *
     * @param emailDto DTO containing the information to send the message.
     * @return Response from the server.
     */
    @PostMapping("/message")
    ResponseEntity<String> sendMessage(@RequestBody EmailDto emailDto);
}
