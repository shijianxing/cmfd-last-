package com.baizhi.PoiIntext;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.AlbumDao;
import com.baizhi.DAO.BannerDao;
import com.baizhi.entity.firstpager.AlbunOne;
import com.baizhi.entity.firstpager.bannerOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class albumonetext {
    @Autowired
   private AlbumDao albumDao;


    @Test
    public void test1(){
        List<AlbunOne> albunOnes = albumDao.queryfirstAll();
        for (AlbunOne albunOne : albunOnes) {
            System.out.println(albunOne);
        }
    }
}
