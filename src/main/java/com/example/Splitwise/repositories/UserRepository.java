package com.example.Splitwise.repositories;

import com.example.Splitwise.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findById(Long aLong);

    Optional<User> findByPhoneNumber(String phone);
}
