package com.baizhi.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.service.AdminService;
import com.baizhi.service.BannerServcie;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("poi")
public class AdminPoi {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BannerServcie bannerServcie;

    @RequestMapping("adminOUTpoi")
    public String Outpoi(){
      //导出 数据库的数据
        //创建空的 excle文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建空的工作蒲
        HSSFSheet admin = workbook.createSheet("admin");
        //创建字体 设置字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)10);//字体长度
        font.setFontName("楷体");//字体
        font.setColor(Font.COLOR_RED);//字体颜色

        //创建样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//居中


        //创建创建row 行
        HSSFRow row = admin.createRow(0);
        //设计表头
        String[] titles={"编号","名字","密码"};
        for (int i = 0; i < titles.length; i++) {
            //创建列
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle);
        }

        //从数据库添加数据
        List<Admin> admins = adminService.queryAll();
        for (int i = 0; i <admins.size() ; i++) {
            //创建行
            HSSFRow row1 = admin.createRow(i+1);
            row1.createCell(0).setCellValue(admins.get(i).getId());
            row1.createCell(1).setCellValue(admins.get(i).getUsername());
            row1.createCell(2).setCellValue(admins.get(i).getPassword());
        }
        //向外输出文件
        try {
            workbook.write(new File("B:/Admin.xls"));
            //关流
            workbook.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        }
    }

    @RequestMapping("inpoi")
    public void InPoi(MultipartFile myexcle){
        /*
        * 导入 word文件
        * */

        //接收用户文件的流
        try {
            InputStream inputStream = myexcle.getInputStream();
            //获得用户上传的excle 的流
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            //获得工作蒲
            HSSFSheet select = workbook.getSheet("select");
            //获取工作蒲取到的最后一行
            ArrayList<Admin> admins = new ArrayList<>();
            //获取最后一行
            int lastRowNum = select.getLastRowNum();
            //利用返回最后一行的长度 遍历获得所有的行的
            for (int i = 1; i <=lastRowNum ; i++) {//这里 必须等于最后一行的长度否者会丢失数据

                //因为第一行是标题 获取第二行的数据
                String id = select.getRow(i).getCell(0).getStringCellValue();
                String username=select.getRow(i).getCell(1).getStringCellValue();
                String password= select.getRow(i).getCell(2).getStringCellValue();
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setId(id);
                admin.setPassword(password);
                admins.add(admin);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("easyOutpoi")
    public String outPoi(){
        //easypoi 导出poi的数据
        File savefile = new File("B:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        //查询所有轮播图
        List<Banner> list = bannerServcie.queryAll();
        for (Banner banner : list) {
            //给图片的赋值 绝对路径
            //B:\第二阶段数据库的学习\框架阶段\IDEACode\cmfd-last\src\main\webapp\img
            banner.setImg("B:\\第二阶段数据库的学习\\框架阶段\\IDEACode\\cmfd-last\\src\\main\\webapp\\img\\"+banner.getImg());
            System.out.println(banner.getImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),
                Banner .class, list);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("B:/excel/banner.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(fos);
            fos.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        }
    }

    @RequestMapping("easyInpoi")
    public String Inpoi(MultipartFile myexcle, HttpSession session){
        //excle导入
        try {
            //获得传入文件的名字
            //String filename = myexcle.getOriginalFilename();

            //图片
            ImportParams params = new ImportParams();
            params.setNeedSave(true);
            params.setTitleRows(0);   //标题行
            params.setHeadRows(1);    //表头
            long start = new Date().getTime();
            //修改默认保存图片
            params.setSaveUrl("/updown/Inpooi");
            List<Banner> result = ExcelImportUtil.importExcel(
                    new FileInputStream("B:\\excel\\Inpoi.xls"),
                    Banner.class, params);
        //测试
            for (Banner banner : result) {
                System.out.println("-----------------导入数据的--------------------"+banner);
            }
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "no";
        }
    }
    // ImportParams params = new ImportParams();
    //        params.setTitleRows(1);   //标题行
    //        params.setHeadRows(1);    //表头
    //        List<Student> list = ExcelImportUtil.importExcel(
    //                new FileInputStream("D:/student.xls"),
    //                Student.class, params);
    //
    //        for (Student student : list) {
    //            System.out.println(student);
    //        }

}
