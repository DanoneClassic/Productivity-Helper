package aaade.service.impl;

import aaade.dto.StatisticsDTO;
import aaade.service.StatisticsFacade;
import aaade.service.TaskStatisticsService;
import aaade.service.WorkspaceStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class StatisticsFacadeImpl implements StatisticsFacade {

    private final TaskStatisticsService taskStatisticsService;
    private final WorkspaceStatisticsService workSpaceStatisticsService;

    @Override
    public Mono<StatisticsDTO> getStatisticsForPeriod(String startDate, String endDate, Long userId) {
        Mono<Long> totalTasks = taskStatisticsService.getTotalTasks(startDate, endDate, userId);
        Mono<Long> completedTasks = taskStatisticsService.getCompletedTasks(startDate, endDate, userId);
        Mono<Long> inProgressTasks = taskStatisticsService.getInProgressTasks(startDate, endDate, userId);
        Mono<Long> newTasks = taskStatisticsService.getNewTasks(startDate, endDate, userId);
        Mono<Long> closedTasks = taskStatisticsService.getClosedTasks(startDate, endDate, userId);
        Mono<Long> totalWorkspaces = workSpaceStatisticsService.getTotalWorkspaces(userId);
        Mono<Long> tasksPerUser = workSpaceStatisticsService.getTasksPerUser(userId);

        return Mono.zip(totalTasks, completedTasks, inProgressTasks, newTasks, closedTasks, totalWorkspaces, tasksPerUser)
                .map(tuple -> new StatisticsDTO(tuple.getT1(), tuple.getT2(), tuple.getT3(), tuple.getT4(), tuple.getT5(), tuple.getT6(), tuple.getT7()));
    }
}