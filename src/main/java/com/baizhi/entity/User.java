package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String id;

    private String phoneNumber;

    private String password;

    private String name;

    private String dharma;

    private String headImg;

    private String sex;

    private String address;

    private String sign;

    private String guruId;

    private Date lastDate;

    private Date createDate;

    private String status;

    private String salt;

    private String other;


}