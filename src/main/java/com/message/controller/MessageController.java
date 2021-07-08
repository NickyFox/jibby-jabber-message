package com.message.controller;

import com.message.model.dto.*;
import com.message.model.table.Chat;
import com.message.model.table.Message;
import com.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;

    public MessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat/{id}")
    public void sendNewMessage(@DestinationVariable long id, @Payload final ChatMessageDto chatMessage) {
        template.convertAndSend("/topic/messages/" + id, chatMessage);
        messageService.newMessageInChat(chatMessage);
    }

    @GetMapping("/chat/messages/{id}")
    public ResponseEntity<Chat> getChat(@PathVariable long id){
        Optional<Chat> optionalChat = messageService.getChat(id);
        if (optionalChat.isPresent()) return ResponseEntity.ok(optionalChat.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
    }

    @GetMapping("/chats/{id}")
    public ResponseEntity<ChatList> getAllChats(@PathVariable long id) {
        List<Chat> chats = messageService.getAllUserChats(id);
        return ResponseEntity.ok(new ChatList(chats));
    }

    @GetMapping("/chats/{id}/unread")
    public ResponseEntity<MessageList> getAllUnreadMessagesFromChat(@PathVariable long id, UnreadMessages unreadMessages) {
        List<Message> messages = messageService.getAllUnreadMessagesFromChat(unreadMessages);
        return ResponseEntity.ok(new MessageList(messages));
    }
}
