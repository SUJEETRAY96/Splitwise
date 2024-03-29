package com.example.Splitwise.repositories;

import com.example.Splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long> {

    Optional<Group> findById(Long id);
}
