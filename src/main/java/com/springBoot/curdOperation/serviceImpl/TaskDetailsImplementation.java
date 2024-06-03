package com.springBoot.curdOperation.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.springBoot.curdOperation.dto.TaskDetailsRequestDTO;
import com.springBoot.curdOperation.dto.TaskDetailsResponseDTO;
import com.springBoot.curdOperation.exception.TaskDetailsBusinessException;
import com.springBoot.curdOperation.model.TaskDetails;
import com.springBoot.curdOperation.repo.TaskDetailsRepo;
import com.springBoot.curdOperation.service.TaskService;
import com.springBoot.curdOperation.util.ValueMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@EnableCaching
public class TaskDetailsImplementation implements TaskService {

    private TaskDetailsRepo taskDetailsRepo;

    @Override
    public TaskDetails addTask(TaskDetails taskDetails) {
        return taskDetailsRepo.save(taskDetails);
    }

    @Override
    public List<TaskDetails> getAll_TaskDetails() {
        return taskDetailsRepo.findAll();
    }

    // used that best practices
    @Override
    @Cacheable(value = "taskDetail")
    public List<TaskDetailsResponseDTO> getTotal_TaskDetails() {
        System.out.println("testing the cache one time...");
        List<TaskDetailsResponseDTO> taskDetailsResponseDTOs = null;

        try {

            List<TaskDetails> allTask = taskDetailsRepo.findAll();

            if (!allTask.isEmpty()) {
                taskDetailsResponseDTOs = allTask.stream().map(ValueMapper::convertToDTO)
                        .collect(Collectors.toList());
            } else {
                taskDetailsResponseDTOs = Collections.emptyList();
            }
        } catch (Exception e) {
            throw new TaskDetailsBusinessException("getTotal_TaskDetails");
        }
        return taskDetailsResponseDTOs;
    }

    @Override
    public TaskDetails modifyTaskDetails(TaskDetails taskDetails) {
        TaskDetails existingTask = taskDetailsRepo.findById(taskDetails.getTaskId()).get();
        existingTask.setAssignee(taskDetails.getAssignee());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setSeverity(taskDetails.getSeverity());
        existingTask.setStoryPoint(taskDetails.getStoryPoint());
        return taskDetailsRepo.save(existingTask);

    }

    @Override
    @Cacheable(value = "taskDetail", key = "#taskId", unless = "#result.severity < 1")
    public TaskDetails getTaskDetailByTaskId(String taskId) {

        System.out.println("getTaskDetailByTaskId() ;testing the cache one time...");

        return taskDetailsRepo.findById(taskId).get();

    }

    @Override
    public List<TaskDetails> getTaskDetailsBySeverity(int severity) {
        return taskDetailsRepo.findBySeverity(severity);

    }

    @Override
    public List<TaskDetails> getTaskDetailByAssignee(String assignee) {
        return taskDetailsRepo.findByAssignee(assignee);
    }

    @Override
    public void deleteTaskDetails(String taskId) {
        taskDetailsRepo.delete(taskDetailsRepo.findById(taskId).get());
    }

    // used that best practices
    @Override
    public TaskDetailsResponseDTO addTask(TaskDetailsRequestDTO taskDetailsRequestDTO) {

        TaskDetailsResponseDTO taskDetailsResponseDTO;

        try {

            log.info("TaskDetailsImplementation::addTask ; here only business logic started");
            TaskDetails task = ValueMapper.convertToEntity(taskDetailsRequestDTO);
            log.debug("TaskDetailsImplementation::addTask added the task as request {}",
                    ValueMapper.jsonAsString(taskDetailsRequestDTO));

            log.info("add task are presist in the DB");
            TaskDetails finalTask = taskDetailsRepo.save(task);
            taskDetailsResponseDTO = ValueMapper.convertToDTO(finalTask);
            log.debug("recieved the new tak details response", ValueMapper.jsonAsString(taskDetailsResponseDTO));

        } catch (Exception e) {
            throw new TaskDetailsBusinessException(e.getMessage());
        }
        log.info("method ends : addTask", taskDetailsResponseDTO);
        return taskDetailsResponseDTO;
    }

}
