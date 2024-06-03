package com.springBoot.curdOperation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.curdOperation.dto.APIResponse;
import com.springBoot.curdOperation.dto.TaskDetailsRequestDTO;
import com.springBoot.curdOperation.dto.TaskDetailsResponseDTO;
import com.springBoot.curdOperation.model.TaskDetails;
import com.springBoot.curdOperation.service.TaskService;
import com.springBoot.curdOperation.util.ValueMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/taskDetails")
@Slf4j
public class TaskController {

  @Autowired
  TaskService taskService;
  

  // used that best practices
  // @PostMapping("/v1/Add_TaskDetails")
  // public ResponseEntity<APIResponse> addTaskDetails(@RequestBody @Valid TaskDetailsRequestDTO taskDetailsRequestDTO) {
  //   log.info("TaskController:: addTaskDetails {} request the task details",
  //       ValueMapper.jsonAsString(taskDetailsRequestDTO));
  //   TaskDetailsResponseDTO taskDetailsResponseDTO = taskService.addTask(taskDetailsRequestDTO);

  //   // Builder Design pattern had used
  //   APIResponse<TaskDetailsResponseDTO> responseDTO = APIResponse.<TaskDetailsResponseDTO>builder().status("Success")
  //       .results(taskDetailsResponseDTO).build();
  //   log.info("TaskController:: addTaskDetails {} response the task details", ValueMapper.jsonAsString(responseDTO));
  //   return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  // }

  // best practice
  // @GetMapping("/v1/viewAllTaskDetails")
  // public ResponseEntity<APIResponse> viewAllTasks(){
  //   List<TaskDetailsResponseDTO> tasks = taskService.getTotal_TaskDetails();
  //            APIResponse<List<TaskDetailsResponseDTO>> responseDto = APIResponse.<List<TaskDetailsResponseDTO>>builder().status("success").results(tasks).build();
  //  return new ResponseEntity<>(responseDto,HttpStatus.OK);
  // }




  // add the new task details
  @PostMapping("/v1/Add_TaskDetails")
  public ResponseEntity<TaskDetails> addTaskDetails(@RequestBody TaskDetails
  taskDetails) {
    try {
      taskService.addTask(taskDetails);
  return new ResponseEntity<>(taskDetails,HttpStatus.CREATED);
      
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  
  }

   // get all the tasks details
   @GetMapping("/v1/getAll_TaskDetails")
   public List<TaskDetails> viewAllTasksDetails() {
     return taskService.getAll_TaskDetails();
   }

  // modify the existing task details
  @PutMapping("/v1/modify_TaskDetails")
  public TaskDetails editTaskDetails(@RequestBody TaskDetails taskDetails) {
    return taskService.modifyTaskDetails(taskDetails);
  }

  // get the task details by using task id
  @GetMapping("/v1/{taskId}")
  public TaskDetails getTaskDetailsByTaskId(@PathVariable String taskId) {
    log.info("<<<<<<<TaskController::getTaskDetailsByTaskId {}>>>>>>>", taskId);
    return taskService.getTaskDetailByTaskId(taskId);
  }

  // get the task details by using severity text
  @GetMapping("/v1/severity/{severity}")
  public List<TaskDetails> getTaskDetailsBySeverity(@PathVariable int severity) {
    return taskService.getTaskDetailsBySeverity(severity);
  }

  // get the task details by using Assignee text
  @GetMapping("/v1/Assignee/{assignee}")
  public List<TaskDetails> getTaskDetailsByAssignee(@PathVariable String assignee) {
    return taskService.getTaskDetailByAssignee(assignee);
  }

  // delete task details by using task id
  @DeleteMapping("/v1/deleteTaskDetails/{taskId}")
  public String removeTaskDetailsBytaskId(@PathVariable String taskId) {
    taskService.deleteTaskDetails(taskId);
    return "removed this task details:--" + taskId;
  }

  // add the new task details
  @PostMapping("/v1/already_AddedTaskDetails")
  public TaskDetails addTaskDetail(@RequestBody TaskDetails
  taskDetails) {
    TaskDetails add_NewTask = taskService.addTask(taskDetails);
    return add_NewTask;
  }

}
