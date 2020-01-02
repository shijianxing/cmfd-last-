package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("kindededitro")
public class KindededitorController {

    @RequestMapping("uploadFileImg")
    public Map<String,Object> filleName(MultipartFile img, HttpServletRequest request, HttpSession session){
        //上传后台的位置
        Map<String,Object> map=new HashMap<String,Object>();
        //上传图片的 文件夹的绝对路径
        String realPath = session.getServletContext().getRealPath("/updown/img");
        //判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()){
            //文件不纯在 创建一个文件夹
            file.mkdirs();
        }
        //为了防止文件重名
        String originalFilename = img.getOriginalFilename();
        //加上时间戳防止重复上传文件重名 覆盖
        String newfilename=new Date().getTime()+"_"+originalFilename;
        //将文件 放入文件夹
        try {
            img.transferTo(new File(realPath,newfilename));
            //给富文本编辑器
            map.put("error",0);
            String scheme = request.getScheme();  //代表 http
            //获得本地的IP 名
            InetAddress localHost = InetAddress.getLocalHost();
            // //PC-20190718ZLAM/192.168.1.156 只获取路径
            String localhost= localHost.toString().split("/")[1];
            //port 端口号
            int serverPort = request.getServerPort();
            //项目名
            String contextPath = request.getContextPath();
            //拼接成 地址
            String url=scheme+"://"+localhost+":"+serverPort+contextPath+"/updown/img"+newfilename;
            map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /*
    *
    * {
    "moveup_dir_path": "",
    "current_dir_path": "",
    "current_url": "/ke4/php/../attached/",
    "total_count": 5,
    "file_list": [
        {
            "is_dir": false,
            "has_file": false,
            "filesize": 208736,
            "dir_path": "",
            "is_photo": true,
            "filetype": "jpg",
            "filename": "1241601537255682809.jpg",
            "datetime": "2018-06-06 00:36:39"
        }
     ]
}
    * */

    @RequestMapping("getAllFileImgs")
    public Map<String,Object> getAllImgs(HttpSession session,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //拿到所有的图片 在图片空间展示所有的图片
        String realPath = session.getServletContext().getRealPath("/updown/img");
        //创建 文件对象
        File file = new File(realPath);
        //获取所有的文件名
        String[] imgs = file.list();
        List<Object> list = new ArrayList<>();
        for (String img : imgs) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("is_dir",false);
            hashMap.put("has_file",false);
            //创建图片文件对象
            File file1 = new File(realPath, img);
            //获取长度
            long length = file1.length();
            hashMap.put("filesize",length);
            hashMap.put("dir_path","");
            hashMap.put("is_photo",true);
            //返回资源名后缀
            String extension = FilenameUtils.getExtension(img);
            hashMap.put("filetype",extension);
            hashMap.put("filename",img);

           //获得时间撮
            String str=img.split("_")[0];
            Long aLong = Long.valueOf(str);
            Date date = new Date(aLong);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            hashMap.put("datetime",format);
            list.add(hashMap);
        }
        map.put("file_list",list);
        map.put("moveup_dir_path","");
        map.put("current_dir_path","");

        /*
         * http://localhost:80/cmfz/upload/img
         * */

        try {
            //获得http
            String scheme = request.getScheme();
            //获得localhost
            InetAddress localHost = InetAddress.getLocalHost();
            //去除pc 前缀
            String  localhost=localHost.toString().split("/")[1];
            int serverPort = request.getServerPort();
            //获取项目名
            String contextPath = request.getContextPath();
            //拼接地址
            String current_url=scheme+"://"+localhost+":"+serverPort+contextPath+"/updown/img/";
            //map集合
            map.put("current_url",current_url);
            map.put("total_count",imgs.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry);
        }
        return map;
    }


}
