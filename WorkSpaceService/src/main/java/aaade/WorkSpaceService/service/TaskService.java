package aaade.WorkSpaceService.service;

import aaade.WorkSpaceService.model.Task;
import aaade.WorkSpaceService.model.TaskEvent;

import java.util.List;

/**
 * TaskService interface provides methods for managing tasks.
 */
public interface TaskService {

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task
     * @return the task with the given ID
     */
    Task getTaskById(Long id);

    /**
     * Saves a new task.
     *
     * @param task the task to save
     * @return the saved task
     */
    Task save(Task task);

    /**
     * Updates the name of a task.
     *
     * @param taskId  the ID of the task to update
     * @param newName the new name for the task
     * @return the updated task
     */
    Task updateTask(Long taskId, String newName);

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     */
    void deleteTaskById(Long id);

    /**
     * Updates the status of a task.
     *
     * @param taskId the ID of the task to update
     * @param event  the event that triggers the status update
     * @return the updated task
     */
    Task updateStatus(Long taskId, TaskEvent event);

    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks
     */
    List<Task> getAllTasks();

    /**
     * Retrieves tasks by workspace ID.
     *
     * @param workSpaceId the ID of the workspace
     * @return a list of tasks in the given workspace
     */
    List<Task> getTasksByWorkspaceId(Long workSpaceId);

    /**
     * Retrieves tasks for a specific period.
     *
     * @param startDate the start date of the period
     * @param endDate   the end date of the period
     * @param userId    the ID of the user
     * @return a list of tasks for the given period and user
     */
    List<Task> getTasksForPeriod(String startDate, String endDate, Long userId);
}