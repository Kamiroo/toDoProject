package com.kamiroo.todomanager.repo;

import com.kamiroo.todomanager.PriorityEnum;
import com.kamiroo.todomanager.StatusEnum;
import com.kamiroo.todomanager.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {

    List<ToDoEntity> findToDoEntityByToDoId(Long todoId);

    @Query(value = "select * from to_do inner join user_entity on to_do.user_id = user_entity.user_id where user_entity.user_id = :userId and to_do.status = :status", nativeQuery = true)
    List<ToDoEntity> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") int statusNumber);

    @Query(value = "select * from to_do inner join user_entity on to_do.user_id = user_entity.user_id where user_entity.user_id = :userId and to_do.status = :status and to_do.priority = :priority", nativeQuery = true)
    List<ToDoEntity> findByUserIdAndStatusAndPriority(@Param("userId") Long userId, @Param("status") int statusNumber, @Param("priority") int priorityNumber);

    @Modifying
    @Transactional
    @Query(value = "update to_do set title = :title, description = :description where to_do_id = :todoId", nativeQuery = true)
    void updateTodoOnTitleAndDescription(@Param("todoId") Long todoId, @Param("title") String title, @Param("description") String description);

    @Modifying
    @Transactional
    @Query(value = "update to_do set priority = :priority where to_do_id = :todoId", nativeQuery = true)
    void updateTodoOnPriority(@Param("todoId") Long todoId, @Param("priority") int priorityNumber);



}
