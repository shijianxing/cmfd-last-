package com.baizhi.service;


import com.baizhi.entity.Chapter;
import com.baizhi.entity.firstpager.ChapterOne;

import java.util.List;
import java.util.Map;

public interface ChapterServler {
    public Map<String,Object> queryAll(Integer page,Integer rows,String id);
    //添加 章节
    public Map<String,Object> insertBychapter(Chapter chapter,String albunId);
    //修改
    void updateChapter(Chapter chapter);
    //批量删除
    void deleteBatchByname(String[] id);
    //接口文档
    public List<ChapterOne> queryfirstALL(String id);
    //接口文档显密法要
    public List<ChapterOne> queryAll();

}
