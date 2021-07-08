package com.message.model.dto;

public class UnreadMessages {
    long sender;
    long receiver;

    public UnreadMessages(long sender, long receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public UnreadMessages() {
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }
}
