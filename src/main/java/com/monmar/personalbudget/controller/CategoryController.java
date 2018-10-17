package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.OperationType;
import com.monmar.personalbudget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public String showCategories(Model model){

        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "list-category";
    }


    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") Category category){

        categoryService.saveCategory(category);

        return "redirect:/category/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormFormAdd(Model model){
        Category category = new Category();

//        List<Integer> multiplyerOperationType = Arrays.asList(OperationType.values())
//                                .stream()
//                                .map(s -> s.getOperationMultiplier())
//                                .collect(Collectors.toList());
//
//        model.addAttribute("operationTypes", multiplyerOperationType);
        model.addAttribute("category", category);

        return "category-form";
    }
}