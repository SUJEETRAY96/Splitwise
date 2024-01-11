package com.example.Splitwise.controllers;

import com.example.Splitwise.dtos.SettleUpGroupRequestDTO;
import com.example.Splitwise.dtos.SettleUpUserRequestDTO;
import com.example.Splitwise.dtos.SettleUpUserResponseDTO;
import com.example.Splitwise.services.ExpenseService;
import com.example.Splitwise.strategy.SettleUpStrategy.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    public SettleUpUserResponseDTO settleup(SettleUpUserRequestDTO request){
        List<Transaction> transactions = expenseService.settleUpUser(request.getUserId());
        SettleUpUserResponseDTO responseDTO = new SettleUpUserResponseDTO();
        responseDTO.setMessage("Settled");
        responseDTO.setStatus("SUCCESS");
        responseDTO.setTransactionList(transactions);
        return responseDTO;
    }
    public SettleUpUserResponseDTO settleupzgroup(SettleUpGroupRequestDTO request){
        List<Transaction> transactions = expenseService.settleUpGroup(request.getGroupId());
        SettleUpUserResponseDTO responseDTO = new SettleUpUserResponseDTO();
        responseDTO.setMessage("Settled");
        responseDTO.setStatus("SUCCESS");
        responseDTO.setTransactionList(transactions);
        return responseDTO;
    }
}
