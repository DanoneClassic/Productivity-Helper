package aaade.qaservice.service.command;

import aaade.qaservice.model.Issue;
import aaade.qaservice.service.impl.IssueServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssignIssueCommand implements Command{
    private final IssueServiceImpl issueService;
    private final Long issueId;
    private final String assignedTo;

    @Override
    public void execute() {
        Issue issue = issueService.getIssueById(issueId);
        issue.setAssignedTo(assignedTo);
        issueService.update(issue);
    }
}
