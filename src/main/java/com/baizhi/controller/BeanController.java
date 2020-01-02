package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BeanController {
    @Autowired
    private BannerServcie bannerService;

    @RequestMapping("pager")
    public Map<String,Object> PagerAll(Integer page,Integer rows){
        Map<String, Object> map = bannerService.PagerByBanner(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public Map<String,Object> exdite(Banner banner, String oper,String[] id){
        Map<String, Object> map = new HashMap<>();
        //判断 jqgrid 的增删改查
        if ("add".equals(oper)){
            //添加数据
            bannerService.insertByBanner(banner);
            //获得添加的id
            String bannerId = banner.getId();
            //返回增加的id用于修改 img
            map.put("bannerId",bannerId);
            return map;
        }else if ("edit".equals(oper)){
           if (banner.getImg()==""){
                banner.setImg(null);
               //修改方法
               bannerService.updateById(banner);

           }else {
               //修改方法
               bannerService.updateById(banner);
               //获得添加的id
               String bannerId = banner.getId();
               //返回增加的id用于修改 img
               map.put("bannerId",bannerId);
               return map;
           }

        }else if ("del".equals(oper)){
            //批量删除
            bannerService.deleteById(id);
        }
        return map;
    }
    @RequestMapping("bannerUpload")
        public void bannerUpload(MultipartFile img, HttpSession session,String bannerId){
            //1. 获得文件上传的绝对路径
            String realPath = session.getServletContext().getRealPath("img");
            //2.判断文件夹是否存在
            File file1 = new File(realPath);
            if (!file1.exists()){
                //文件为空 创建一个文件夹
                file1.mkdirs();
            }
            //获得文件名
            String filename = img.getOriginalFilename();
            //为了防止用户多次上传文件
            String newfilename=new Date().getTime()+"__"+filename;
            //将文件放入文件夹
            try {
                Banner banner=new Banner();
                img.transferTo(new File(realPath,newfilename));
                banner.setImg(newfilename);
                banner.setId(bannerId);
                //调用方法将文件名 传入数据库
                bannerService.updateById(banner);
                System.out.println("-----图片的名字---------"+newfilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
