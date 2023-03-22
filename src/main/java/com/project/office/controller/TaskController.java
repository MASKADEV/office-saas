package com.project.office.controller;

import com.project.office.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teams/{teamId}/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    // Implement endpoints for task management
}
