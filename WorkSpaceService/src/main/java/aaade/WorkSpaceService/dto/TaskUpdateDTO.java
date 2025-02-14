package aaade.WorkSpaceService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TaskUpdateDTO is a data transfer object class that represents the data to update a task.
 */
@Data
@AllArgsConstructor
public class TaskUpdateDTO {
    private Long id;
    private String newName;
}
