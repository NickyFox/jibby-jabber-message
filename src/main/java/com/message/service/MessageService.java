package com.message.service;

import com.message.model.dto.ChatMessageDto;
import com.message.model.table.Chat;
import com.message.model.table.Message;
import com.message.repository.ChatRepository;
import com.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;

    private void newMessageChat(ChatMessageDto chatMessageDto) {
        Chat chat = new Chat();
        Message savedMessage = createMessage(chatMessageDto);
        if (chatMessageDto.getReceiver() < chatMessageDto.getSender()) {
            chat.setUser1(chatMessageDto.getReceiver());
            chat.setUser2(chatMessageDto.getSender());
        } else {
            chat.setUser2(chatMessageDto.getReceiver());
            chat.setUser1(chatMessageDto.getSender());
        }
        List<Message> messages = new ArrayList<>();
        messages.add(savedMessage);
        chat.setMessages(messages);
        chatRepository.save(chat);
    }

    public void newMessageInChat(ChatMessageDto chatMessageDto) {
        Optional<Chat> optionalChat = chatRepository.findById(chatMessageDto.getId());
        if (optionalChat.isPresent()) {
            Chat chat = optionalChat.get();
            Message savedMessage = createMessage(chatMessageDto);
            Collection<Message> messages = chat.getMessages();
            messages.add(savedMessage);
            chat.setMessages(messages);
            chatRepository.save(chat);
        } else {
            newMessageChat(chatMessageDto);
        }
    }

    public List<Chat> getAllUserChats(long userId) {
        return chatRepository.findAllByUser1OrUser2(userId, userId);
    }

    public Optional<Chat> getChat(long chatId) {
        return chatRepository.findById(chatId);
    }

    private Message createMessage(ChatMessageDto chatMessageDto) {
        Message message = new Message();
        message.setReceiver(chatMessageDto.getReceiver());
        message.setSender(chatMessageDto.getSender());
        message.setContent(chatMessageDto.getContent());
        Date date = new Date();
        message.setDate(new Timestamp(date.getTime()));
        return messageRepository.save(message);
    }

}
