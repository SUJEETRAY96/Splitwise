package com.example.Splitwise.strategy.SettleUpStrategy;

import com.example.Splitwise.models.*;
import com.example.Splitwise.repositories.UserExpenseRepository;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TwoSetsSettleUpStrategy implements SettleUpStrategy{
    private UserExpenseRepository userExpenseRepository;

    @Autowired
    public TwoSetsSettleUpStrategy(UserExpenseRepository userExpenseRepository){
        this.userExpenseRepository=userExpenseRepository;
    }
    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        // get All the expenses
        List<UserExpense> userExpensesForAllTheseUser = userExpenseRepository.findAllByExpenceIn(expenses);


        //How many user involved in this particular expense
        HashMap<User,Integer> moneyPaidExtra = new HashMap<>();
        for(UserExpense userExpense: userExpensesForAllTheseUser){
            User user = userExpense.getUser();
            if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                moneyPaidExtra.put(user, moneyPaidExtra.get(user) + userExpense.getAmount());
            }else{
                moneyPaidExtra.put(user, moneyPaidExtra.get(user) - userExpense.getAmount());
            }
        }

        // Divide into two sets
        TreeSet<Pair<User,Integer>> extraPaid = new TreeSet<>();
        TreeSet<Pair<User,Integer>> lessPaid = new TreeSet<>();

        for(Map.Entry<User,Integer> moneyPaid: moneyPaidExtra.entrySet()){
            if(moneyPaid.getValue()<0){
                lessPaid.add(new Pair<>(moneyPaid.getKey(), moneyPaid.getValue()));
            }else{
                extraPaid.add(new Pair<>(moneyPaid.getKey(), moneyPaid.getValue()));
            }
        }
        List<Transaction> transactionList = new ArrayList<>();

        while(!lessPaid.isEmpty()){
            Pair<User,Integer> lessPaidPair = lessPaid.pollFirst();
            Pair<User,Integer> extraPaidPair = extraPaid.pollFirst();
            Transaction transaction = new Transaction();
            transaction.setFromUser(lessPaidPair.a);
            transaction.setToUser(extraPaidPair.a);
            if(Math.abs(lessPaidPair.b)<extraPaidPair.b){
                transaction.setAmount(Math.abs(lessPaidPair.b));
                if((Math.abs(lessPaidPair.b)-extraPaidPair.b) != 0){
                    extraPaid.add(new Pair<>(extraPaidPair.a,(extraPaidPair.b-Math.abs(lessPaidPair.b))));
                }
            }else{
                transaction.setAmount(extraPaidPair.b);
                if((Math.abs(lessPaidPair.b)-extraPaidPair.b) != 0) {
                    lessPaid.add(new Pair<>(lessPaidPair.a, (extraPaidPair.b - Math.abs(lessPaidPair.b))));
                }
            }

            transactionList.add(transaction);
        }
        return transactionList;
    }
}
