package com.message.model.table;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message_status")
public class MessageStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    String name;

    public MessageStatus(long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public MessageStatus() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
