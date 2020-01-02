package com.baizhi.entity.firstpager;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ChapterOne {
    private String title;
    private String download_url;
    private  String size;
    private String duration;
}
