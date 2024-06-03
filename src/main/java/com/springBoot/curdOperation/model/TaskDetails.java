package com.springBoot.curdOperation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tasks")
public class TaskDetails {

    @Id
    private String taskId;
    private String description;
    private int severity;
    private String assignee;
    private int storyPoint;
}
