package com.example.bigevent.service.impl;

import com.example.bigevent.dto.Category;
import com.example.bigevent.mapper.CategoryRepository;
import com.example.bigevent.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryRepository.add(category);
    }

    @Override
    public List<Category> findCategoryById(Integer id) {
        List<Category> list = categoryRepository.findCategoryById(id);
        return list;
    }

    @Override
    public Category findDetailById(Integer id) {
        Category category = categoryRepository.findDetailById(id);
        return category;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryRepository.update(category);
    }
}
