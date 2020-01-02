package com.baizhi.service;


import com.baizhi.entity.firstpager.AlbunOne;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map<String,Object> queryAlbunPager(Integer page,Integer rows);

    /*  查询 所有的专辑*/
    public List<AlbunOne> queryfirstAll();

}
