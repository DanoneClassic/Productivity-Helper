package aaade.qaservice.repository;

import aaade.qaservice.model.Issue;
import aaade.qaservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
