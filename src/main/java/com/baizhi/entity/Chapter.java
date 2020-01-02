package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chapter {
    private  String id ;          //小专辑表
    private  String title;       //标题
    private  String album_id;    //章节表的id
    private  String size;        //文件大小
    private  String duration;    //时长
    private  String  src;        //音频
    private  String  status;     //状态
    private  String  other;      //多余字段
}
