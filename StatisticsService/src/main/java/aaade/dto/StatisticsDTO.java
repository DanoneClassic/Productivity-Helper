package aaade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticsDTO {
    private long totalTasks;
    private long completedTasks;
    private long inProgressTasks;
    private long newTasks;
    private long closedTasks;
    private long totalWorkspaces;
    private long tasksPerUser;
}
