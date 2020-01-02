package com.baizhi.DAO;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminDao {
    //根据名字查询
    Admin quaryByname(String username);

    //查询全部用于poi
    List<Admin> quaryAll();

    //批量添加
    public void queryInsertAll(List<Admin> list);
}
