package com.baizhi.entity.firstpager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleOne {
    private String thumbnail;//图片
    private String title;       //标题
    private  Integer score=4;   //分数
    private  String author;     //作者
    private Integer set_count=12;
    private  String brief;
    private Date  create_date;

}
