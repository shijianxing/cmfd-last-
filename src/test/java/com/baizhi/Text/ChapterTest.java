package com.baizhi.Text;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.AdminDao;
import com.baizhi.DAO.AlbumDao;
import com.baizhi.DAO.BannerDao;
import com.baizhi.DAO.ChapterDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.firstpager.ChapterOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.Encoder;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class ChapterTest {
    @Autowired
    private ChapterDao chapterDao;

    @Autowired
    private AlbumDao albumDao;

    @Test
    public void qu(){
        Integer count = chapterDao.queryCount("1");
        System.out.println(count);
    }
    @Test
    public void text1(){
        List<Chapter> chapters = chapterDao.queryByIdPagerAll(0, 1, "1");
        System.out.println(chapters);
    }
    @Test
    public void text2(){
        Album album = new Album("2", "测试", "c", "12", "xiaos", "sdf", "12", "sdf", new Date(), "展示", "ss");
        albumDao.insertByalbum(album);

    }

    @Test
    public void text3(){
        //专辑表
        List<Album> albums = albumDao.queryAlbunPager(0, 1);
        System.out.println(albums);

    }
    @Test
    public void text4(){
        /*String path= ClassLoader.getSystemResource("test.mp4").getPath();
        File source = new File(path);
        Encoder encoder = new Encoder(new OSXffmpeglocator());
        try {
            MultimediaInfo multimediaInfo = encoder.getInfo(source);

            long ls = multimediaInfo.getDuration();
            System.out.println("此视频时长为:"+ls);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        List<ChapterOne> chapterOnes = chapterDao.queryfirstALL("1");
        System.out.println(chapterOnes);

    }

}
