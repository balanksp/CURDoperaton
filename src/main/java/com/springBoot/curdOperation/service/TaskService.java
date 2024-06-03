package com.springBoot.curdOperation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springBoot.curdOperation.dto.TaskDetailsRequestDTO;
import com.springBoot.curdOperation.dto.TaskDetailsResponseDTO;
import com.springBoot.curdOperation.model.TaskDetails;

@Service
public interface TaskService {
    public TaskDetails addTask(TaskDetails taskDetails);

  // used that best practices
    public TaskDetailsResponseDTO addTask(TaskDetailsRequestDTO taskDetailsRequestDTO);

  // used that best practices
  public List<TaskDetailsResponseDTO> getTotal_TaskDetails( );
    
    public List<TaskDetails> getAll_TaskDetails( );

    public TaskDetails modifyTaskDetails(TaskDetails taskDetails);

    public TaskDetails getTaskDetailByTaskId(String taskId);

    public List<TaskDetails> getTaskDetailsBySeverity(int severity);

    public List<TaskDetails> getTaskDetailByAssignee(String assignee);

    public void deleteTaskDetails(String taskId);

    
}
