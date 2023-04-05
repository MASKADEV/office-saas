package com.project.office.dto.request;

import com.project.office.dto.enumuration.TaskStatus;
import lombok.Data;

@Data
public class TaskModal {
    private String title;

    private String description;

    private String taskStatus;

}
