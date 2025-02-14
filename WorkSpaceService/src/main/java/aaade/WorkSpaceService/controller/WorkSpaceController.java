package aaade.WorkSpaceService.controller;

import aaade.WorkSpaceService.model.Task;
import aaade.WorkSpaceService.model.WorkSpace;
import aaade.WorkSpaceService.service.WorkSpaceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WorkSpaceController class provides RESTful API endpoints for managing workspaces.
 */
@RestController
@RequestMapping("/api/workspaces")
@AllArgsConstructor
@Slf4j
public class WorkSpaceController {
    private final WorkSpaceService workSpaceService;

    /**
     * Retrieves all workspaces.
     *
     * @return a list of all workspaces
     */
    @GetMapping
    public List<WorkSpace> getAll() {
        return workSpaceService.getAll();
    }

    /**
     * Retrieves a workspace by its ID.
     *
     * @param id the ID of the workspace
     * @return the workspace with the given ID
     */
    @GetMapping("/{id}")
    public WorkSpace getWorkSpaceById(@PathVariable Long id) {
        return workSpaceService.getWorkSpaceById(id);
    }

    /**
     * Saves a new workspace.
     *
     * @param workSpace the workspace to save
     * @return the saved workspace
     */
    @PostMapping("/save")
    public WorkSpace save(@RequestBody WorkSpace workSpace) {
        return workSpaceService.save(workSpace);
    }

    /**
     * Updates a workspace.
     *
     * @param workSpace the workspace to update
     * @return the updated workspace
     */
    @PutMapping("/update")
    public WorkSpace update(@RequestBody WorkSpace workSpace) {
        return workSpaceService.update(workSpace);
    }

    /**
     * Deletes a workspace by its ID.
     *
     * @param id the ID of the workspace to delete
     */
    @DeleteMapping("/deleteWorkSpaceById/{id}")
    public void deleteWorkSpaceById(@PathVariable Long id) {
        workSpaceService.deleteWorkSpaceById(id);
    }

    /**
     * Adds a task to a workspace.
     *
     * @param workspaceId the ID of the workspace
     * @param task        the task to add
     * @return the updated workspace
     */
    @PostMapping("/{workspaceId}/tasks")
    public WorkSpace addTaskToWorkspace(@PathVariable Long workspaceId, @RequestBody Task task) {
        return workSpaceService.addTaskToWorkspace(workspaceId, task);
    }

    /**
     * Removes a task from a workspace.
     *
     * @param workspaceId the ID of the workspace
     * @param taskId      the ID of the task to remove
     * @return the updated workspace
     */
    @DeleteMapping("/{workspaceId}/tasks/{taskId}")
    public WorkSpace removeTaskFromWorkspace(@PathVariable Long workspaceId, @PathVariable Long taskId) {
        return workSpaceService.removeTaskFromWorkspace(workspaceId, taskId);
    }

    /**
     * Retrieves workspaces by user ID.
     *
     * @param userId the ID of the user
     * @return a list of workspaces for the given user
     */
    @GetMapping("/user/{userId}")
    public List<WorkSpace> getWorkSpacesByUserId(@PathVariable Long userId) {
        return workSpaceService.getWorkSpacesByUserId(userId);
    }
}