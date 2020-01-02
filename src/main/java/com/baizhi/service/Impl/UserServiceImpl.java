package com.baizhi.service.Impl;

import com.baizhi.DAO.UserMapper;
import com.baizhi.entity.Moneasy;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
   @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        //用户删除
        return  userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        record.setId(UUID.randomUUID().toString());

        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryPhone(String phone) {
        //登录


        return null;
    }

    @Override
    public List<Map<String, Object>> queryByGoeasyALL() {

        //用于用户在地图的数量
        List<Moneasy> queryall = userMapper.queryall();
        List<Map<String,Object>> list=new ArrayList<>();
        for (Moneasy moneasy : queryall) {
            Map<String,Object> map=new HashMap<>();
            map.put("value",moneasy.getCount());
            map.put("name",moneasy.getName());
            System.out.println(map);
            list.add(map);
        }
        return list;
    }
}
