package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("login")
    public String login(HttpSession session, Admin admin,String enCode){
        //管理员登录
        //判断用户名是否为空
        String error;
        //获得验证码
        String  code = (String) session.getAttribute("code");
        if (admin.getUsername() == null){
            admin.setUsername("error");
        }
        Admin admin1 = adminService.quaryByname(admin.getUsername());
        if (admin1==null){
            //用户名不存在
             error="用户不存在或请输入用户名";
             return error;
        }else {
            //用户名存在
            if (admin1.getPassword().equals(admin.getPassword())){
                //密码正确
                //判断验证码是否相等
                if (code.equals(enCode)){
                    //存储登陆对象
                    session.setAttribute("admin",admin1);
                    return "ok";
                }else {
                    error="验证码错误";
                    return error;
                }
            }else {
                error="请输入正确的密码";
                return error;
            }
        }

    }
}
