package com.project.office.repository;


import com.project.office.entity.Team;
import com.project.office.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepoository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t JOIN t.members m WHERE m = :user")
    List<Team> findByUser(User user);
}
