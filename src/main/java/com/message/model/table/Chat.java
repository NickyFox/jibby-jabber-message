package com.message.model.table;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    long user1;

    @NotNull
    long user2;

    @NotNull
    @ManyToMany
    @JoinColumn(name = "message_id")
    private Collection<Message> messages;

    public Chat() {
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

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }
}
