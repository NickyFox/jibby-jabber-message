package com.message.model.dto;

import com.message.model.table.Message;

import java.util.List;

public class MessageList {
    List<Message> messages;

    public MessageList(List<Message> messages) {
        this.messages = messages;
    }

    public MessageList() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
