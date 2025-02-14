package aaade.service;

import reactor.core.publisher.Mono;

/**
 * TaskStatisticsService interface provides methods for fetching task statistics.
 */
public interface TaskStatisticsService {

    /**
     * Retrieves the total number of tasks for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a Mono of Long representing the total number of tasks
     */
    Mono<Long> getTotalTasks(String startDate, String endDate, Long userId);

    /**
     * Retrieves the number of completed tasks for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a Mono of Long representing the number of completed tasks
     */
    Mono<Long> getCompletedTasks(String startDate, String endDate, Long userId);

    /**
     * Retrieves the number of tasks in progress for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a Mono of Long representing the number of tasks in progress
     */
    Mono<Long> getInProgressTasks(String startDate, String endDate, Long userId);

    /**
     * Retrieves the number of new tasks for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a Mono of Long representing the number of new tasks
     */
    Mono<Long> getNewTasks(String startDate, String endDate, Long userId);

    /**
     * Retrieves the number of closed tasks for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a Mono of Long representing the number of closed tasks
     */
    Mono<Long> getClosedTasks(String startDate, String endDate, Long userId);
}