package com.example.studentmanagement.api;

import com.example.studentmanagement.exception.InvalidUniversityClassException;
import com.example.studentmanagement.exception.StudentEmptyNameException;
import com.example.studentmanagement.exception.StudentNonExistException;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author rw
 * @Date: 2021/2/14 21:51
 * @Version 1.0
 */
@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/name")
    public List<Student> getStudents(@RequestParam String name) {
        return studentService.getStudentByName(name);
    }

    @GetMapping("/contain_name")
    public List<Student> getStudentsContainNamae(@RequestParam String name) {
        return studentService.getStudentsContainStrInNamae(name);
    }

    @GetMapping("/class")
    public List<Student> getStudentsContainNamae(@RequestParam int year,
                                                 @RequestParam int number) {
        return studentService.getStudentsInClass(year, number);
    }

    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        try {
            Student saveStudnet = studentService.AddStudent(student);
            return  ResponseEntity.ok("Registered student" + student.toString());
        } catch (StudentEmptyNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "assignclass/{sid}/{cid}")
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId) {
        try {
            Student updatedStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("Assigned class" + updatedStudent.toString());
        } catch (StudentNonExistException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InvalidUniversityClassException e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
