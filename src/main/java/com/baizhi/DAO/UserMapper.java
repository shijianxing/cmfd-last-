package com.baizhi.DAO;

import com.baizhi.entity.Moneasy;
import com.baizhi.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /* 查询所在城市 的人数 SELECT COUNT(ID),address FROM t_user GROUP BY address  */
    public List<Moneasy> queryall();

    /*  用户登录 */
    public User selectByPrimaryPhone(String phone);
}