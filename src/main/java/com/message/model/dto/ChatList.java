package com.message.model.dto;

import com.message.model.table.Chat;

import java.util.List;

public class ChatList {
    List<Chat> chatDtoList;


    public ChatList() {
    }

    public ChatList(List<Chat> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }

    public List<Chat> getChatDtoList() {
        return chatDtoList;
    }

    public void setChatDtoList(List<Chat> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }
}
