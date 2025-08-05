package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.dao.CourseRepository;
import com.example.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentRepository;
import com.example.demo.entity.Student;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> createStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        Student oldStudent=null;
        Optional<Student> optionalstudent=studentRepository.findById(student.getId());
        if(optionalstudent.isPresent()) {
            oldStudent=optionalstudent.get();
            oldStudent.setName(student.getName());
            oldStudent.setEmail(student.getEmail());
            oldStudent.setPassword(student.getPassword());
            studentRepository.save(oldStudent);
        }else {
            return new Student();
        }
        return oldStudent;
    }

    public String deleteStudentById(int id) {
        studentRepository.deleteById(id);
        return "Student got deleted";
    }
    public Student assignCoursesToStudent(int studentId, Set<Integer> courseIds) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) return null;

        Set<Course> courses = new HashSet<>(courseRepository.findAllById(courseIds));
        student.setCourses(courses);
        return studentRepository.save(student);
    }

}