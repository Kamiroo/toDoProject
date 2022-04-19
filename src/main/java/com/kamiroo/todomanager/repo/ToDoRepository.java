package com.kamiroo.todomanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kamiroo.todomanager.StatusEnum;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {

  List<ToDoEntity> findToDoEntityByToDoId(Long todoId);

  @Query(value = "select * from to_do inner join user_entity on to_do.user_id = user_entity.user_id where user_entity.user_id = :userId and to_do.status = :status", nativeQuery = true)
  List<ToDoEntity> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") int statusNumber);

  List<ToDoEntity> findByUserEntityUserIdAndStatus(Long userId, StatusEnum status);

}
