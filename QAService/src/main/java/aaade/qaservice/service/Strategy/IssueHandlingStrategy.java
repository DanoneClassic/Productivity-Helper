package aaade.qaservice.service.Strategy;

import aaade.qaservice.model.Issue;

public interface IssueHandlingStrategy {
    void handleIssue(Issue issue);
}
