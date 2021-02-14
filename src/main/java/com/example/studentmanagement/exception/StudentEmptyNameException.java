package com.example.studentmanagement.exception;

/**
 * @Author rw
 * @Date: 2021/2/14 21:39
 * @Version 1.0
 */
public class StudentEmptyNameException extends RuntimeException {
    public StudentEmptyNameException(String message) {
        super(message);
    }
}
