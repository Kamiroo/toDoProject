package com.kamiroo.todomanager;

public class Comment {

    private Long todoId;
    private String comment;

    public Comment(Long todoId, String comment) {
        this.todoId = todoId;
        this.comment = comment;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
