package com.baizhi.Text;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @RequestMapping("bannerEdit")
    public Map<String,Object> bannerEdit(String name, String img_src){
        System.out.println(name);
        System.out.println(img_src);
        /*
        * 新添加数据的id
        * */
        Map<String, Object> map = new HashMap<>();
        map.put("bannerId","180");
        return map;
    }

    @RequestMapping("banad")
    public void bannerUpload(MultipartFile img_src,String bannerId){
        System.out.println(img_src.getOriginalFilename());
        System.out.println(bannerId);
    }
}
