package com.kamiroo.todomanager.repo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column(
            name = "comment_id"
    )
    private Long commentId;

    @Column(
            name = "comment",
            nullable = true,
            columnDefinition = "varchar(60)"
    )
    private String comment;

    @CreatedDate
    @Column(
            name = "create_date"
    )
    private LocalDate createDate;

    public CommentEntity() {
    }

    public CommentEntity(Long commentId, String comment, LocalDate createDate) {
        this.commentId = commentId;
        this.comment = comment;
        this.createDate = createDate;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
