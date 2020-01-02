package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterServler;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterServler  chapterServler;
    @Autowired
    private Chapter chapter;

    @RequestMapping("pagerAll")
    public Map<String,Object> pagerALL(Integer page,Integer rows,String id){
        //分页查询
        Map<String, Object> map = chapterServler.queryAll(page, rows, id);
        return map;
    }

    //添加
    @RequestMapping("editChapter")
    public Map<String,Object> edit(Chapter chapter,String albumid,String oper,String[] id){
        Map<String, Object> map=new HashMap<>();
       if ("add".equals(oper)){
           //添加数据
           map = chapterServler.insertBychapter(chapter, albumid);
           return  map;
       }else if ("edit".equals(oper)){
           //判断是否在修改文件
           if (chapter.getSrc()==""){
               chapter.setSrc(null);
               chapterServler.updateChapter(chapter);
               //如果用户没有修改 音频 返回的id为null；
               map.put("chapterId",null);
               return map;
           }else {
            //修改
               //返回一个jqgrid 后续处理
                map.put("chapterId",chapter.getId());
                chapterServler.updateChapter(chapter);
                return map;
           }
       }else  if ("del".equals(oper)){
           //批量删除
           chapterServler.deleteBatchByname(id);

       }
        return  null;
    }

    @RequestMapping("chapterUpload")
    public void chapterUpload(MultipartFile src, String chapterId, HttpSession session){
        System.out.println("---------进入文件上传-------------------");
        //文件上传
        //获得文件的全限定名路径
        String realPath = session.getServletContext().getRealPath("audio");
        //判断文件是否存在
        File file = new File(realPath);
        if (!file.exists()){
            //文件为空 创建文件夹
            file.mkdir();
        }
        //获得文件的名
        String filename = src.getOriginalFilename();
        //防止用户重复上传同样的数据文件重名
        String newfileName=new Date().getTime()+"_"+filename;
        //将上传文件放入文件夹
        try {
            //获得文件大小
            long size = src.getSize();
            double newsize=(double)size;
            Double fileSizeMB = new BigDecimal(newsize/1024/1024).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            String string = fileSizeMB.toString();
            src.transferTo(new File(realPath,newfileName));
            //获得时长
            //读取文件
            AudioFile audioFile = AudioFileIO.read(new File(realPath, newfileName));
            //读取文件头
            AudioHeader header = audioFile.getAudioHeader();
            //获得音频的时长
            int trackLength = header.getTrackLength();//获取文件的长度
            String secound=trackLength%60+"秒";
            String minute=trackLength/60+"分";
            //修改src 字段
            chapter.setSize(string+"MB");
            chapter.setSrc(newfileName);
            chapter.setId(chapterId);
            chapter.setDuration(minute+secound);
            chapterServler.updateChapter(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------文件上传结束-------------");
    }


    //下载
    @RequestMapping("filedown")
    public void filedown(String name, HttpServletResponse response, HttpSession session) {
            //获得文件的绝对路径
        String realPath = session.getServletContext().getRealPath("updown");
        System.out.println("----------------下载文件名是什么----------------"+name);
        //拆分 文件名
        String srcname = name.split("_")[1];
        //文件名文乱码问题
        String encode = null;
        try {
            encode = URLEncoder.encode(srcname, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //附件 形式下载 content-reposition attachment filename
        response.setHeader("content-disposition","attachment;fileName="+encode);
        ServletOutputStream outputStream = null;
        //工具类
        try {
            outputStream = response.getOutputStream();
            FileUtils.copyFile(new File(realPath,encode),outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关流
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
      /*  String realPath = session.getServletContext().getRealPath("updown");
        //获得输入流
        FileInputStream fileInputStream = new FileInputStream(new File(realPath, name));
        //获得文件输出流
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(name,"UTF-8"));
        //io工具类
        IOUtils.copy(fileInputStream,outputStream);
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(outputStream);*/
    }
}
