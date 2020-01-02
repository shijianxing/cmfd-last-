package com.baizhi.DAO;

import com.baizhi.entity.Counter;

import java.util.List;

public interface CounterMapper {
    int deleteByPrimaryKey(String id);

    int insert(Counter record);

    int insertSelective(Counter record);

    Counter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Counter record);

    int updateByPrimaryKey(Counter record);
    List<Counter> selectByPrimaryUserId(String userid);
    List<Counter> selectByPrimaryAll();
}