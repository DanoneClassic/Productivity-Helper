package aaade.WorkSpaceService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * WorkSpace is an entity class that represents a workspace in the application.
 * It contains a list of tasks that belong to the workspace.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workSpaces")
public class WorkSpace {

    /**
     * The unique identifier of the workspace.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * The name of the workspace.
     */
    private String name;

    /**
     * The list of tasks that belong to the workspace.
     * It is annotated with @JsonBackReference to avoid infinite loop during serialization due to the bidirectional relationship.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Task> tasks;

    /**
     * The identifier of the owner of the workspace.
     */
    private Long ownerId;
}