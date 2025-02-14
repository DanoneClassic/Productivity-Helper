package aaade.qaservice.service.command;

import aaade.qaservice.enums.IssueState;
import aaade.qaservice.model.Issue;
import aaade.qaservice.service.impl.IssueServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeIssueStateCommand implements Command{
    private final IssueServiceImpl issueService;
    private final Long issueId;
    private final IssueState newState;

    @Override
    public void execute() {
        Issue issue = issueService.getIssueById(issueId);
        issue.setIssueState(newState);
        issueService.update(issue);
    }
}
