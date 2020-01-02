package com.baizhi.entity.firstpager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AlbunOne {
    private String id;
    private String thumbnail;
    private String title;
    private String author;
    private  Integer set_count;
    private Date create_date;
    private  Integer type;
}
