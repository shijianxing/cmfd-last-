package com.baizhi.config;


import com.baizhi.DAO.GuruMapper;
import com.baizhi.entity.Guru;
import io.goeasy.GoEasy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class EchartsAdvice {
    @Autowired
    private GuruMapper guruMapper;

    @After(value = "execution(int com.baizhi.service.UserService.*(..))")
    public void  UserAdviceByAfter(JoinPoint joinPoint){
        System.out.println("---------------------进入后置通知");

    }

    @After(value = "execution(int com.baizhi.service.GuruService.*(..))")
    public void GuruAdviceByAfter(JoinPoint joinPoint){
        System.out.println("-------------------进入后置通知--------");
        List<Integer> list=new ArrayList<>();
        Integer[] mon={1,2,3,4,5,6,7};
        for (Integer integer : mon) {
            Integer modata = guruMapper.Modata(integer);
            list.add(modata);
        }
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b121a256bfeb4d0e874c71b5a744d092");
        goEasy.publish("gurueasy",list.toString());
    }



}
