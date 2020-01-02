package com.baizhi.DAO;

import com.baizhi.entity.Chapter;
import com.baizhi.entity.firstpager.ArticleOne;
import com.baizhi.entity.firstpager.ChapterOne;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    //根据id查询多个
    List<Chapter> queryByAlum(String id);
    //添加
    void insertChapter(Chapter chapter);
    //修改
    void updateChapter(Chapter chapter);
    //删除
    void deleteBatchByname(String[] id);
    //总条数
    Integer queryCount(String id );
    // 根据album的id 分页查询
    public List<Chapter> queryByIdPagerAll(@Param("start") Integer start, @Param("end") Integer end,@Param("id")String id);

    /*  专辑id 接口文档*/
    public List<ChapterOne> queryfirstALL(String id);

    public List<ChapterOne> queryAll();
}
