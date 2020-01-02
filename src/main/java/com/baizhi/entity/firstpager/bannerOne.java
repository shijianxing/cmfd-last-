package com.baizhi.entity.firstpager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bannerOne {

    private String id;
    private String  thumbnail;//图片
    private  String desc;//描述
}
