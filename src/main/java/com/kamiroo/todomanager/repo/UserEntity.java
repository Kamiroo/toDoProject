package com.kamiroo.todomanager.repo;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "user_id"
    )
    private Long userId;

    @Column(
            name = "login",
            nullable = false,
            columnDefinition = "varchar(35)"
    )
    private String login;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "varchar(20)"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "varchar(20)"
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "varchar(40)",
            unique = true
    )
    private String email;

    @OneToMany
    @JoinColumn(name = "toDo_id")
    private List<ToDoEntity> toDoEntities;

    public UserEntity(String login, String firstName, String lastName, String email) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserEntity() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", toDoEntities=" + toDoEntities +
                '}';
    }
}