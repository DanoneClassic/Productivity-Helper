package aaade.qaservice.service.impl;

import aaade.qaservice.model.Issue;
import aaade.qaservice.service.IssueService;
import aaade.qaservice.repository.IssueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssueServiceImpl implements IssueService{

    private final IssueRepository issueRepository;
    public List<Issue> getAll() {
        return issueRepository.findAll();
    }
    public Issue getIssueById(Long id) {
        return issueRepository.getReferenceById(id);
    }

    public Issue save(Issue workSpace) {
        return issueRepository.save(workSpace);
    }

    public Issue update(Issue workSpace) {
        return issueRepository.save(workSpace);
    }

    public void deleteIssueById(Long id) {
        Issue toDelete = findIssueById(id);
        issueRepository.delete(toDelete);
    }

    protected Issue findIssueById(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WorkSpace not found"));
    }
}
