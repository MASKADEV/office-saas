package com.project.office.repository;


import com.project.office.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepoository extends JpaRepository<Team, Long> {
}
