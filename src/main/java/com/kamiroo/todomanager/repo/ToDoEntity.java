package com.kamiroo.todomanager.repo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
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
    private Enum status;

    @Column(
            name = "priority",
            nullable = false,
            columnDefinition = "varchar(20)"
    )
    private String priority;

    @CreatedDate
    private LocalDate createDate;
    @LastModifiedDate
    private LocalDate updateDate;

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

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
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
}
