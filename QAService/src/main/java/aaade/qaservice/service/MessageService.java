package aaade.qaservice.service;

import aaade.qaservice.model.Message;
import jakarta.persistence.EntityNotFoundException;

/**
 * MessageService interface provides methods for managing messages.
 */
public interface MessageService {

    /**
     * Retrieves a message by ID.
     *
     * @param id the ID of the message
     * @return the message with the given ID
     * @throws EntityNotFoundException if no message with the given ID is found
     */
    Message getMessageById(Long id);

    /**
     * Saves a new message.
     *
     * @param message the message to save
     * @return the saved message
     */
    Message save(Message message);

    /**
     * Updates a message.
     *
     * @param message the message to update
     * @return the updated message
     */
    Message update(Message message);

    /**
     * Deletes a message by ID.
     *
     * @param id the ID of the message to delete
     */
    void deleteMessageById(Long id);
}