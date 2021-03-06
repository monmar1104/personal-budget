package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public String showCategories(Model model){
    	
    	model.addAttribute("category", new Category());

        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);
        return "list-category";
    }


    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") Category category){

        categoryService.saveCategory(category);

        return "redirect:/category/list";
    }

    @GetMapping("/showUpdateCategoryForm")
    public String showFormFormAdd(@RequestParam("categoryId") int categoryId, Model model){
    	Category category = categoryService.findCategoryById(categoryId);
    	model.addAttribute("category", category);
    	
    	
        return "update-category-form";
    }
}
