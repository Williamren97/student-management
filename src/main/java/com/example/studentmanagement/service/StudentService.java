package com.example.studentmanagement.service;

import com.example.studentmanagement.dao.StudentDao;
import com.example.studentmanagement.dao.UniversityClassDao;
import com.example.studentmanagement.exception.InvalidUniversityClassException;
import com.example.studentmanagement.exception.StudentEmptyNameException;
import com.example.studentmanagement.exception.StudentNonExistException;
import com.example.studentmanagement.mapper.StudentMapper;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author rw
 * @Date: 2021/2/14 21:37
 * @Version 1.0
 */
@Service
public class StudentService {
    private StudentDao studentDao;
    private UniversityClassDao universityClassDao;
    private StudentMapper studentMapper;
    @Autowired
    public StudentService(StudentDao studentDao, UniversityClassDao universityClassDao, StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.universityClassDao = universityClassDao;
        this.studentMapper = studentMapper;
    }

    public Student AddStudent(Student student) {
        if (student.getName().isEmpty()) {
            throw  new StudentEmptyNameException("Student name can not be empty");
        }
        return  studentDao.save(student);
    }

    public  Student updateStudent(Student student) {
        if(student.getId() == null || !studentDao.existsById(student.getId())) {
            throw  new StudentNonExistException("Can not find student id");
        }
        return studentDao.save(student);
    }

    public Student assignClass(Long studentId, Long classId) {
        if(!studentDao.existsById(studentId)) {
            throw  new StudentNonExistException("Can not find student id" + studentId);
        }
        if (!universityClassDao.existsById(classId)) {
            throw  new InvalidUniversityClassException("Can not find class id" + classId);
        }
        Student student = getStudentById(studentId).get();
        UniversityClass universityClass = universityClassDao.findById(classId).get();
        student.setUniversityClass(universityClass);
        return studentDao.save(student);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }

    public List<Student> getStudentByName(String name) {
        return studentDao.findByName(name);
    }

    public List<Student> getStudentsContainStrInNamae(String name) {
        return studentMapper.getStudentsContainStrInNamae("%" + name +"%");
    }

    public List<Student> getStudentsInClass(int year, int number) {
        return studentMapper.getStudentInClass(year,number);
    }


}
