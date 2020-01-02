package com.baizhi.Text;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.AlbumDao;
import com.baizhi.DAO.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.firstpager.ArticleOne;
import com.baizhi.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class ArticleServiceTest  {
      @Autowired
    private ArticleService articleService;
      @Autowired
      private Article article;


      @Test
    public void pahe(){
          Map<String, Object> map = articleService.pagerAll(1, 2);
          System.out.println(map);
      }

    @Test
    public void first(){
        List<ArticleOne> articleOnes = articleService.queryfirstAll("1");
        System.out.println(articleOnes);
    }


    @Test
    public void text(){
          article.setAuthor("顾城");
          article.setContent("wen");
          article.setCreate_date(new Date());
          article.setId("3");
          article.setStatus("展示");
          article.setTitle("cheshi");
          article.setGuru_id("5");
          articleService.insertArticle(article);
    }
}
