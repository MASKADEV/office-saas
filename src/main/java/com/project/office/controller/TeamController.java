package com.project.office.controller;


import com.project.office.dto.request.TeamModal;
import com.project.office.dto.response.ResponseModal;
import com.project.office.entity.Team;
import com.project.office.entity.User;
import com.project.office.service.TeamServiceImpl;
import com.project.office.service.UserService;
import com.project.office.util.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {
    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/add-team")
    public ResponseEntity<ResponseModal> saveTeam(@RequestBody TeamModal team, @RequestHeader("Authorization") String authorizationHeader){
        String token = jwtTokenUtil.extractUsername(authorizationHeader.substring(7));
        Set<User> members = new HashSet<>();
        members.add(userService.getUser(token));
        Team teambody = new Team();
        teambody.setName(team.getName());
        teambody.setMembers(members);
        return ResponseEntity.ok(ResponseModal.builder().results(Map.of("result",teamService.createTeam(teambody))).build());
    }

    @PatchMapping("/update-team")
    public ResponseModal updateTeam(@RequestHeader("Authorization") String authorizationHeader, @RequestBody String email, @RequestParam String id){
        String token = jwtTokenUtil.extractUsername(authorizationHeader.substring(7));
        int i_d = Integer.parseInt(id);
        long team_id = i_d;
        Team team = teamService.findById(team_id);
//        Set<User> members = new HashSet<User>();
//        members.add(userService.getUser("maskadev01"));
//        members.addAll(team.getMembers());
//            team.setMembers(members);
        return ResponseModal.builder().results(Map.of("res", "maska")).build();
//        return ResponseModal.builder().results(Map.of("res", teamService.updateTeam(team.getId(), team))).build();
//        return ResponseModal.builder().statusCode(HttpStatus.NO_CONTENT.value()).build();


    }
}

