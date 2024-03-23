package ru.app.tasktrackerscheduler.rabbitMQ.model.dto;

import lombok.*;

/**
 * Data transfer object representing email information.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    /**
     * The email address.
     */
    private String email;

    /**
     * The header of the email.
     */
    private String header;

    /**
     * The body of the email.
     */
    private String body;
}
