package com.baizhi.service.Impl;

import com.baizhi.DAO.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.firstpager.AlbunOne;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map<String, Object> queryAlbunPager(Integer page, Integer rows) {
        /*
         * 1.计算起始
         * 2.jqGrid
         * 3.page 当前页
         *   rows 数据
         *   total：总页数
         *   records： 总条数
         * */
        //计算起始页
        Integer start=(page-1)*rows;
        //总条数
        Integer count = albumDao.queryCount();
        //计算页数
        Integer total=count%rows==0?count/rows:count/rows+1;
        //数据
        List<Album> albums = albumDao.queryAlbunPager(start, rows);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("page",page);
        map.put("rows",albums);
        map.put("total",total);
        map.put("records",count);

        return map;
    }

    @Override
    public List<AlbunOne> queryfirstAll() {

        return albumDao.queryfirstAll();
    }

}
