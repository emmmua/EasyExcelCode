package org.example.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 学生实体类
 */
// 基于lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    /**
     * id
     */
    @ExcelProperty("ID")
    @ExcelIgnore
    private String id;

    /**
     * 学生姓名
     */
    @ExcelProperty("姓名")
    @ColumnWidth(20)
    private String name;
    /**
     * 学生性别
     */
    @ExcelProperty("性别")
    private String gender;

    /**
     * 学生出生日期
     */
    @ExcelProperty("生日")
    @ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;
}