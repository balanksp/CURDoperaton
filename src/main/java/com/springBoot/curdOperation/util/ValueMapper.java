package com.springBoot.curdOperation.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBoot.curdOperation.dto.TaskDetailsRequestDTO;
import com.springBoot.curdOperation.dto.TaskDetailsResponseDTO;
import com.springBoot.curdOperation.model.TaskDetails;

public class ValueMapper {
    public static TaskDetails convertToEntity(TaskDetailsRequestDTO taskDetailsRequestDTO){
        TaskDetails taskDetails= new TaskDetails();
        taskDetails.setSeverity(taskDetailsRequestDTO.getSeverity());
        taskDetails.setAssignee(taskDetailsRequestDTO.getAssignee());
        taskDetails.setDescription(taskDetailsRequestDTO.getDescription());
        taskDetails.setStoryPoint(taskDetailsRequestDTO.getStoryPoint());
        return taskDetails;
    }

    public static TaskDetailsResponseDTO convertToDTO (TaskDetails taskDetails ){
        TaskDetailsResponseDTO taskDetailsResponseDTO = new TaskDetailsResponseDTO();
        taskDetailsResponseDTO.setSeverity(taskDetails.getSeverity());
        taskDetailsResponseDTO.setDesc(taskDetails.getDescription());
        taskDetailsResponseDTO.setAssignee(taskDetails.getAssignee());
        taskDetailsResponseDTO.setStoryPoint(taskDetails.getStoryPoint());
        return taskDetailsResponseDTO;

    } 

    public static String jsonAsString (Object obj){
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }
}
