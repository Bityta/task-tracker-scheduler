package ru.app.tasktrackerscheduler.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * Entity class representing the role of a user in the application.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
@ToString
@EqualsAndHashCode
public class UserRole {

    /**
     * Unique identifier for the user role.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_roles_id_seq")
    @SequenceGenerator(name = "users_roles_id_seq", sequenceName = "users_roles_id_seq", allocationSize = 1)
    private long id;

    /**
     * The role of the user.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private RoleEnum role;

    /**
     * The user associated with this role.
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}
