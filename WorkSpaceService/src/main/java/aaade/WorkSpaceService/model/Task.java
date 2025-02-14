package aaade.WorkSpaceService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

/**
 * Task is an entity class that represents a task in the application.
 * It is associated with a workspace.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    /**
     * The unique identifier of the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * The name of the task.
     */
    @NonNull
    private String name;

    /**
     * The description of the task.
     */
    private String description;

    /**
     * The date associated with the task.
     */
    private Date data;

    /**
     * The workspace that the task belongs to.
     * It is annotated with @JsonBackReference to avoid infinite loop during serialization due to the bidirectional relationship.
     */
    @ManyToOne
    @JsonBackReference
    private WorkSpace workSpace;

    /**
     * The state of the task.
     */
    @Enumerated(EnumType.STRING)
    private TaskState state;
}