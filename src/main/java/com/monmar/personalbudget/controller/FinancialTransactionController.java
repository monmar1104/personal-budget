package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.BudgetDetail;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.service.CategoryService;
import com.monmar.personalbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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

        FinancialTransaction financialTransaction = new FinancialTransaction();
//
        model.addAttribute("transaction", financialTransaction);
//        List<String> categoryList = categoryService.getCategoryList().stream().map(s -> s.getCategoryName()).collect(Collectors.toList());
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);


        return "list-transaction";
    }

    @PostMapping("/addTransaction")
    public String addTransaction(@ModelAttribute("transaction") @Valid FinancialTransaction financialTransaction, BindingResult result, Model model) {
        FinancialTransaction newfintrans = new FinancialTransaction();

        newfintrans.setTransactionDate(financialTransaction.getTransactionDate());
        String rejectedValue = result.getFieldErrors().get(0).getRejectedValue().toString();
        newfintrans.setCategory(categoryService.findCategoryById(Integer.valueOf(rejectedValue)));
        newfintrans.setTransactionDescription(financialTransaction.getTransactionDescription());
        newfintrans.setTransactionAmount(financialTransaction.getTransactionAmount());

        if(result.hasErrors()){
            result.toString();
        }

        transactionService.saveTransaction(newfintrans);

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


    @GetMapping("/showTransactionFormUpdate")
    public String showTransactionFormForUpdate(@RequestParam("budgetDetailId") int id, Model model) {

        FinancialTransaction transaction = transactionService.getTransactionById(id);

        model.addAttribute("transaction",transaction);

        return "";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("budgetDetailId") int id, Model model) {

        transactionService.deleteTransactionById(id);

        return "redirect:/transaction/list";
    }

    @PostMapping("/search")
    public String searchTransactionByName(@RequestParam("transactionName") String name, Model model) {
        List<FinancialTransaction> transactionList  = transactionService.searchTransactionByName(name);

        model.addAttribute("transactionList", transactionList);
        model.addAttribute("transaction", new FinancialTransaction());

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);

        return "list-transaction";
    }



    @GetMapping("/showAddTransactionsForm")
    public String showAddTransactionsForm(Model model) {

        FinancialTransaction financialTransaction = new FinancialTransaction();

        model.addAttribute("transactions", financialTransaction);
        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);


        return "add-transaction-form";
    }


}
