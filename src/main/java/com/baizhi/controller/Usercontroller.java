package com.baizhi.controller;

import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class Usercontroller {
    @Autowired
    private UserService userService;

    @RequestMapping("addressUser")
    public List<Map<String, Object>> address(){
        List<Map<String, Object>> list = userService.queryByGoeasyALL();
         return list;
    }


}
