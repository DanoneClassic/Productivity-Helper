package aaade.service.impl;

import aaade.dto.Task;
import aaade.dto.TaskState;
import aaade.service.TaskStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class TaskStatisticsServiceImpl implements TaskStatisticsService {
    private final WebClient taskServiceClient;

    private Flux<Task> getTasks(String startDate, String endDate, Long userId) {
        return taskServiceClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/workspaces/tasks/statistics")
                        .queryParam("startDate", startDate)
                        .queryParam("endDate", endDate)
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToFlux(Task.class)
                .onErrorResume(e -> Flux.empty());
    }

    @Override
    public Mono<Long> getTotalTasks(String startDate, String endDate, Long userId) {
        return getTasks(startDate, endDate, userId).count();
    }

    @Override
    public Mono<Long> getCompletedTasks(String startDate, String endDate, Long userId) {
        return getTasks(startDate, endDate, userId)
                .filter(task -> task.getState() == TaskState.COMPLETED)
                .count();
    }

    @Override
    public Mono<Long> getInProgressTasks(String startDate, String endDate, Long userId) {
        return getTasks(startDate, endDate, userId)
                .filter(task -> task.getState() == TaskState.IN_PROGRESS)
                .count();
    }

    @Override
    public Mono<Long> getNewTasks(String startDate, String endDate, Long userId) {
        return getTasks(startDate, endDate, userId)
                .filter(task -> task.getState() == TaskState.NEW)
                .count();
    }

    @Override
    public Mono<Long> getClosedTasks(String startDate, String endDate, Long userId) {
        return getTasks(startDate, endDate, userId)
                .filter(task -> task.getState() == TaskState.CLOSED)
                .count();
    }
}