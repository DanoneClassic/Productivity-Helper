package aaade.qaservice.dto;

import aaade.qaservice.enums.IssuePriority;
import aaade.qaservice.enums.IssueState;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class IssueDTO {
    private Long id;
    private String title;
    private String description;
    private Date dateCreated;
    private IssueState issueState;
    private IssuePriority priority;
    private String assignedTo;
    private String reportedBy;
    private List<MessageDTO> messages;
}
