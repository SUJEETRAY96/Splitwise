package com.example.Splitwise.strategy.SettleUpStrategy;

import com.example.Splitwise.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private User fromUser;
    private User toUser;
    private int amount;
}
