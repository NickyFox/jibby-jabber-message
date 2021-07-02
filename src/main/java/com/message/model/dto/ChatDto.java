package com.message.model.dto;

public class ChatDto {
    long id;
    long user1;
    long user2;

    public ChatDto(long id, long user1, long user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    public ChatDto(long user1, long user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public ChatDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
