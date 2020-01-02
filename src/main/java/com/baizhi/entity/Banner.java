package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@ExcelTarget(value = "轮播图")
public class Banner implements Serializable {
    @Excel(name = "编号")
    private  String id;
    @Excel(name = "标题")
    private  String title;       //标题
    @Excel(name = "图片",type = 2,height = 40,width = 40)
    private  String img;         //图片路径
    @Excel(name = "创建时间",format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private   Date  create_date; //创建时间
    @ExcelIgnore
    private  String status;       //状态
    @ExcelIgnore
    private  String other;
}
