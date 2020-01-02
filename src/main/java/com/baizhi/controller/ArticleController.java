package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("article")
//文章表
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("pagerAll")
    public Map<String, Object> pagerAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.pagerAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public void exdit(String[] id, String oper, Article article) {
        if ("del".equals(oper)) {
            articleService.DeleteById(id);
        }/*else  if ("add".equals(oper)){
            System.out.println(article);
        }*/
    }

    @RequestMapping("add")
    public void addArticle(Article article) {
        System.out.println(article.getContent());
        articleService.insertArticle(article);
    }

    @RequestMapping("updatesubmit")
    public void updateArticle(Article article) {
        System.out.println(article.getContent());
        articleService.updateArticle(article);
    }

}
