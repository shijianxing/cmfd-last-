package com.baizhi.controller;

import com.baizhi.entity.firstpager.AlbunOne;
import com.baizhi.entity.firstpager.ArticleOne;
import com.baizhi.entity.firstpager.ChapterOne;
import com.baizhi.entity.firstpager.bannerOne;
import com.baizhi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class Fistcontroller {

    @Autowired
    private BannerServcie bannerServcie;//轮播图

    @Autowired
    private AlbumService albumService;//专辑

    @Autowired
    private GuruService guruService; //上师

    @Autowired
    private ChapterServler chapterServler;//传密法要

    @Autowired
    private ArticleService articleService;//

    @RequestMapping("first_page")
    public Map<String,Object> fastpage(String uid, String type, String sub_type){
        HashMap<String, Object> map = new HashMap<>();
        //一级页面
        //查询轮播图
        System.out.println(uid+"------------------"+type);
        if ("all".equals(type)){
            System.out.println("----------------");
            List<bannerOne> bannerOnes = bannerServcie.queryFirst();
            List<AlbunOne> albunOnes = albumService.queryfirstAll();
            map.put("header",bannerOnes);
            map.put("body",albunOnes);
            return map;
        }
        if ("si".equals(type)&&"ssyi".equals(sub_type)){
            List<ArticleOne> articleOnes = articleService.queryfirstAll(uid);
            map.put("articleByid",articleOnes);
            return map;
        }

        if ("si".equals(type)&&"xmfy".equals(sub_type)){
            //显密法要
            List<ChapterOne> chapterOnes = chapterServler.queryAll();
            map.put("all",chapterOnes);
            return map;
        }else {
            return null;
        }
    }

    @RequestMapping("wen")
    public Map<String,Object> firstarticle(String id,String uid){
        HashMap<String, Object> map = new HashMap<>();
        //查询文章表的
        List<ChapterOne> chapterOnes = chapterServler.queryfirstALL(id);
        List<ArticleOne> articleOnes = articleService.queryfirstAll(uid);

        map.put("introduction",chapterOnes);
        map.put("list",articleOnes);

        return map;
    }


}
