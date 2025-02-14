package aaade.WorkSpaceService.service.impl;

import aaade.WorkSpaceService.exception.task.TaskNotFoundException;
import aaade.WorkSpaceService.model.Task;
import aaade.WorkSpaceService.model.TaskEvent;
import aaade.WorkSpaceService.model.TaskState;
import aaade.WorkSpaceService.repository.TaskRepository;
import aaade.WorkSpaceService.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final StateMachine<TaskState, TaskEvent> stateMachine;

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task save(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new EntityNotFoundException("Task could not be saved: " + e.getMessage());
        }
    }

    public Task updateTask(Long taskId, String newName) {
        try {
            Task task = findTaskById(taskId);
                if (task != null) {
                    System.out.println(newName);
                    task.setName(newName);
                    return taskRepository.save(task);
                }
        } catch (Exception e) {
            throw new EntityNotFoundException("Task could not be updated: " + e.getMessage());
        }
        return null;
    }

    public void deleteTaskById(Long id) {
        try {
            Task toDelete = findTaskById(id);
            if (toDelete != null) {
                taskRepository.delete(toDelete);
            }
        } catch (Exception e) {
            throw new EntityNotFoundException("Task could not be deleted: " + e.getMessage());
        }
    }

    public Task updateStatus(Long taskId, TaskEvent event) {
        Task task = getTaskById(taskId);
        if (task == null) {
            throw new TaskNotFoundException("Task not found");
        }

        stateMachine.stop();
        stateMachine.getStateMachineAccessor().doWithAllRegions(access -> access.resetStateMachine(new DefaultStateMachineContext<>(task.getState(), null, null, null)));
        stateMachine.start();
        stateMachine.sendEvent(event);
        task.setState(stateMachine.getState().getId());
        return taskRepository.save(task);
    }

    protected Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByWorkspaceId(Long workSpaceId) {
        return taskRepository.findByWorkSpaceId(workSpaceId);
    }

    public List<Task> getTasksForPeriod(String startDate, String endDate, Long userId) {
        Timestamp startTimestamp = Timestamp.valueOf(startDate + " 00:00:00");
        Timestamp endTimestamp = Timestamp.valueOf(endDate + " 23:59:59");
        return taskRepository.findTasksForPeriod(startTimestamp, endTimestamp, userId);
    }
}