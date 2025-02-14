package aaade.controller;

import aaade.dto.StatisticsDTO;
import aaade.service.impl.StatisticsFacadeImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * StatisticsController class provides RESTful API endpoints for fetching statistics.
 */
@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
@Slf4j
public class StatisticsController {

    private final StatisticsFacadeImpl statisticsFacade;

    /**
     * Retrieves statistics for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a Mono of StatisticsDTO containing the statistics for the given period and user
     */
    @GetMapping
    public Mono<StatisticsDTO> getStatistics(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long userId) {
        log.info("Getting statistics for period from {} to {} for user with id {}", startDate, endDate, userId);
        return statisticsFacade.getStatisticsForPeriod(startDate, endDate, userId);
    }
}