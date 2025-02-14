package aaade.service;

import aaade.dto.StatisticsDTO;
import reactor.core.publisher.Mono;

/**
 * StatisticsFacade interface provides methods for fetching statistics.
 */
public interface StatisticsFacade {

    /**
     * Retrieves statistics for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a Mono of StatisticsDTO containing the statistics for the given period and user
     */
    Mono<StatisticsDTO> getStatisticsForPeriod(String startDate, String endDate, Long userId);
}