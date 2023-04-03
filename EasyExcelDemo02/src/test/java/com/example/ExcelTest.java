package com.example;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.entity.FillData;
import org.junit.jupiter.api.Test;

public class ExcelTest {

    /**
     * 单组数据填充
     */
    @Test
    public void test01() {
        // 准备模板
        String template = "src/main/resources/static/template/fill_data_template1.xlsx";

        // 创建工作簿对象
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("E:\\百度云下载文件\\EasyExcel公开课资料\\write\\1.Excel填充单组数据.xlsx", FillData.class).withTemplate(template);

        // 创建工作表对象
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();

        // 准备数据
        FillData fillData = new FillData();
        fillData.setName("吴帆");
        fillData.setAge(21);

        // 填充数据
        sheet.doFill(fillData);
    }
}
