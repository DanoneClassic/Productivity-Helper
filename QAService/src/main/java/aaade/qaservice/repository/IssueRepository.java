package aaade.qaservice.repository;

import aaade.qaservice.model.Issue;
import aaade.qaservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
//    List<Message> findByIssueId(Long id);
    Issue getReferenceById(Long id);
}