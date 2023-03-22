package com.project.office.service;

import com.project.office.entity.Team;
import com.project.office.repository.TeamRepoository;
import com.project.office.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    private TeamRepoository teamRepoository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Team createTeam(Team team) {
        return null;
    }

    @Override
    public Team findById(Long id) {
        return null;
    }

    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Team updateTeam(Long id, Team team) {
        return null;
    }

    @Override
    public void deleteTeam(Long id) {

    }

    @Override
    public void addMember(Long teamId, Long userId) {

    }

    @Override
    public void removeMember(Long teamId, Long userId) {

    }
}
