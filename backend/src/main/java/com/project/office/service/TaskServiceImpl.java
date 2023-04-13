package com.project.office.service;

import com.project.office.entity.Task;
import com.project.office.entity.Team;
import com.project.office.repository.TaskRepository;
import com.project.office.repository.TeamRepoository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamRepoository teamRepository;

    @Override
    public Task createTask(Long teamId, Task task) {
        try {
            Team team = teamRepository.getById(teamId);
            task.setTeam(team);
            Task res = taskRepository.save(task);
            return res;
        }catch(Exception e){
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public List<Task> findAllByTeamId(Long teamId) {
//        Team team = teamRepository.findById(teamId).orElse(null);
//        if(team != null){
//            List<Task> tasks =taskRepository.findByTeam(team);
//            tasks.stream().forEach(task -> {
//                log.info("hola " + task.getTitle());
//            });
//            return tasks;
//        }else {
            return null;
//        }
    }

    @Override
    public Task updateTask(Long id, Task status) {
        try {
            Task task = taskRepository.getById(id);
            if(task != null){
                return null;
            }
            task.setStatus(status.getStatus());
            Task res = taskRepository.save(task);
            return res;
        }catch(Exception e){
            log.info(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }
}
