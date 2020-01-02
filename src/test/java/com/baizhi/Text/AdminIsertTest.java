package com.baizhi.Text;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.AdminDao;
import com.baizhi.DAO.AlbumDao;
import com.baizhi.DAO.ChapterDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class AdminIsertTest {
  @Autowired
    private AdminDao adminDao;
    @Autowired
    private Admin admin;

    @Test
    public void inset(){
        //批量插入
        List<Admin> list=new ArrayList<>();
        admin.setId(UUID.randomUUID().toString());
        admin.setPassword("123123");
        admin.setUsername("poi");
        Admin admintwo=new Admin("123132","12312","密码","测试");

        list.add(admin);
        list.add(admintwo);
        adminDao.queryInsertAll(list);
    }


}
