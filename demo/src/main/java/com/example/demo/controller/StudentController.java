package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import com.example.demo.dao.CourseRepository;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PostMapping("/addCourse")
    public Course addCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PostMapping("/addStudents")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        return studentService.createStudents(students);
    }

    @PostMapping("/addCourses")
    public List<Course> addCourses(@RequestBody List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PutMapping("/updatestudent")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }
    @PutMapping("/student/{id}/assignCourses")
    public Student assignCoursesToStudent(@PathVariable int id, @RequestBody Set<Integer> courseIds) {
        return studentService.assignCoursesToStudent(id, courseIds);
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudentById(id);
    }

}