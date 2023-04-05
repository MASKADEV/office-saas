package com.project.office.service;

import com.project.office.entity.Team;

import java.util.List;

public interface TeamService {
    Team createTeam(Team team);
    Team findById(Long id);
    List<Team> findAll();
    Team updateTeam(Long id, Team team);
    void deleteTeam(Long id);
    void addMember(Long teamId, Long userId);
    void removeMember(Long teamId, Long userId);
}
