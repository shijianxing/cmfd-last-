package com.baizhi.Text;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.CounterMapper;
import com.baizhi.DAO.UserMapper;
import com.baizhi.entity.Counter;
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
public class countertext {
    @Autowired
    private CounterMapper counterMapper;

    @Test
    public void test1(){
        List<Counter> counters = counterMapper.selectByPrimaryUserId("11");
        for (Counter counter : counters) {
            System.out.println(counter);
        }
    }

    @Test
    public void tes2(){
        List<Counter> counters = counterMapper.selectByPrimaryAll();
        for (Counter counter : counters) {
            System.out.println(counter);
        }
    }
}
