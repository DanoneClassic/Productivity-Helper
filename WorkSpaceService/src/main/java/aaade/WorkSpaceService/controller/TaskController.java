package aaade.WorkSpaceService.controller;

import aaade.WorkSpaceService.dto.TaskUpdateDTO;
import aaade.WorkSpaceService.model.Task;
import aaade.WorkSpaceService.model.TaskEvent;
import aaade.WorkSpaceService.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TaskController class provides RESTful API endpoints for managing tasks.
 */
@RestController
@RequestMapping("/api/workspaces/tasks")
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;
    /**
     * Creates a new task.
     *
     * @param task the task to create
     */
    @PostMapping("/createTask")
    public void createTask(@RequestBody Task task) {
        Task savedTask = taskService.save(task);
    }

    /**
     * Adds a task to a workspace.
     *
     * @param task the task to add
     */
    @PostMapping("/addTaskToWorkSpace")
    public void addTaskToWorkSpace(@RequestBody Task task) {
        Task savedTask = taskService.save(task);
    }

    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task
     * @return the task with the given ID
     */
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    /**
     * Updates the name of a task.
     *
     * @param taskUpdateDTO the DTO containing the task ID and the new name
     * @return the updated task
     */
    @PutMapping("/updateName")
    public Task updateTaskName(@RequestBody TaskUpdateDTO taskUpdateDTO) {
        return taskService.updateTask(taskUpdateDTO.getId(), taskUpdateDTO.getNewName());
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }

    /**
     * Updates the status of a task.
     *
     * @param id the ID of the task to update
     * @param event the event that triggers the status update
     * @return the updated task
     */
    @PostMapping("/{id}/status")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody TaskEvent event) {
        log.info("Updating task status with id: {}", id);
        return taskService.updateStatus(id, event);
    }

    /**
     * Retrieves tasks by workspace ID.
     *
     * @param workspaceId the ID of the workspace
     * @return a list of tasks in the given workspace
     */
    @GetMapping("/workspace/{workspaceId}")
    public List<Task> getTasksByWorkspaceId(@PathVariable Long workspaceId) {
        return taskService.getTasksByWorkspaceId(workspaceId);
    }

    /**
     * Retrieves tasks for a specific period.
     *
     * @param startDate the start date of the period
     * @param endDate the end date of the period
     * @param userId the ID of the user
     * @return a list of tasks for the given period and user
     */
    @GetMapping("/statistics")
    public List<Task> getTasksForStatistics(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long userId) {
        return taskService.getTasksForPeriod(startDate, endDate, userId);
    }

}