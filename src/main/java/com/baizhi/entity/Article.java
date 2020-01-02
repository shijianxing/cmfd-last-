package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Article {//文章表
    private String id;
    private String title;//标题
    private String author;//作者
    private String content;//内容
    private String  guru_id;
    private Date create_date;//创建时间
    private  String status;
    private  String other;
}
