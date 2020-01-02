package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.firstpager.ArticleOne;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //分页查询
    public Map<String, Object> pagerAll(Integer page, Integer rows);

    //批量删除
    public void DeleteById(String[] id);

    //添加
    public void insertArticle(Article article);

    //修改
    public void updateArticle(Article article);

    public List<ArticleOne> queryfirstAll(String guruid);//查询

}
