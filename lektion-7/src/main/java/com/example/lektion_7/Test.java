package com.example.lektion_7;

import com.example.lektion_7.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Test {

    @Autowired
    StudentRepository studentRepository;

    public void findStudentByIdTest(Long Id) {

        Optional<Student> student = studentRepository.findStudentById(Id);

        if (student.isPresent()) {
            Student studentTest = student.get();
            System.out.println("Found student: " + studentTest.getFirstName() + " " + studentTest.getLastName());
        } else {
            System.out.println("Student not found with ID" + Id);
        }
    }


}
