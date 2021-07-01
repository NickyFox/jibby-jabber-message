package com.message.model.dto;

import java.sql.Timestamp;

public class ChatMessageDto {
    long sender;
    long receiver;
    String content;

    public ChatMessageDto(long sender, long receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public ChatMessageDto() {
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
