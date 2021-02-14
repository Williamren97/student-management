package com.example.studentmanagement.service;

import com.example.studentmanagement.dao.UniversityClassDao;
import com.example.studentmanagement.exception.InvalidUniversityClassException;
import com.example.studentmanagement.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @Author rw
 * @Date: 2021/2/14 22:53
 * @Version 1.0
 */
@Service
public class UniversityClassService {

    private UniversityClassDao universityClassDao;

    @Autowired
    public UniversityClassService(UniversityClassDao universityClassDao) {
        this.universityClassDao = universityClassDao;
    }

    public List<UniversityClass> getAllClasses() {
        return (List<UniversityClass>) universityClassDao.findAll();
    }

    public UniversityClass addClass(UniversityClass universityClass) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (universityClass.getYear() < currentYear) {
            throw new InvalidUniversityClassException("Cannot add class in the past");
        }
        if (universityClass.getYear() > currentYear + 1) {
            throw new InvalidUniversityClassException("Cannot add class in the future");
        }
        return universityClassDao.save(universityClass);
    }
}
