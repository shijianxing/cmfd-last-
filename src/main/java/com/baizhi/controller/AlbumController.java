package com.baizhi.controller;

import com.baizhi.service.AlbumService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("pagerAll")
    public Map<String,Object> pagerALL(Integer page,Integer rows){
        //jqgrid 的分页查询
        Map<String, Object> map = albumService.queryAlbunPager(page, rows);
        return map;
    }
}
