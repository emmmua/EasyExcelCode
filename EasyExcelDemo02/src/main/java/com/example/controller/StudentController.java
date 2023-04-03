package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.entity.Student;
import com.example.listener.StudentListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentListener studentListener;

    @GetMapping("/read")
    @ResponseBody
    public String readExcel(MultipartFile uploadExcel) {
        try {

            // 1、拿到工作簿
            ExcelReaderBuilder readWorkBook = EasyExcel.read(uploadExcel.getInputStream(), Student.class, studentListener);

            // 2、获取工作表
            ExcelReaderSheetBuilder sheet = readWorkBook.sheet();

            // 3、读取
            sheet.doRead();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }


    @GetMapping("/writer")
    public void writer(HttpServletResponse response) throws IOException {


        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");
        /**
         * 构建一个写的工作簿对象
         *
         * @param pathName 要写入的文件路径
         * @param head     封装写入数据的实体的类型
         * @return 写的工作簿对象
         */
        // 1、工作簿对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(response.getOutputStream(), Student.class);

        // 2、工作表对象
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet("模板");

        // 3、写出数据
        List<Student> studentList = initData();

        sheet.doWrite(studentList);
    }


    private static List<Student> initData() {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student data = new Student();
            data.setName("Fivk学号0" + i);
            data.setBirthday(new Date());
            data.setGender("男");
            students.add(data);
        }
        return students;
    }
}
