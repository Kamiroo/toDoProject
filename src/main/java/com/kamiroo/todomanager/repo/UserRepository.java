package com.kamiroo.todomanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByFirstName(String firstName);

    @Query(value = "select * from user_entity", nativeQuery = true)
    List<UserEntity> findAllUsers();

    List<UserEntity> deleteByFirstNameAndLastName(String firstName, String lastName);
}
