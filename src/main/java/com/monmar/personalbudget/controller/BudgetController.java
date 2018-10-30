package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.*;
import com.monmar.personalbudget.service.BudgetService;
import com.monmar.personalbudget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public String listBudgetItems(Model model){
        List<BudgetDetail> budgetDetailList = budgetService.getBudgetDetailList();

        model.addAttribute("budgetDetailList", budgetDetailList);

        BudgetDetail budgetDetail = new BudgetDetail();
        model.addAttribute("budgetDetail", budgetDetail);

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);

        int budgetId = 10;

        Map<Integer, Double> getSumOfTransactionByCategoryMap = budgetService.getSumOfTransactionByCategoryMap(budgetId);
        model.addAttribute("sumCategoryMap", getSumOfTransactionByCategoryMap);


        return "list-budgets";
    }

    @GetMapping("/listByBudgetName")
    public String listBudgetItemsByBudgetName(@RequestParam("budgetName") String name, Model model){
        List<BudgetDetail> budgetDetails = budgetService.getBudgetDetailListByName(name);

        model.addAttribute("budgetDetailList", budgetDetails);

        return "list-budgets";
    }

    @GetMapping("/showAddBudgetItemForm")
    public String showAddTransactionForm(Model model) {

        BudgetDetail budgetDetail = new BudgetDetail();

        model.addAttribute("budgetDetail", budgetDetail);
        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);


        return "add-transaction";
    }


    @PostMapping("/addBudgetItem")
    public String addBudgetItem(@ModelAttribute("budgetDetail") @Valid BudgetDetail budgetDetail, BindingResult result, Model model) {

        String rejectedValue = result.getFieldErrors().get(0).getRejectedValue().toString();
        budgetDetail.setCategory(categoryService.findCategoryById(Integer.valueOf(rejectedValue)));
        budgetService.addBudgetItem(budgetDetail);

        return "redirect:/budget/list";
    }
    @PostMapping("/search")
    public String searchItemByCatName(@RequestParam("categoryName") String name, Model model) {
        List<BudgetDetail> budgetDetailList  = budgetService.searchBudgetItemByCatName(name);

        model.addAttribute("budgetDetailList", budgetDetailList);
        model.addAttribute("budgetDetail", new BudgetDetail());

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categoryName", name);

        return "list-budgets";
    }

    @GetMapping("/showAddBudgetItemFormUpdate")
    public String showTransactionFormForUpdate(@RequestParam("budgetDetailId") int id, Model model) {

        BudgetDetail budgetDetail = budgetService.getBudgetDetailById(id);

        model.addAttribute("budgetDetail",budgetDetail);
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);

        return "add-budget-detail-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("budgetDetailId") int id, Model model) {

        budgetService.deleteTransactionById(id);

        return "redirect:/budget/list";
    }


}
