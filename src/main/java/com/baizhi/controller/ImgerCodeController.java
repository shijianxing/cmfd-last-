package com.baizhi.controller;


import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("code")
public class ImgerCodeController {



    @RequestMapping("getCode")
    public void getcode(HttpServletResponse response, HttpSession session){
        //1.回执验证码中的字符
        String code = ValidateImageCodeUtils.getSecurityCode();
        //获得图片
        BufferedImage image = ValidateImageCodeUtils.createImage(code);
        //获得输出流
        ServletOutputStream outputStream=null;

        //存储验证码
        session.setAttribute("code",code);
        try {
            //通过图片输出流 输出到页面
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png",outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
