package com.baizhi.DAO;

import com.baizhi.entity.Banner;
import com.baizhi.entity.firstpager.bannerOne;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //查询所有`
    public List<Banner> quaryByAll();
    // 动态修改修改
    public void updateById(Banner banner);
    //批量删除
    public void deleteById(List<String> id);
    //添加轮播图
    public void  insertByBanner(Banner banner);

    //查询总条数
    public Integer queryCount();
    //分页查询
    public List<Banner> queryByPageAll(@Param("start") Integer start,@Param("end") Integer end);

    //前台展示
    public List<bannerOne> queryFirst();

}
