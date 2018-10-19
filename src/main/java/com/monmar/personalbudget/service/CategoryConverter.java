package com.monmar.personalbudget.service;

import com.monmar.personalbudget.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<String, Category> {

        private CategoryService categoryService;

        @Autowired
        public CategoryConverter(CategoryService categoryService){
            this.categoryService = categoryService;
        }

        @Override
        public Category convert(String id) {
            return categoryService.findCategoryById(Integer.valueOf(id));

    }


}
