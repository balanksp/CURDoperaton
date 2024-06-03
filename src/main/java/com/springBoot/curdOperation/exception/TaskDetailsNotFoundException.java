package com.springBoot.curdOperation.exception;

public class TaskDetailsNotFoundException extends RuntimeException{
    public TaskDetailsNotFoundException(String message) {
        super(message);
    }
}
