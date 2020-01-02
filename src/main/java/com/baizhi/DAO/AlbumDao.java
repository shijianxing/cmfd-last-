package com.baizhi.DAO;


import com.baizhi.entity.Album;
import com.baizhi.entity.firstpager.AlbunOne;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //分页查询
    public List<Album> queryAlbunPager(@Param("start")Integer start,@Param("end")Integer end);

    //添加
    public void insertByalbum(Album album);

    //动态删除
    public void deleteAll(String [] id);

    //查询总条数
    public Integer queryCount();

    /*  查询 所有的专辑*/
    public List<AlbunOne> queryfirstAll();

}
