package com.baizhi.PoiIntext;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.CmfdShiApplication;
import com.baizhi.entity.Banner;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes=CmfdShiApplication.class)
@RunWith(SpringRunner.class)
public class Inpoitest {


    @Test
    public void test1() throws IOException {
        //poi练习
        //1.创建excle文件 并且 读入对应的excle
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:/banner.xls"));
        //获得sheet工作蒲
        HSSFSheet select1 = workbook.getSheet("select1");

        //3.获得最后一行的下标
        int lastRowNum = select1.getLastRowNum();
        //创建list数组
        ArrayList<Banner> banners = new ArrayList<>();
        //创建一个for循环 用 获得文件的最后的下标作为条件
        for (int i=0;i<lastRowNum;i++){
            //文件蒲  获得行 具体列 值
            String id = select1.getRow(i).getCell(0).getStringCellValue();
            
        }

    }
    @Test
    public void contextLoads() throws Exception {
       /* ImportParams params = new ImportParams();
        params.setTitleRows(1);   //标题行
        params.setHeadRows(1);    //表头
        List<Banner> list = ExcelImportUtil.importExcel(
                new FileInputStream("B:\\excel\\Inpoi.xls"),Banner.class, params);
        for (Banner banner : list) {

            System.out.println(banner);
        }*/
        //图片
        ImportParams params = new ImportParams();
        params.setNeedSave(true);
        params.setTitleRows(0);   //标题行
        params.setHeadRows(1);    //表头
        long start = new Date().getTime();
        //修改默认保存图片
        //params.setSaveUrl("/updown/Inpooi");
        List<Banner> result = ExcelImportUtil.importExcel(
                new FileInputStream("B:\\excel\\Inpoi.xls"),
                Banner.class, params);
        //测试
        for (Banner banner : result) {
            System.out.println("-----------------导入数据的--------------------"+banner);
            String img = banner.getImg();
            String[] split = img.split("\\\\");

            System.out.println("---图片----"+split[2]);
        }
    }
}
