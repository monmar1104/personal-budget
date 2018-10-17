package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class FinancialTransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/list")
    public String showTransactionList(Model model){

        List<FinancialTransaction> financialTransactionList = transactionService.getTransactionList();
        model.addAttribute("transactionList", financialTransactionList);

        return "list-transaction";

    }

}
