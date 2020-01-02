package com.baizhi.service;


import com.baizhi.entity.Guru;
import com.baizhi.entity.Moneasy;

import java.util.List;

public interface GuruService {
    int deleteByPrimaryKey(String id);

    int insert(Guru record);

    int insertSelective(Guru record);

    Guru selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Guru record);

    int updateByPrimaryKey(Guru record);
    //查最近七天的用户访问量
    public List<Integer> Modata();
    //上师一年的人数折线图
    List <Integer> monther();
}
