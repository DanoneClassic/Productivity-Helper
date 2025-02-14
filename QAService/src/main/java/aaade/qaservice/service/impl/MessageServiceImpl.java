package aaade.qaservice.service.impl;

import aaade.qaservice.model.Message;
import aaade.qaservice.repository.MessageRepository;
import aaade.qaservice.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public Message getMessageById(Long id) {
        return messageRepository.getReferenceById(id);
    }

    public Message save(Message task) {
        return messageRepository.save(task);
    }

    public Message update(Message task) {
        return messageRepository.save(task);
    }

    public void deleteMessageById(Long id) {
        Message toDelete = findMessageById(id);
        messageRepository.delete(toDelete);
    }

    protected Message findMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

}
