package aaade.WorkSpaceService.repository;

import aaade.WorkSpaceService.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Timestamp;
import java.util.List;

/**
 * TaskRepository interface provides methods for querying tasks from the database.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByWorkSpaceId(Long workSpaceId);

    /**
     * Retrieves tasks for a specific period for a specific user.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a list of tasks for the given period and user
     */
    @Query("SELECT t FROM Task t WHERE t.workSpace.ownerId = :userId AND t.data BETWEEN :startDate AND :endDate")
    List<Task> findTasksForPeriod(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("userId") Long userId);
}
