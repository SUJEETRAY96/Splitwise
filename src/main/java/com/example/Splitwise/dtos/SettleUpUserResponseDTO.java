package com.example.Splitwise.dtos;

import com.example.Splitwise.strategy.SettleUpStrategy.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SettleUpUserResponseDTO {
    private String message;
    private String Status;
    private List<Transaction> transactionList;
}
