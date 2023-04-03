package org.example.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.example.entity.Student;

/**
 * 读取文档的监听器类
 */
public class StudentListener extends AnalysisEventListener<Student> {

    /**
     * 读写监听器，每读一行内容，都会调用一次该对象的invoke，在invoke可以操作使用读取到的数据
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context analysis context
     */
    @Override
    public void invoke(Student student, AnalysisContext context) {
        System.out.println("student = " + student);
    }

    /**
     * 读取完整个文档之后的方法
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完成");
    }
}
