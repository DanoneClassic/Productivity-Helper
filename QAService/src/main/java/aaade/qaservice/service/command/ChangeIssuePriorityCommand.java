package aaade.qaservice.service.command;

import aaade.qaservice.enums.IssuePriority;
import aaade.qaservice.model.Issue;
import aaade.qaservice.service.impl.IssueServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeIssuePriorityCommand implements Command{

    private final IssueServiceImpl issueService;
    private final Long issueId;
    private final IssuePriority newPriority;

    @Override
    public void execute() {
        Issue issue = issueService.getIssueById(issueId);
        issue.setPriority(newPriority);
        issueService.update(issue);
    }
}
