package com.example.Splitwise.services;

import com.example.Splitwise.models.Expense;
import com.example.Splitwise.models.User;
import com.example.Splitwise.models.UserExpense;
import com.example.Splitwise.repositories.UserExpenseRepository;
import com.example.Splitwise.repositories.UserRepository;
import com.example.Splitwise.strategy.SettleUpStrategy.Transaction;
import com.example.Splitwise.strategy.SettleUpStrategy.TwoSetsSettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private UserRepository userRepository;
    private UserExpenseRepository userExpenseRepository;
    private TwoSetsSettleUpStrategy twoSetsSettleUpStrategy;
    @Autowired
    public ExpenseService(UserRepository userRepository,
                          UserExpenseRepository userExpenseRepository,
                          TwoSetsSettleUpStrategy twoSetsSettleUpStrategy){
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.twoSetsSettleUpStrategy = twoSetsSettleUpStrategy;
    }
    public List<Transaction> settleUpUser(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            //Throw Exception
            return null;
        }
        List<UserExpense> userExpenseList = userExpenseRepository.findAllByUser(userOptional.get());
        List<Expense> expensesInvolvingUser = new ArrayList<>();
        for(UserExpense userExpense: userExpenseList){
            expensesInvolvingUser.add(userExpense.getExpense());
        }
        List<Transaction> transactions = twoSetsSettleUpStrategy.settle(expensesInvolvingUser);
        List<Transaction> filterdTransaction = new ArrayList<>();
        for(Transaction transaction: transactions){
            if(transaction.getToUser().getId()==userId || transaction.getFromUser().getId()==userId){
                filterdTransaction.add(transaction);
            }
        }
        return filterdTransaction;
    }
}
