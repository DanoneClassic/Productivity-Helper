package aaade.service;

import reactor.core.publisher.Mono;

/**
 * WorkspaceStatisticsService interface provides methods for fetching workspace statistics.
 */
public interface WorkspaceStatisticsService {

    /**
     * Retrieves the total number of workspaces for a specific user.
     *
     * @param userId the ID of the user
     * @return a Mono of Long representing the total number of workspaces
     */
    Mono<Long> getTotalWorkspaces(Long userId);

    /**
     * Retrieves the number of tasks per user.
     *
     * @param userId the ID of the user
     * @return a Mono of Long representing the number of tasks per user
     */
    Mono<Long> getTasksPerUser(Long userId);
}