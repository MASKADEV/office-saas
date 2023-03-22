package com.project.office.service;

import com.project.office.entity.Task;
import com.project.office.repository.TaskRepository;
import com.project.office.repository.TeamRepoository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamRepoository teamRepository;

    @Override
    public Task createTask(Long teamId, Task task) {
        return null;
    }

    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public List<Task> findAllByTeamId(Long teamId) {
        return null;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }
}
