package com.baizhi.controller.echerts;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("echerts")
public class AdminEcherts {
    @Autowired
    private AdminService adminService;

    @RequestMapping("ALLecherts")
    public List<Map<String,Object>> echerts(){
        //查询出所有的数据
        List<Admin> admins = adminService.queryAll();
        //创建map
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        //建立数组
        String[] city={"天津","上海","重庆","河北","河南","云南","辽宁","黑龙江","湖南","安徽","山东","新疆","江苏","浙江","江西","湖北"};

            for (int i = 0; i <city.length ; i++) {
                map.put("name",city[i]);
                map.put("value",admins.size());
                list.add(map);
            }

        return list;
    }
}
