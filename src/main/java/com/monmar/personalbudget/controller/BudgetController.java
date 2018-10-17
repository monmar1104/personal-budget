package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.BudgetDetail;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.OperationType;
import com.monmar.personalbudget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/list")
    public String listBudgetItems(Model model){
        List<BudgetDetail> budgetDetails = budgetService.getBudgetDetailList();

        model.addAttribute("budgetDetailList", budgetDetails);

        return "list-budget-items";
    }

    @GetMapping("/saveBudgetItem")
    public String saveBudgetItem(){

        Budget budget= new Budget();
        budget.setBudgetName("Maj2018");
        budget.setBudgetDate(LocalDate.now());
        Category category = new Category();
        category.setCategoryName("Woda");
        category.setCategoryOperationType(OperationType.EXPENDITURE);

        budgetService.addBudgetItem(budget, category, BigDecimal.valueOf(186.33));


        return "redirect:/budget/list";
    }

}
