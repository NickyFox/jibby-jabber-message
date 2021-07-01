package com.message.model.dto;

public class ChatDto {
    long user1;
    long user2;

    public ChatDto(long user1, long user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public ChatDto() {
    }

    public long getUser1() {
        return user1;
    }

    public void setUser1(long user1) {
        this.user1 = user1;
    }

    public long getUser2() {
        return user2;
    }

    public void setUser2(long user2) {
        this.user2 = user2;
    }
}
