package com.example.bigevent.service;

import com.example.bigevent.dto.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> findCategoryById(Integer id);

    Category findDetailById(Integer id);

    void update(Category category);
}
