package com.project.office.controller;

import com.project.office.dto.enumuration.TaskStatus;
import com.project.office.dto.request.TaskModal;
import com.project.office.dto.response.ResponseModal;
import com.project.office.entity.Task;
import com.project.office.service.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/teams/{teamId}/tasks")
@Slf4j
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/add-task")
    public ResponseEntity<ResponseModal> addTask(@PathVariable("teamId") long team_id, @RequestBody TaskModal payload){
        Task task = new Task();
        task.setTitle(payload.getTitle());
        task.setDescription(payload.getDescription());
        task.setStatus(TaskStatus.valueOf(payload.getTaskStatus()));
        Task res = taskService.createTask(team_id, task);
        if(res != null){
        return ResponseEntity.ok(ResponseModal.builder().results(Map.of("results", res.getTitle())).statusCode(HttpStatus.ACCEPTED.value()).build());
        }else {
            return ResponseEntity.ok(ResponseModal.builder().message("error happen please check results").build());
        }
    }

    @PutMapping("/update-task")
    public ResponseEntity<ResponseModal> updateTask(@RequestParam("id") long id, @RequestBody TaskModal payload){
        Task task = new Task();
        task.setStatus(TaskStatus.valueOf(payload.getTaskStatus()));
        Task res = taskService.updateTask(id, task);
        if(res != null){
            return ResponseEntity.ok(ResponseModal.builder().results(Map.of("results", res.getStatus())).statusCode(HttpStatus.ACCEPTED.value()).build());
        }else {
            return ResponseEntity.ok(ResponseModal.builder().message("error happen please check results").build());
        }
    }
}
