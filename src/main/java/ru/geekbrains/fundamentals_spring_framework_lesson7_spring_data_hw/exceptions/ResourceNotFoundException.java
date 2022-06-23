package ru.geekbrains.fundamentals_spring_framework_lesson7_spring_data_hw.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private String message;
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
