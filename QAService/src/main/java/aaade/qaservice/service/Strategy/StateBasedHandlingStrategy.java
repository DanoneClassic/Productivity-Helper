package aaade.qaservice.service.Strategy;

import aaade.qaservice.enums.IssueState;
import aaade.qaservice.model.Issue;
import aaade.qaservice.service.impl.IssueServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StateBasedHandlingStrategy implements IssueHandlingStrategy {

    private IssueServiceImpl issueService;

    public StateBasedHandlingStrategy(IssueServiceImpl issueService) {
        this.issueService = issueService;
    }

    @Override
    public void handleIssue(Issue issue) {
        if (issue.getIssueState() == IssueState.OPEN) {
            issue.setIssueState(IssueState.IN_PROGRESS);
        } else if (issue.getIssueState() == IssueState.IN_PROGRESS) {
            issue.setIssueState(IssueState.RESOLVED);
        }

        issueService.update(issue);
    }
}

