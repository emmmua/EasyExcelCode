package com.example.service.impl;

import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public void readExcel(List<Student> students) {
        for (Student student : students) {
            System.out.println("student = " + student);
        }
    }
}
