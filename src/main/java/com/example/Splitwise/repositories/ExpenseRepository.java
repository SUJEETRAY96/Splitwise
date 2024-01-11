package com.example.Splitwise.repositories;

import com.example.Splitwise.models.Expense;
import com.example.Splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findAllByGroup(Group group);
}
