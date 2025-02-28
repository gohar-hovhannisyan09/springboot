package com.gh.springboot.repository;

import com.gh.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.email=:email")
    Optional<User> findByEmail(String email);

    List<User> getById(long id);
}
