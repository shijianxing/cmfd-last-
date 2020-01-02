package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Guru implements Serializable {
    private String id;

    private String headImg;

    private String name;

    private String dharma;

    private String sex;

    private Date createDate;

    private String status;

    private String other;


}