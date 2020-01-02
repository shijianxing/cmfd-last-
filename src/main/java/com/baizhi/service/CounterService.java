package com.baizhi.service;

import com.baizhi.entity.Counter;

import java.util.List;

public interface CounterService {
    //根据用户id查询

    List<Counter> selectByPrimaryUserId(String userid);
    //查全部
    List<Counter> selectByPrimaryAll();
}
