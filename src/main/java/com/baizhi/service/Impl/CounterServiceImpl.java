package com.baizhi.service.Impl;

import com.baizhi.DAO.CounterMapper;
import com.baizhi.entity.Counter;
import com.baizhi.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterMapper counterMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Counter> selectByPrimaryUserId(String userid) {
        //根据id查询关于 用户的法要

        List<Counter> counters = counterMapper.selectByPrimaryUserId(userid);
        return counters;

    }

    @Override
    public List<Counter> selectByPrimaryAll() {
        //查全部
        return counterMapper.selectByPrimaryAll();
    }
}
