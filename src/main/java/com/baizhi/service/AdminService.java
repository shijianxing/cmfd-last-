package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin quaryByname(String username);
    List<Admin> queryAll();

}
