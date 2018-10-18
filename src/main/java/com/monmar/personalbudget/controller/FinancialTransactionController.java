package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.service.CategoryService;
import com.monmar.personalbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transaction")
public class FinancialTransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String showTransactionList(Model model) {

        List<FinancialTransaction> financialTransactionList = transactionService.getTransactionList();
        model.addAttribute("transactionList", financialTransactionList);

//        FinancialTransaction financialTransaction = new FinancialTransaction();
//
//        model.addAttribute("transaction", financialTransaction);
//        List<String> categoryList = categoryService.getCategoryList().stream().map(s -> s.getCategoryName()).collect(Collectors.toList());
//        List<Category> categoryList = categoryService.getCategoryList();
//        model.addAttribute("categoryList", categoryList);


        return "list-transaction";
    }

    @PostMapping("/addTransaction")
    public String addTransaction(@ModelAttribute("transaction") FinancialTransaction financialTransaction) {
        transactionService.saveTransaction(financialTransaction);

        return "redirect:/transaction/list";
    }


    @GetMapping("/showAddTransactionForm")
    public String showAddTransactionForm(Model model) {

        FinancialTransaction financialTransaction = new FinancialTransaction();

        model.addAttribute("transaction", financialTransaction);
        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);


        return "add-transaction";
    }

    @GetMapping("/saveExTrans")
    public String saveExmpleTranaction(){
        FinancialTransaction financialTransaction = new FinancialTransaction();
        Category category = categoryService.getCategoryList().get(4);
        financialTransaction.setTransactionDate(LocalDate.now());
        financialTransaction.setTransactionAmount(255.2);
        financialTransaction.setTransactionDescription("sdfsdfsdggds");
        financialTransaction.setCategory(category);

        transactionService.saveTransaction(financialTransaction);


        return "redirect:/transaction/list";
    }

    @GetMapping("/showAddTransactionsForm")
    public String showAddTransactionsForm(Model model) {

        FinancialTransaction financialTransaction = new FinancialTransaction();

        model.addAttribute("transactions", financialTransaction);
        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);


        return "add-transaction-form";
    }
    //TODO ONLY FOR TEST
    @PostMapping("/addTransactions")
    public String addTransactions(@ModelAttribute("transactions") FinancialTransaction financialTransaction){

        financialTransaction.setCategory(categoryService.getCategoryList().get(3));
        financialTransaction.setTransactionDate(LocalDate.now());
        transactionService.saveTransaction(financialTransaction);

        return "redirect:/transaction/list";
    }

}
