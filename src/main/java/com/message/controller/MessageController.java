package com.message.controller;

import com.message.model.dto.ChatMessageDto;
import com.message.model.table.Chat;
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

    @MessageMapping("/chat")
    public void sendNewMessage(@DestinationVariable long chatId, @Payload final ChatMessageDto chatMessage) {
        template.convertAndSend("/message" + chatMessage.getReceiver(), chatMessage);
        messageService.newMessageInChat(chatMessage, chatId);
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<Chat> getChat(@PathVariable long id){
        Optional<Chat> optionalChat = messageService.getChat(id);
        if (optionalChat.isPresent()) return ResponseEntity.ok(optionalChat.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
    }

    @GetMapping("/chat/all/{id}")
    public ResponseEntity<List<Chat>> getAllChats(@PathVariable long id) {
        return ResponseEntity.ok(messageService.getAllUserChats(id));
    }
}
