package com.example.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    protected SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccountController(@RequestBody Account newAccount) {
        Account createdAccount = accountService.registerAccount(newAccount);
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginAccountController(@RequestBody Account account) {
        Account authorizedAccount = accountService.loginAccount(account);
        return ResponseEntity.ok(authorizedAccount);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessageController(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok(createdMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messagesList = new ArrayList<>();
        messagesList = messageService.getAllMessages();
        return ResponseEntity.ok(messagesList);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageByMessageIdController(@PathVariable  int messageId) {
        Message message = messageService.getMessageByMessageId(messageId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageByMessageIdController(@PathVariable  int messageId) {
        return ResponseEntity.ok(messageService.deleteMessageByMessageId(messageId));
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageByMessageIdController(@RequestBody Message message, @PathVariable int messageId) {
        return ResponseEntity.ok(messageService.updateMessageByMessageId(messageId, message.getMessageText()));
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountIdController(@PathVariable int accountId) {
        return ResponseEntity.ok(messageService.getAllMessagesByAccountId(accountId)); 
    }

}
