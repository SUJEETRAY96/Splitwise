package com.example.Splitwise.strategy.SettleUpStrategy;

import com.example.Splitwise.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settle(List<Expense> expenses);
}
