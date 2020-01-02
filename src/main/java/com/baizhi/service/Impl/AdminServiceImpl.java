package com.baizhi.service.Impl;

import com.baizhi.DAO.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    //根据username查询 管理员表
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin quaryByname(String username) {
        return  adminDao.quaryByname(username);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Admin> queryAll() {
        List<Admin> admins = adminDao.quaryAll();
        return  admins;
    }
}
