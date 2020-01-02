package com.baizhi.DAO;

import com.baizhi.entity.Guru;
import com.baizhi.entity.Moneasy;
import io.goeasy.GoEasy;

import java.util.List;

public interface GuruMapper {
    int deleteByPrimaryKey(String id);

    int insert(Guru record);

    int insertSelective(Guru record);

    Guru selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Guru record);

    int updateByPrimaryKey(Guru record);
    public Integer Modata(Integer mon);
    List<Moneasy> monther();
}