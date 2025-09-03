package com.linusholmer.lektion_6;

import com.linusholmer.lektion_6.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> studentList = studentRepository.findAll();

        if (studentList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(studentList);
    }

    @PostMapping
    public ResponseEntity<Student> insertStudentToStudents (
            @RequestBody Student student
    ) {

        return ResponseEntity.status(201).body(studentRepository.save(student));
    }
}
