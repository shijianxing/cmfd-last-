package com.baizhi.service.Impl;

import com.baizhi.DAO.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.firstpager.ArticleOne;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> pagerAll(Integer page, Integer rows) {
        //计算起始页
        Integer start=(page-1)*rows;
        //总条数
        Integer count = articleDao.countById();
        //计算总页数
        Integer total=count%rows==0?count/rows:count/rows+1;
        //数据
        List<Article> articles = articleDao.queryPageALL(start, rows);

        //返回给jqgrid
        Map<String,Object> map=new HashMap<String,Object>();
        //添加
        map.put("page",page);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",articles);
        return map;
    }

    @Override
    public void DeleteById(String[] id) {
        articleDao.DeleteById(id);
    }

    @Override
    public void insertArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        articleDao.insertArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public List<ArticleOne> queryfirstAll(String guruid) {
        return articleDao.queryfirstAll(guruid);
    }
}
