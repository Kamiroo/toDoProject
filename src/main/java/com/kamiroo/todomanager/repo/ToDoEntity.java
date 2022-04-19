package com.kamiroo.todomanager.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "toDo")
public class ToDoEntity {

    @Id
    @GeneratedValue
    @Column(name = "toDo_id")
    private Long toDoId;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private String title;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "varchar(100)"
    )
    private String description;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "varchar(20)"
    )
    private StatusEnum status;

    @Column(
            name = "priority",
            nullable = false,
            columnDefinition = "varchar(20)"
    )
    private PriorityEnum priority;

    @CreatedDate
    private LocalDate createDate;
    @LastModifiedDate
    private LocalDate updateDate;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public Long getToDoId() {
        return toDoId;
    }

    public void setToDoId(Long toDoId) {
        this.toDoId = toDoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
