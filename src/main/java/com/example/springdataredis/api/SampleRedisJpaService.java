package com.example.springdataredis.api;

import com.example.springdataredis.model.Student;
import com.example.springdataredis.repo.StudentRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleRedisJpaService {
    @Autowired
    private StudentRepository studentRepository;
    public static DozerBeanMapper mapper = new DozerBeanMapper();

    public Student add(Student student) {
        studentRepository.save(student);
        return get(student.getId());
    }

    public List<Student> fillAll() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public Student get(String id) {
        final Student retrievedStudent = studentRepository.findById(id).orElse(null);
        return retrievedStudent;
    }

    public ResponseEntity update(String id, Student student) {
        Student current = get(id);
        if (current == null) return ResponseEntity.badRequest().body("Id not found!");
        mapper.map(student, current);
        studentRepository.save(current);
        return ResponseEntity.ok(get(current.getId()));
    }

    public ResponseEntity delete(String id) {
        Student current = get(id);
        if (current == null) return ResponseEntity.badRequest().body("Id not found!");
        studentRepository.deleteById(id);
        final Student retrievedStudent = studentRepository.findById(id).orElse(null);
        if (retrievedStudent == null) return ResponseEntity.ok("Success");
        return ResponseEntity.badRequest().body("Fail!");
    }

}
