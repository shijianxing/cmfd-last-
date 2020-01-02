package com.baizhi.service.Impl;

import com.baizhi.DAO.GuruMapper;
import com.baizhi.entity.Guru;
import com.baizhi.entity.Moneasy;
import com.baizhi.service.GuruService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruMapper guruMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        int i = guruMapper.deleteByPrimaryKey(id);


        return i;
    }

    @Override
    public int insert(Guru record) {
        int i = guruMapper.insert(record);


        return i;
    }

    @Override
    public int insertSelective(Guru record) {
        int i = guruMapper.insertSelective(record);


        return i;
    }

    @Override
    public Guru selectByPrimaryKey(String id) {

        return guruMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Guru record) {
        int i = guruMapper.updateByPrimaryKeySelective(record);

        return i;
    }

    @Override
    public int updateByPrimaryKey(Guru record) {

        int i = guruMapper.updateByPrimaryKey(record);



        return i;
    }

    @Override
    public  List<Integer> Modata() {
        List<Integer> list=new ArrayList<>();
        Integer[] mons={1,2,3,4,5,6,7};
        for (Integer integer : mons) {
            Integer modata = guruMapper.Modata(integer);
            list.add(modata);
        }
        return list;
    }

    @Override
    public List<Integer> monther() {
        List<Integer> list = new ArrayList<>();
        List<Moneasy> monther = guruMapper.monther();
        for (Moneasy moneasy : monther) {
            list.add(moneasy.getCount());
        }
        return list;
    }
}
