package com.baizhi;

import com.baizhi.DAO.AdminDao;
import com.baizhi.DAO.BannerDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class CmfdShiApplicationTests {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private  Banner banner;



    @Test
    public void contextLoads() {
        Admin admin = adminDao.quaryByname("顺");
        System.out.println(admin);
    }

    @Test
    public void insert(){
       /*
       * 添加
       * */
        banner.setId("1");
       banner.setCreate_date(new Date());
       banner.setImg("/img/A2.jpg");
        banner.setTitle("测试");
       banner.setStatus("展示");
        bannerDao.insertByBanner(banner);

    }
    @Test
    public void sele(){
        /*
        *查询所有
        * */
        List<Banner> banners = bannerDao.quaryByAll();
        for (Banner banner1 : banners) {
            System.out.println(banner1);
        }
    }

    @Test
    public void delete(){
    /*
    * 批量删除
    * */
        List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");
        bannerDao.deleteById(list);
    }
    @Test
    public void page(){
        List<Banner> banners = bannerDao.queryByPageAll(0, 2);
        for (Banner banner1 : banners) {
            System.out.println(banner1);
        }

    }

}
