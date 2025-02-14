package aaade.WorkSpaceService.service;

import aaade.WorkSpaceService.model.Task;
import aaade.WorkSpaceService.model.WorkSpace;
import java.util.List;

/**
 * WorkSpaceService interface provides methods for managing workspaces.
 */
public interface WorkSpaceService {

    /**
     * Retrieves all workspaces.
     *
     * @return a list of all workspaces
     */
    List<WorkSpace> getAll();

    /**
     * Retrieves a workspace by its ID.
     *
     * @param id the ID of the workspace
     * @return the workspace with the given ID
     */
    WorkSpace getWorkSpaceById(Long id);

    /**
     * Saves a new workspace.
     *
     * @param workSpace the workspace to save
     * @return the saved workspace
     */
    WorkSpace save(WorkSpace workSpace);

    /**
     * Updates a workspace.
     *
     * @param workSpace the workspace to update
     * @return the updated workspace
     */
    WorkSpace update(WorkSpace workSpace);

    /**
     * Deletes a workspace by its ID.
     *
     * @param id the ID of the workspace to delete
     */
    void deleteWorkSpaceById(Long id);

    /**
     * Adds a task to a workspace.
     *
     * @param workspaceId the ID of the workspace
     * @param task the task to add
     * @return the updated workspace
     */
    WorkSpace addTaskToWorkspace(Long workspaceId, Task task);

    /**
     * Removes a task from a workspace.
     *
     * @param workspaceId the ID of the workspace
     * @param taskId the ID of the task to remove
     * @return the updated workspace
     */
    WorkSpace removeTaskFromWorkspace(Long workspaceId, Long taskId);

    /**
     * Retrieves workspaces by user ID.
     *
     * @param userId the ID of the user
     * @return a list of workspaces for the given user
     */
    List<WorkSpace> getWorkSpacesByUserId(Long userId);
}