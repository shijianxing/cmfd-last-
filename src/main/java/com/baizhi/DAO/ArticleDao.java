package com.baizhi.DAO;

import com.baizhi.entity.Article;
import com.baizhi.entity.firstpager.ArticleOne;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    //分页插叙
    public List<Article> queryPageALL(@Param("start")Integer start,@Param("end") Integer end);
    //查询总条数
    public Integer countById();

    //批量删除
    public void DeleteById(String[] id);

    //添加
    public void insertArticle(Article article);

    //动态修改
    public  void updateArticle(Article article);
    //接口文档
    public List<ArticleOne> queryfirstAll(String guruid);//查询
}
