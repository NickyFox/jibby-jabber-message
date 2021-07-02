package com.message.controller;

import com.message.model.ChatMapper;
import com.message.model.dto.ChatDto;
import com.message.model.dto.ChatList;
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
import java.util.stream.Collectors;

@RestController
@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;
    private final ChatMapper chatMapper;

    public MessageController(SimpMessagingTemplate template, ChatMapper chatMapper) {
        this.template = template;
        this.chatMapper = chatMapper;
    }

    @MessageMapping("/chat/{id}")
    public void sendNewMessage(@DestinationVariable long id, @Payload final ChatMessageDto chatMessage) {
        template.convertAndSend("/message" + id, chatMessage);
        messageService.newMessageInChat(chatMessage);
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<Chat> getChat(@PathVariable long id){
        Optional<Chat> optionalChat = messageService.getChat(id);
        if (optionalChat.isPresent()) return ResponseEntity.ok(optionalChat.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
    }

    @GetMapping("/chat/all/{id}")
    public ResponseEntity<ChatList> getAllChats(@PathVariable long id) {
        List<Chat> chats = messageService.getAllUserChats(id);
        List<ChatDto> chatDtos = chats.stream().map(chatMapper::chatToChatDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ChatList(chatDtos));
    }
}
