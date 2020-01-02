package com.baizhi.Text;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.GuruMapper;
import com.baizhi.entity.Guru;

import java.util.*;

import com.baizhi.entity.Moneasy;
import com.baizhi.entity.User;
import com.baizhi.service.GuruService;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class userTest {
    @Autowired
    private GuruMapper guruMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private GuruService guruService;

    @Test
    public void query(){
        Guru guru = guruMapper.selectByPrimaryKey("1");
        System.out.println(guru);
    }

    @Test
    public void isneret(){

        guruMapper.insert(new Guru("1245","2","小黄","小白","男",new Date(),"xxx","sd"));
    }
    @Test
    public void mon(){
        List<Integer> list=new ArrayList<>();
        Integer[] mon={1,2,3,4,5,6,7};
        for (Integer integer : mon) {
            Integer modata = guruMapper.Modata(integer);

            list.add(modata);
        }
       /* GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b121a256bfeb4d0e874c71b5a744d092");
        goEasy.publish("gurueasy",list.toString());*/

    }

    @Test
    public void goeasy(){
        //查询一至12月
        List<Integer> list = new ArrayList<>();
        List<Moneasy> monther = guruMapper.monther();
        for (Moneasy moneasy : monther) {
            list.add(moneasy.getCount());
        }
        //发布消息
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b121a256bfeb4d0e874c71b5a744d092");
        goEasy.publish("mother",list.toString());
    }

    @Test
    public void userone(){
        //测试自定义切面
        int i = userService.deleteByPrimaryKey("10");
        System.out.println(i);
    }

    @Test
    public void isneret12(){
        int i = guruService.deleteByPrimaryKey("1245");
        System.out.println(i);
    }
}
