package com.baizhi.Text;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.UserMapper;
import com.baizhi.entity.Moneasy;
import com.baizhi.entity.User;
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
public class utext {
    @Autowired
   private UserMapper userMapper;


    @Test
    public void all(){
        List<Moneasy> queryall = userMapper.queryall();

        List<Map<String,Object>> list=new ArrayList<>();
        for (Moneasy moneasy : queryall) {
            Map<String,Object> map=new HashMap<>();
           map.put("value",moneasy.getCount());
           map.put("name",moneasy.getName());
            System.out.println(map);
            list.add(map);
            System.out.println(list);
        }
        /*//发布消息
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b121a256bfeb4d0e874c71b5a744d092");
        goEasy.publish("address",list.toString());*/
    }

    @Test
    public void test1(){
        User user = userMapper.selectByPrimaryPhone("123456");
        System.out.println(user.getPhoneNumber());

    }
}
