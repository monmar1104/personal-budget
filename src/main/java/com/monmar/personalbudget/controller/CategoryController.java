package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.ExpenditureCategory;
import com.monmar.personalbudget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/showCategories")
    public String showCategories(Model model){

        List<ExpenditureCategory> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "category-list";
    }
}
