package com.project.office.service;

import com.project.office.entity.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Long teamId, Task task);
    Task findById(Long id);
    List<Task> findAllByTeamId(Long teamId);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
}
