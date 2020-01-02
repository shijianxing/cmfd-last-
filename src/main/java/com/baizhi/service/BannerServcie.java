package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.firstpager.bannerOne;

import java.util.List;
import java.util.Map;

public interface BannerServcie {
    //查询总条数
    public Integer queryCount();
    //用于jqgrad 的分页查询
    public Map<String,Object> PagerByBanner(Integer page,Integer rows);
    //添加轮播图
    public void  insertByBanner(Banner banner);
    //批量删除
    public void deleteById(String[] id);
    // 动态修改修改
    public void updateById(Banner banner);

    //查询所有用户easypoi的导出
    public List<Banner> queryAll();

    //前台展示
    public List<bannerOne> queryFirst();

}
