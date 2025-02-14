package aaade.qaservice.service.Strategy;

import aaade.qaservice.enums.IssuePriority;
import aaade.qaservice.model.Issue;
import aaade.qaservice.service.impl.IssueServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PriorityBasedHandlingStrategy implements IssueHandlingStrategy {

    private IssueServiceImpl issueService;

    @Override
    public void handleIssue(Issue issue) {
        if (issue.getPriority() == IssuePriority.LOW) {
            issue.setPriority(IssuePriority.MEDIUM);
        } else if (issue.getPriority() == IssuePriority.MEDIUM) {
            issue.setPriority(IssuePriority.HIGH);
        }

        issueService.update(issue);
    }
}