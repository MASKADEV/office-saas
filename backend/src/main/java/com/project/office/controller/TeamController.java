package com.project.office.controller;


import com.project.office.dto.request.TeamModal;
import com.project.office.dto.response.ResponseModal;
import com.project.office.entity.Task;
import com.project.office.entity.Team;
import com.project.office.entity.User;
import com.project.office.service.TaskServiceImpl;
import com.project.office.service.TeamServiceImpl;
import com.project.office.service.UserService;
import com.project.office.util.jwt.JwtTokenUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {
    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private UserService userService;

    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/add-team")
    public ResponseEntity<ResponseModal> saveTeam(@RequestBody TeamModal team, @RequestHeader("Authorization") String authorizationHeader){
        String token = jwtTokenUtil.extractUsername(authorizationHeader.substring(7));
        List<User> members = new ArrayList<>();
        User user =  userService.getUser(token);
        members.add(user);
        Team teambody = new Team();
        teambody.setName(team.getName());
        teambody.setMembers(members);
        teamService.createTeam(teambody);
        return ResponseEntity.ok(ResponseModal.builder().message("team has been added!").build());
    }

    @PatchMapping("/update-team")
    public ResponseEntity<ResponseModal> updateTeam(@RequestHeader("Authorization") String authorizationHeader, @RequestBody TeamModal teamModal, @RequestParam("id") String id){
        String token = jwtTokenUtil.extractUsername(authorizationHeader.substring(7));
        int i_d = Integer.parseInt(id);
        long team_id = i_d;
        Team team = teamService.findById(team_id);
        team.setName(teamModal.getName());
        teamService.updateTeam(team_id, team);
        return ResponseEntity.ok(ResponseModal.builder().message("team has been updated").build());
    }

    @GetMapping("/get-teams")
    public ResponseEntity<ResponseModal> getTeams(@RequestHeader("Authorization") String authorizationHeader){
        String token = jwtTokenUtil.extractUsername(authorizationHeader.substring(7));
        User user = userService.getUser(token);
        List<TeamModal> teams = new ArrayList<>();
        teamService.findAll(user).stream().forEach(team -> {
            List<Task> tasks = taskService.findAllByTeamId(team.getId());
            if(tasks != null){
                teams.add(new TeamModal(team.getId(), team.getName(), tasks));
            }else {
                teams.add(new TeamModal(team.getId(), team.getName(), null));
            }
        });
        return ResponseEntity.ok(ResponseModal.builder().results(Map.of("results", teams)).build());
    }

    @DeleteMapping("/delete-teams")
    public ResponseEntity<ResponseModal> deleteTeam(@RequestParam("id") long id){
        teamService.deleteTeam(id);
        return ResponseEntity.ok(ResponseModal.builder().message("team has been deleted").build());
    }
}

