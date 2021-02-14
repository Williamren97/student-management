package com.example.studentmanagement.exception;

/**
 * @Author rw
 * @Date: 2021/2/14 21:43
 * @Version 1.0
 */
public class StudentNonExistException  extends  RuntimeException {
    public StudentNonExistException(String message) {
        super(message);
    }
}
