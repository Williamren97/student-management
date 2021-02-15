package com.example.studentmanagement.mapper;

import com.example.studentmanagement.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    @Select("SELECT * FROM student WHERE name LIKE#{name}")
    List<Student> getStudentsContainStrInNamae(@Param("name") String name);

    @Select("SELECT * FROM student WHERE university_class_id IN" +
            "(SELECT id FROM university_class WHERE year = #{year} AND number = #{number})")
    List<Student> getStudentInClass(@Param("year") int year, @Param("number") int number);
}
