package com.springBoot.curdOperation.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springBoot.curdOperation.dto.APIResponse;
import com.springBoot.curdOperation.dto.ErrorDTO;
import com.springBoot.curdOperation.exception.TaskDetailsBusinessException;
import com.springBoot.curdOperation.exception.TaskDetailsNotFoundException;

@RestControllerAdvice
public class TaskDetailsServiceExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        List<ErrorDTO> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getField(), error.getDefaultMessage());
                    errors.add(errorDTO);
                });
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;

    }

    @ExceptionHandler(TaskDetailsBusinessException.class)
    public APIResponse<?> handleServiceException (TaskDetailsBusinessException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
    }
    

@ExceptionHandler(TaskDetailsNotFoundException.class)
public APIResponse<?> handleTaskDetailsNotFoundException (TaskDetailsNotFoundException exception){
    APIResponse<?> serviceResponse = new APIResponse<>();
    serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(Collections.singletonList(new ErrorDTO("", exception.getMessage())));
        return serviceResponse;
}

}
