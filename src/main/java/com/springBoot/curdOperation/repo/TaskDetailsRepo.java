package com.springBoot.curdOperation.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.context.annotation.ApplicationScope;

import com.springBoot.curdOperation.model.TaskDetails;

@ApplicationScope
public interface TaskDetailsRepo  extends MongoRepository<TaskDetails,String>{

    List<TaskDetails> findBySeverity(int severity);
    List<TaskDetails> findByAssignee(String assignee);
    
    
}
