package com.example.lektion_7;

import com.example.lektion_7.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentById(Long Id);
}
