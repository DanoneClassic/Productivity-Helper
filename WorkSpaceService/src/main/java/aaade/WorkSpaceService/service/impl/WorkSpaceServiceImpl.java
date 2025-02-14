package aaade.WorkSpaceService.service.impl;

import aaade.WorkSpaceService.exception.workspace.WorkspaceNotDeletedException;
import aaade.WorkSpaceService.exception.workspace.WorkspaceNotSavedException;
import aaade.WorkSpaceService.exception.workspace.WorkspaceNotUpdatedException;
import aaade.WorkSpaceService.exception.workspace.WorkspacesNotFoundException;
import aaade.WorkSpaceService.model.Task;
import aaade.WorkSpaceService.model.WorkSpace;
import aaade.WorkSpaceService.repository.WorkSpaceRepository;
import aaade.WorkSpaceService.service.WorkSpaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkSpaceServiceImpl implements WorkSpaceService {

    private final WorkSpaceRepository workSpaceRepository;
    private final TaskServiceImpl taskService;

    public List<WorkSpace> getAll() {
        List<WorkSpace> workSpaces = workSpaceRepository.findAll();
        if (workSpaces.isEmpty()) {
            return Collections.emptyList();
        }
        return workSpaces;
    }

    public WorkSpace getWorkSpaceById(Long id) {
        return workSpaceRepository.findById(id).orElse(null);
    }

    public WorkSpace save(WorkSpace workSpace) {
        try {
            return workSpaceRepository.save(workSpace);
        } catch (Exception e) {
            throw new WorkspaceNotSavedException("Workspace could not be saved: " + e.getMessage());
        }
    }

    public WorkSpace update(WorkSpace workSpace) {
        try {
            return workSpaceRepository.save(workSpace);
        } catch (Exception e) {
            throw new WorkspaceNotUpdatedException("Workspace could not be updated: " + e.getMessage());
        }
    }

    public void deleteWorkSpaceById(Long id) {
        try {
            WorkSpace toDelete = findWorkSpaceById(id);
            workSpaceRepository.delete(toDelete);
        } catch (Exception e) {
            throw new WorkspaceNotDeletedException("Workspace could not be deleted: " + e.getMessage());
        }
    }

    protected WorkSpace findWorkSpaceById(Long id) {
        return workSpaceRepository.findById(id).orElse(null);
    }

    public WorkSpace addTaskToWorkspace(Long workspaceId, Task task) {
        WorkSpace workspace = findWorkSpaceById(workspaceId);
        if (workspace == null) {
            throw new WorkspacesNotFoundException("Workspace not found");
        }
        task.setWorkSpace(workspace);
        taskService.save(task);
        workspace.getTasks().add(task);
        return workSpaceRepository.save(workspace);
    }

    public WorkSpace removeTaskFromWorkspace(Long workspaceId, Long taskId) {
        WorkSpace workspace = findWorkSpaceById(workspaceId);
        if (workspace == null) {
            throw new WorkspacesNotFoundException("Workspace not found");
        }
        Task task = taskService.findTaskById(taskId);
        workspace.getTasks().remove(task);
        taskService.deleteTaskById(taskId);
        return workSpaceRepository.save(workspace);
    }

    public List<WorkSpace> getWorkSpacesByUserId(Long userId) {
        List<WorkSpace> workSpaces = workSpaceRepository.findByOwnerId(userId);
        if (workSpaces.isEmpty()) {
            return Collections.emptyList();
        }
        return workSpaces;
    }
}