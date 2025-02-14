package aaade.qaservice.service;

import aaade.qaservice.model.Issue;

import java.util.List;

/**
 * IssueService interface provides methods for managing issues.
 */
public interface IssueService {

    /**
     * Retrieves all issues.
     *
     * @return a list of all issues
     */
    List<Issue> getAll();

    /**
     * Retrieves an issue by ID.
     *
     * @param id the ID of the issue
     * @return the issue with the given ID
     */
    Issue getIssueById(Long id);

    /**
     * Saves a new issue.
     *
     * @param issue the issue to save
     * @return the saved issue
     */
    Issue save(Issue issue);

    /**
     * Updates an issue.
     *
     * @param issue the issue to update
     * @return the updated issue
     */
    Issue update(Issue issue);

    /**
     * Deletes an issue by ID.
     *
     * @param id the ID of the issue to delete
     */
    void deleteIssueById(Long id);
}