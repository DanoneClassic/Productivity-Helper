package aaade.WorkSpaceService.repository;

import aaade.WorkSpaceService.model.Task;
import aaade.WorkSpaceService.model.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {

    List<Task> getTasksById(Long id);

    WorkSpace getReferenceById(Long id);

    List<WorkSpace> findByOwnerId(Long ownerId);
}
