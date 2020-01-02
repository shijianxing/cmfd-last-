package com.baizhi.PoiIntext;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.BannerDao;
import com.baizhi.DAO.UserMapper;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Moneasy;
import com.baizhi.entity.User;
import com.baizhi.entity.firstpager.bannerOne;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class banneronetext {
    @Autowired
   private BannerDao bannerDao;


    @Test
    public void test1(){
        List<bannerOne> bannerOnes = bannerDao.queryFirst();
        for (bannerOne bannerOne : bannerOnes) {
            System.out.println(bannerOne);
        }
    }
}
