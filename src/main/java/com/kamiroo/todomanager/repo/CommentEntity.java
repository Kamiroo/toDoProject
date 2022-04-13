package com.kamiroo.todomanager.repo;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "comment_id"
    )
    private Long commentId;

    @Column(
            name = "comment",
            nullable = false,
            columnDefinition = "varchar(60)"
    )
    private String comment;

    @CreatedDate
    @Column(
            name = "create_date"
    )
    private LocalDate createDate;

}
