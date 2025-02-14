package aaade.qaservice.controller;

import aaade.qaservice.dto.MessageDTO;
import aaade.qaservice.model.Message;
import aaade.qaservice.service.MessageService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * MessageController class provides RESTful API endpoints for managing messages.
 */
@RestController
@RequestMapping("/api/QA/messages")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final ModelMapper modelMapper;

    /**
     * Retrieves a message by ID.
     *
     * @param id the ID of the message
     * @return the message with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
        Message message = messageService.getMessageById(id);
        MessageDTO messageDTO = modelMapper.map(message, MessageDTO.class);
        return ResponseEntity.ok(messageDTO);
    }

    /**
     * Saves a new message.
     *
     * @param message the message to save
     * @return the saved message
     */
    @PostMapping("/save")
    public ResponseEntity<MessageDTO> save(@RequestBody Message message) {
        Message savedMessage = messageService.save(message);
        MessageDTO messageDTO = modelMapper.map(savedMessage, MessageDTO.class);
        return ResponseEntity.ok(messageDTO);
    }

    /**
     * Updates a message.
     *
     * @param message the message to update
     * @return the updated message
     */
    @PostMapping("/update")
    public ResponseEntity<MessageDTO> update(@RequestBody Message message) {
        Message updatedMessage = messageService.update(message);
        MessageDTO messageDTO = modelMapper.map(updatedMessage, MessageDTO.class);
        return ResponseEntity.ok(messageDTO);
    }

    /**
     * Deletes a message by ID.
     *
     * @param id the ID of the message to delete
     * @return a ResponseEntity with no content
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable Long id) {
        messageService.deleteMessageById(id);
        return ResponseEntity.noContent().build();
    }
}