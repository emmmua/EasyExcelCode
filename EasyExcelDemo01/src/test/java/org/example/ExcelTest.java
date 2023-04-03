package org.example;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import org.example.entity.Student;
import org.example.listener.StudentListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelTest {

    /**
     * 工作簿：一个excel文件就是一个工作簿
     * 工作表：一个工作簿中可以有多个工作表（sheet）
     */
    @Test
    public void test01() {

        /**
         * 构建一个读的工作簿
         *
         * @param inputStream  要读的文件路径
         * @param head         文件中每一行数据要存储到的实体类的类型的class
         * @param readListener 读写监听器，每读一行内容，都会调用一次该对象的invoke，在invoke可以操作使用读取到的数据
         * @return Excel reader builder.
         */
        // 1、获得一个工作簿对象
        ExcelReaderBuilder readWorkBook = EasyExcel.read("E:\\百度云下载文件\\EasyExcel公开课资料\\杭州黑马在线202003班学员信息表.xlsx",
                Student.class,
                new StudentListener());

        // 2、获得一个工作表对象
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet();

        // 3、读取工作表中的内容
        sheet.doRead();
    }


    /**
     * 需求：单实体导出
     * 导出多个学生对象到Excel表格
     * 包含如下列：姓名、性别、出生日期
     * 模板详见：杭州黑马在线202003班学员信息.xlsx
     */
    @Test
    public void test02() {
        /**
         * 构建一个写的工作簿对象
         *
         * @param pathName 要写入的文件路径
         * @param head     封装写入数据的实体的类型
         * @return 写的工作簿对象
         */
        // 1、工作簿对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write("E:\\百度云下载文件\\EasyExcel公开课资料\\Fivk_2023001班学员信息-write.xlsx", Student.class);

        // 2、工作表对象
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();

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
