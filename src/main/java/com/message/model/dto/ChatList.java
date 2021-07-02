package com.message.model.dto;

import java.util.List;

public class ChatList {
    List<ChatDto> chatDtoList;

    public ChatList(List<ChatDto> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }

    public ChatList() {
    }

    public List<ChatDto> getChatDtoList() {
        return chatDtoList;
    }

    public void setChatDtoList(List<ChatDto> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }
}
