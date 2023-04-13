package com.project.office.dto.request;

import com.project.office.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamModal {
    private long id;
    private String name;
    private List<Task> tasks;
}
