package com.baizhi.Goeasy;

import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;
import java.util.List;

@RequestMapping("goeasy")
@RestController
public class GuruController {
    @Autowired
    private GuruService guruService;

    @RequestMapping("week")
    public List<Integer> echerts(){
        //一直7 星期注册量
        List<Integer> list = guruService.Modata();
        System.out.println(list);
        return list;
    }

    @RequestMapping("mobther")
    public List<Integer> echertsMonther(){
        //用户的一个月活跃量
        List<Integer> monther = guruService.monther();
        System.out.println("-----------ajax---"+monther);
        return monther;
    }
}
