package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.OperationType;
import com.monmar.personalbudget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/saveBudgetItem")
    public String saveBudgetItem(){

        Budget budget= new Budget();
        budget.setBudgetName("Maj2018");
        budget.setCreationDate(LocalDate.now());
        Category category = new Category();
        category.setCategoryName("Energia");

        budgetService.addBudgetItem(budget, category, BigDecimal.valueOf(255.25), OperationType.EXPENDITURE);


        return "redirect:/budget/list";
    }

}
