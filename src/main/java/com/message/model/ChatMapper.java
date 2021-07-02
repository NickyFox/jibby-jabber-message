package com.message.model;
import com.message.model.dto.ChatDto;
import com.message.model.table.Chat;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatDto chatToChatDto(Chat chat);
}
