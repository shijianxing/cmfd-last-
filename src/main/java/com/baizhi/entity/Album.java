package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album {
    private String id;
    private String title;
    private  String img;  //图片
    private  String score; //分数
    private  String author; //作者
    private  String broadcaster;//播音员
    private  String count;  //章节数
    private  String brief;   //内容简要
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private   Date create_date;   //创建时间
    private  String status;   //状态
    private  String other;   //预留字段
}
