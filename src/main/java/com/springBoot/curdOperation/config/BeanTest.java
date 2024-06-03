package com.springBoot.curdOperation.config;

import org.springframework.context.annotation.Bean;

import com.springBoot.curdOperation.model.TaskDetails;
import com.springBoot.curdOperation.repo.TaskDetailsRepo;

public class BeanTest {
    

    private TaskDetailsRepo taskDetailsRepo;
    
    public BeanTest(TaskDetailsRepo taskDetailsRepo) {
        this.taskDetailsRepo = taskDetailsRepo;
    }

    @Bean
    TaskDetails addTask(TaskDetails taskDetails) {

        return taskDetailsRepo.save(taskDetails);
    }
}
