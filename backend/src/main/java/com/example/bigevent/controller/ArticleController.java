package com.example.bigevent.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bigevent.dto.Article;
import com.example.bigevent.dto.PageBean;
import com.example.bigevent.service.ArticleService;
import com.example.bigevent.utils.JWTUtil;
import com.example.bigevent.vo.ResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.zip.ZipEntry;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://47.93.166.101:10512"})
public class ArticleController {

    private final ArticleService articleService;
//    @GetMapping("/list")
//    public ResultVo list() {

    /// /        log.info("你好");
//        return ResultVo.ok(200,"成功");
//    }
    @PostMapping("/article")
    public ResultVo addArticle(@RequestBody @Validated Article article, @RequestAttribute Integer id) {
        article.setCreateUser(id);
        articleService.addArticle(article);
        return ResultVo.success(article, "添加成功");
    }

    @GetMapping("/article")
    public ResultVo getArticle(Integer pageNum, Integer pageSize, @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String state, @RequestAttribute Integer id) {
        PageBean<Article> list = articleService.list(pageNum, pageSize, categoryId, state, id);
        return ResultVo.success(list, "查询成功");
    }
}
