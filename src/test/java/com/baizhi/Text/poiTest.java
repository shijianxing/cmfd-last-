package com.baizhi.Text;

import com.baizhi.CmfdShiApplication;
import com.baizhi.DAO.AdminDao;
import com.baizhi.DAO.AlbumDao;
import com.baizhi.DAO.BannerDao;
import com.baizhi.DAO.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.Cleaner;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfdShiApplication.class)
public class poiTest {
    @Autowired
    private BannerDao bannerDao;

    @Test
    public void text1() throws IOException {
    //创建 excle 文件
        HSSFWorkbook workbook = new HSSFWorkbook();
    //创建工作蒲
        HSSFSheet sheet = workbook.createSheet("sheet");
        //创建 行 0：代表的是第一行
        HSSFRow row = sheet.createRow(0);
        //创建 单元格
        HSSFCell cell = row.createCell(0);

        //给单元格赋值
        cell.setCellValue("第一次创建单元格");

        //指定excle 输出的位置以及文件名
        workbook.write(new File("B:/text.xls"));
        workbook.close();
    }

    @Test
    public void text2(){
        //数据库的数据导出
        //创建xls文件的对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(format);

        //创建工作pu
        HSSFSheet select1 = workbook.createSheet("select1");
        select1.setColumnWidth(4,20*256);
        select1.setColumnWidth(0,40*256);
        //创建 样式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)10);//单元格 大小
        font.setBold(true);
        font.setColor(Font.COLOR_RED);//颜色
        font.setFontName("微软雅黑");//设置字体

        //创建风格对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//居中

        //创建行
        HSSFRow row = select1.createRow(0);
        //自定义标题行
        String [] titles={"编号","标题","状态","头像","创建时间"};
        for (int i = 0; i < titles.length; i++) {
             String title=titles[i];
             //创建列
            HSSFCell cell = row.createCell(i);
            //赋值
            cell.setCellValue(title);
            //把设置的字体样式交个单元格
            cell.setCellStyle(cellStyle);
        }

        //数据库表的数据
        List<Banner> banners = bannerDao.quaryByAll();
        for (int i = 0; i < banners.size(); i++) {
            //创建行
            HSSFRow row1 = select1.createRow(i+1);
            row1.createCell(0).setCellValue(banners.get(i).getId());
            row1.createCell(1).setCellValue(banners.get(i).getTitle());
            row1.createCell(2).setCellValue(banners.get(i).getStatus());
            row1.createCell(3).setCellValue(banners.get(i).getImg());
            /*row.createCell(4).setCellValue(banners.get(i).getCreate_date());*/
            HSSFCell cell = row1.createCell(4);
            cell.setCellValue(banners.get(i).getCreate_date());
            cell.setCellStyle(style);
        }
        try {
            workbook.write(new File("B:/banner.xls"));
            workbook.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
