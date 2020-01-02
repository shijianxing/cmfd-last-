package com.baizhi.service.Impl;

import com.baizhi.DAO.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.firstpager.ChapterOne;
import com.baizhi.service.ChapterServler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ChapterServlerImpl implements ChapterServler {
    @Autowired
    private ChapterDao chapterDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows, String id) {
        /*
         * 1.计算起始
         * 2.jqGrid
         * 3.page 当前页
         *   rows 数据
         *   total：总页数
         *   records： 总条数
         * */
        //计算起始页
        Integer start=(page-1)*rows;
        //总条数
        Integer count = chapterDao.queryCount(id);
        //计算页数
        Integer total=count%rows==0?count/rows:count/rows+1;
        //数据
        List<Chapter> chapters = chapterDao.queryByIdPagerAll(start, rows, id);
        System.out.println("-----获取的章节表数据----"+chapters);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("page",page);
        map.put("rows",chapters);
        map.put("total",total);
        map.put("records",count);
        return map;
    }

    @Override
    public Map<String, Object> insertBychapter(Chapter chapter, String albunId) {
        //添加章节
        chapter.setId(UUID.randomUUID().toString());
        chapter.setAlbum_id(albunId);
        chapterDao.insertChapter(chapter);
        //获得添加过后的id
        String chapterId = chapter.getId();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("chapterId",chapterId);

        return map;
    }

    @Override
    public void updateChapter(Chapter chapter) {
        chapterDao.updateChapter(chapter);
    }

    @Override
    public void deleteBatchByname(String[] id) {
        chapterDao.deleteBatchByname(id);
    }

    @Override
    public List<ChapterOne> queryfirstALL(String id) {
        return chapterDao.queryfirstALL(id);
    }

    @Override
    public List<ChapterOne> queryAll() {
        return chapterDao.queryAll();
    }
}
