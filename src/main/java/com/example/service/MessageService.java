package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.RequirementsNotMetException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    protected MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Create a message in the database.
     * @param message
     * @return the created message entitiy with its generated id.
     * @throws RequirementsNotMetException in case message is empty, over 255 characters or poster doesn't exist.
     */
    public Message createMessage(Message message) {
        String messageText = message.getMessageText();
        if (messageText == null || messageText == "" || messageText.length() > 255) {
            throw new RequirementsNotMetException("Message is too long or its empty.");
        }
        if (!accountRepository.existsById(message.getPostedBy())) {
            throw new RequirementsNotMetException("Message is not from an existing account.");
        }
        return messageRepository.save(message);
    }

    /**
     * Get every messages in the database.
     * @return a list of all messages available
     */
    public List<Message> getAllMessages() {
        List<Message> messagesList = new ArrayList<>();
        messageRepository.findAll().forEach(messagesList::add);
        return messagesList;
    }

    /**
     * Get message by messageId
     * @param messageId
     * @return message entity if exist, otherwise null.
     */
    public Message getMessageByMessageId(int messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    /**
     * Delete message by its messageId
     * @param messageId
     * @return return null if no rows are affected, otherwise the number of affected rows.
     */
    public Integer deleteMessageByMessageId(int messageId) {
        int updatedRow = messageRepository.deleteByIdAndGetCount(messageId);
        return updatedRow == 0 ? null : updatedRow;
    }

    /**
     * Update message text to the new give text associated its messageId
     * @param messageId
     * @param messageText
     * @return return null if no rows are affected, otherwise the number of affected rows.
     */
    public Integer updateMessageByMessageId(int messageId, String messageText) {
        if (!messageRepository.existsById(messageId) ||
            messageText == null ||
            messageText.length() == 0 ||
            messageText.length() > 255) 
            throw new RequirementsNotMetException("This messageId doesn't exist or the message text did not meet requirements.");

        int updatedRow = messageRepository.updateMessageByIdAndGetCount(messageId, messageText);
        return updatedRow == 0 ? null : updatedRow;
    }
}
