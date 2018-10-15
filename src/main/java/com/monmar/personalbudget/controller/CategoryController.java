package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.ExpenditureCategory;
import com.monmar.personalbudget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public String showCategories(Model model){

        List<ExpenditureCategory> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "list-category";
    }


    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") ExpenditureCategory category){

        categoryService.saveCategory(category);

        return "redirect:/category/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormFormAdd(Model model){
        ExpenditureCategory category = new ExpenditureCategory();
        model.addAttribute("category", category);

        return "category-form";
    }
}
