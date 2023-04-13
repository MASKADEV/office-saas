package com.project.office.repository;

import com.project.office.entity.Task;
import com.project.office.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTeam(Team team);
}
