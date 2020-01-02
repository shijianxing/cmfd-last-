package com.baizhi.service.Impl;

import com.baizhi.DAO.BannerDao;
import com.baizhi.entity.firstpager.bannerOne;
import com.baizhi.service.BannerServcie;
import org.springframework.beans.factory.annotation.Autowired;
import com.baizhi.entity.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class BannerServiceImpl implements BannerServcie {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public Integer queryCount() {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> PagerByBanner(Integer page, Integer rows) {


            /*
             * 1.计算起始
             * 2.jqGrid
             * 3.page 当前页
             *   rows 数据
             *   total：总页数
             *   records： 总页数
             * */
            //计算起始页  当前页-1乘以每页展示的条数
            Integer start=(page-1)*rows;
            //总页数 总页数等于 总条数/每页展示的条数
            Integer counts = bannerDao.queryCount();
            //三元 运算符 用于数据是否展示完全
            Integer total=counts%rows==0?counts/rows:counts/rows+1;
            //获得数据
            List<com.baizhi.entity.Banner> banners = bannerDao.queryByPageAll(start, rows);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("page",page);
            map.put("rows",banners);
            map.put("total",total);
            map.put("records",counts);
            System.out.println("--------分页查询-----"+map);

            return map;
    }

    public void insertByBanner(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        bannerDao.insertByBanner(banner);
    }

    public void deleteById(String[] id) {
        List<String> ids = new ArrayList<>();
        for (String s : id) {
            ids.add(s);
        }
        bannerDao.deleteById(ids);
    }

    public void updateById(Banner banner) {

        bannerDao.updateById(banner);
    }

    @Override
    public List<Banner> queryAll() {
        return bannerDao.quaryByAll();
    }

    @Override
    public List<bannerOne> queryFirst() {

        return bannerDao.queryFirst();
    }
}
