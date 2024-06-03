package com.springBoot.curdOperation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDetailsResponseDTO {
    
    private String taskId;
    private String desc;
    private int severity;
    private String assignee;
    private int storyPoint;


}
