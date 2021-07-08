package com.message.service;

import com.message.model.dto.ChatDto;
import com.message.model.dto.ChatMessageDto;
import com.message.model.dto.UnreadMessages;
import com.message.model.table.Chat;
import com.message.model.table.Message;
import com.message.model.table.MessageStatus;
import com.message.model.table.MessageStatusTransition;
import com.message.repository.ChatRepository;
import com.message.repository.MessageRepository;
import com.message.repository.MessageStatusTransitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessageStatusTransitionRepository messageStatusTransitionRepository;

    public void newMessageInChat(ChatMessageDto chatMessageDto) {
        Optional<Chat> optionalChat = chatRepository.findById(chatMessageDto.getId());
        if (optionalChat.isPresent()) {
            setMessagesToExistingChat(optionalChat.get(), chatMessageDto);
        } else {
            Optional<Chat> chatExists = chatExists(chatMessageDto);
            if (chatExists.isEmpty()) {
                newMessageChat(chatMessageDto);
            } else {
                setMessagesToExistingChat(chatExists.get(), chatMessageDto);

            }
        }
    }

    public List<Chat> getAllUserChats(long userId) {
        return chatRepository.findAllByUser1OrUser2(userId, userId);
    }

    public Optional<Chat> getChat(long chatId) {
        return chatRepository.findById(chatId);
    }

    private void setMessagesToExistingChat(Chat chat, ChatMessageDto chatMessageDto) {
        Message savedMessage = createMessage(chatMessageDto);
        setMessageStatusToUnread(savedMessage);
        Collection<Message> messages = chat.getMessages();
        messages.add(savedMessage);
        chat.setMessages(messages);
        chatRepository.save(chat);
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

    private void setMessageStatusToUnread(Message message) {
        MessageStatus messageStatus = new MessageStatus(1, "unread");
        Date date = new Date();
        MessageStatusTransition messageStatusTransition = new MessageStatusTransition(messageStatus, message, new Timestamp(date.getTime()));
        messageStatusTransitionRepository.save(messageStatusTransition);
    }

    public void setMessageTransitionToRead(long messageId) {
        MessageStatusTransition messageStatusTransition = messageStatusTransitionRepository.findByMessage_Id(messageId);
        MessageStatus messageStatus = new MessageStatus(2, "read");
        messageStatusTransition.setMessageStatus(messageStatus);
        messageStatusTransitionRepository.save(messageStatusTransition);
    }

    public void setAllMessagesToRead(List<Long> messagesId) {
        messagesId.forEach(this::setMessageTransitionToRead);
    }

    public List<Message> getAllUnreadMessagesFromChat(UnreadMessages unreadMessages){
        List<MessageStatusTransition> messageStatusTransitions = messageStatusTransitionRepository
                .findAllByMessageStatus_IdAndMessage_ReceiverAndMessage_Sender(1,unreadMessages.getReceiver(), unreadMessages.getSender());
        return messageStatusTransitions.stream().map(MessageStatusTransition::getMessage).collect(Collectors.toList());
    }


    private void newMessageChat(ChatMessageDto chatMessageDto) {
        Chat chat = new Chat();
        Message savedMessage = createMessage(chatMessageDto);
        setMessageStatusToUnread(savedMessage);
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

    private Optional<Chat> chatExists(ChatMessageDto chatMessageDto) {
        if (chatMessageDto.getReceiver() < chatMessageDto.getSender()){
            return chatRepository.findByUser1AndUser2(chatMessageDto.getReceiver(), chatMessageDto.getSender());
        }
        return chatRepository.findByUser1AndUser2(chatMessageDto.getSender(), chatMessageDto.getReceiver());
    }
}
