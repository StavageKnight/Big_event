package com.example.bigevent.controller;

import com.example.bigevent.dto.Category;
import com.example.bigevent.service.CategoryService;
import com.example.bigevent.vo.ResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://47.93.166.101:10512"})
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResultVo addCategory(@RequestBody Category category, @RequestAttribute Integer id) {
        category.setCreateUser(id);
        categoryService.add(category);
        return ResultVo.success(category, "创建成功");
    }

    @GetMapping("/category")
    public ResultVo findCategoryById(@RequestAttribute Integer id) {
        List<Category> list = categoryService.findCategoryById(id);
        return ResultVo.success(list, "查询成功");
    }

    @GetMapping("/category/detail")
    public ResultVo findDetailById(@RequestParam Integer id) {
        Category list = categoryService.findDetailById(id);
        return ResultVo.success(list, "查询成功");
    }

    @PutMapping("/category")
    public ResultVo update(@RequestBody Category category) {
        categoryService.update(category);
        return ResultVo.ok(200, "更新成功");
    }
}
