package aaade.service.impl;

import aaade.dto.Workspace;
import aaade.service.WorkspaceStatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class WorkspaceStatisticsServiceImpl implements WorkspaceStatisticsService {

    private final WebClient workSpaceServiceClient;

    private Mono<List<Workspace>> getWorkSpaces(Long userId) {
        return workSpaceServiceClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/workspaces/user/{userId}")
                        .build(userId))
                .retrieve()
                .bodyToFlux(Workspace.class)
                .collectList()
                .onErrorReturn(List.of());
    }

    @Override
    public Mono<Long> getTotalWorkspaces(Long userId) {
        return getWorkSpaces(userId)
                .map(List::size)
                .map(Integer::longValue); // convert to Long
    }


    @Override
    public Mono<Long> getTasksPerUser(Long userId) {
        return getWorkSpaces(userId)
                .flatMapMany(Flux::fromIterable)
                .map(workspace -> workspace.getTasks() != null ? workspace.getTasks().size() : 0)
                .reduce(0L, Long::sum);
    }
}
