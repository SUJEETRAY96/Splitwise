package com.example.Splitwise.repositories;

import com.example.Splitwise.models.Expense;
import com.example.Splitwise.models.User;
import com.example.Splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense,Long> {
    List<UserExpense> findAllByUser(User user);

    List<UserExpense> findAllByExpenceIn(List<Expense> expenses);
}
