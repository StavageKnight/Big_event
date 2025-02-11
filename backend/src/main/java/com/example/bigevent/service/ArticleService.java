package com.example.bigevent.service;

import com.example.bigevent.dto.Article;
import com.example.bigevent.dto.PageBean;

public interface ArticleService {

    void addArticle(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state,Integer id);
}
