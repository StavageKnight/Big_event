package com.example.bigevent.service.impl;

import com.example.bigevent.dto.Article;
import com.example.bigevent.dto.PageBean;
import com.example.bigevent.mapper.ArticleRepository;
import com.example.bigevent.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public void addArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateTime(LocalDateTime.now());
        articleRepository.addArticle(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state, Integer id) {
        PageBean<Article> pageBean = new PageBean<>();
        //导入分页查询插件
        PageHelper.startPage(pageNum, pageSize);
        List<Article> li = articleRepository.list(categoryId, state, id);
        Page<Article> page = (Page<Article>) li;
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

}
