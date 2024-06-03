package com.springBoot.curdOperation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailsRequestDTO {
    
    @NotBlank(message = "give a description about that task details")
    private String description;

    @Min(value = 1 , message = "high priority for this task details")
    @Max(value = 3 , message = "low priority for this task details")
    private int severity;

    @NotBlank(message = "task details assignee to this person")
    private String assignee;
    
    @Min(value = 1, message = "story point should be between 1 and 3")
    @Max(value = 10, message = "story point should be between 1 and 3")
    private int storyPoint;
    
}
