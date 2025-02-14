package aaade.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Task {

    private Long id;
    private String name;
    private String description;

    private Date data;
    private Workspace workSpace;
    private TaskState state;
}
