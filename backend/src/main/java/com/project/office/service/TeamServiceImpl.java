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
        Team results = teamRepoository.save(team);
        return results;
    }

    @Override
    public Team findById(Long id) {
        return teamRepoository.findById(id).orElse(null);
    }

    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Team updateTeam(Long id, Team team) {
        Team team_found = teamRepoository.save(team);
        return team_found;
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
