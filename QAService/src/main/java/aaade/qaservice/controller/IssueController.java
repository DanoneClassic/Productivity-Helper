package aaade.qaservice.controller;

import aaade.qaservice.enums.IssueState;
import aaade.qaservice.model.Issue;
import aaade.qaservice.enums.IssuePriority;
import aaade.qaservice.service.impl.IssueServiceImpl;
import aaade.qaservice.dto.IssueDTO;
import aaade.qaservice.service.Strategy.IssueHandlingStrategy;
import aaade.qaservice.service.command.AssignIssueCommand;
import aaade.qaservice.service.command.ChangeIssuePriorityCommand;
import aaade.qaservice.service.command.ChangeIssueStateCommand;
import aaade.qaservice.service.command.Command;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * IssueController class provides RESTful API endpoints for managing issues.
 */
@RestController
@RequestMapping("/api/QA/issues")
@AllArgsConstructor
public class IssueController {

    private final IssueServiceImpl issueService;
    private final ModelMapper modelMapper;

    @Qualifier("priorityBasedHandlingStrategy")
    private final IssueHandlingStrategy priorityBasedStrategy;
    @Qualifier("stateBasedHandlingStrategy")
    private final IssueHandlingStrategy stateBasedStrategy;

    /**
     * Retrieves all issues.
     *
     * @return a list of all issues
     */
    @GetMapping
    public List<IssueDTO> getAll() {
        List<Issue> issues = issueService.getAll();
        return issues.stream()
                .map(issue -> modelMapper.map(issue, IssueDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an issue by ID.
     *
     * @param id the ID of the issue
     * @return the issue with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<IssueDTO> getIssueById(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id);
        IssueDTO issueDTO = modelMapper.map(issue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    /**
     * Saves a new issue.
     *
     * @param issue the issue to save
     * @return the saved issue
     */
    @PostMapping("/save")
    public ResponseEntity<IssueDTO> save(@RequestBody Issue issue) {
        Issue savedIssue = issueService.save(issue);
        IssueDTO issueDTO = modelMapper.map(savedIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    /**
     * Updates an issue.
     *
     * @param issue the issue to update
     * @return the updated issue
     */
    @PutMapping("/update")
    public ResponseEntity<IssueDTO> update(@RequestBody Issue issue) {
        Issue updatedIssue = issueService.update(issue);
        IssueDTO issueDTO = modelMapper.map(updatedIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    /**
     * Deletes an issue by ID.
     *
     * @param id the ID of the issue to delete
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/deleteWorkSpaceById/{id}")
    public ResponseEntity<Void> deleteIssueById(@PathVariable Long id) {
        issueService.deleteIssueById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Closes an issue by ID.
     *
     * @param id the ID of the issue to close
     * @return the closed issue
     */
    @PutMapping("/{id}/close")
    public ResponseEntity<IssueDTO> closeIssue(@PathVariable Long id) {
        Command command = new ChangeIssueStateCommand(issueService, id, IssueState.CLOSED);
        command.execute();

        Issue updatedIssue = issueService.getIssueById(id);
        IssueDTO issueDTO = modelMapper.map(updatedIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    /**
     * Changes the priority of an issue.
     *
     * @param id          the ID of the issue
     * @param newPriority the new priority of the issue
     * @return the issue with the updated priority
     */
    @PutMapping("/{id}/changePriority")
    public ResponseEntity<IssueDTO> changePriority(@PathVariable Long id, @RequestParam IssuePriority newPriority) {
        Command command = new ChangeIssuePriorityCommand(issueService, id, newPriority);
        command.execute();

        Issue updatedIssue = issueService.getIssueById(id);
        IssueDTO issueDTO = modelMapper.map(updatedIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    /**
     * Assigns an issue to a user.
     *
     * @param id         the ID of the issue
     * @param assignedTo the username of the user to assign the issue to
     * @return the issue with the updated assignment
     */
    @PutMapping("/{id}/assign")
    public ResponseEntity<IssueDTO> assignTo(@PathVariable Long id, @RequestParam String assignedTo) {
        Command command = new AssignIssueCommand(issueService, id, assignedTo);
        command.execute();

        Issue updatedIssue = issueService.getIssueById(id);
        IssueDTO issueDTO = modelMapper.map(updatedIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    /**
     * Prioritizes an issue.
     *
     * @param id the ID of the issue
     * @return the issue with the updated priority
     */
    @PutMapping("/{id}/prioritize")
    public ResponseEntity<IssueDTO> prioritizeIssue(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id);
        priorityBasedStrategy.handleIssue(issue);

        Issue updatedIssue = issueService.getIssueById(id);
        IssueDTO issueDTO = modelMapper.map(updatedIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }

    /**
     * Changes the state of an issue.
     *
     * @param id the ID of the issue
     * @return the issue with the updated state
     */
    @PutMapping("/{id}/changeState")
    public ResponseEntity<IssueDTO> changeState(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id);
        stateBasedStrategy.handleIssue(issue);

        Issue updatedIssue = issueService.getIssueById(id);
        IssueDTO issueDTO = modelMapper.map(updatedIssue, IssueDTO.class);
        return ResponseEntity.ok(issueDTO);
    }
}