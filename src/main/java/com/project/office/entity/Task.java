package com.project.office.entity;

import com.project.office.dto.enumuration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status; // TaskStatus is an enum with values like NEW, IN_PROGRESS, COMPLETED, etc.

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
