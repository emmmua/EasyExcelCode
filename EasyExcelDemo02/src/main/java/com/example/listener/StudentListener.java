package com.example.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Scope("prototype")    // EasyExcel官方要求每次读取都要使用新的Listener，不能是单例的
public class StudentListener extends AnalysisEventListener<Student> {

    @Autowired
    private StudentService studentService;

    ArrayList<Student> students = new ArrayList<Student>();

    // 每读一样，会调用该invoke方法一次
    @Override
    public void invoke(Student student, AnalysisContext context) {
        students.add(student);
        if (students.size() % 5 == 0) {
            studentService.readExcel(students);
            students.clear();
        }

    }

    // 全部读完之后，会调用该方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // TODO......
    }
}
