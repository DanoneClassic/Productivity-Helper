package aaade.dto;

import lombok.Data;

import java.util.List;

@Data
public class Workspace {
    private Long id;
    private String name;
    private List<Task> tasks;
    private Long ownerId;
}
