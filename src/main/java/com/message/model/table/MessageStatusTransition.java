package com.message.model.table;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "message_status_transition")
public class MessageStatusTransition {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "message_status_id")
    private MessageStatus messageStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @NotNull
    private Timestamp transitionedAt;


    public MessageStatusTransition() {
    }

    public MessageStatusTransition(@NotNull MessageStatus messageStatus, @NotNull Message message, @NotNull Timestamp transitionedAt) {
        this.messageStatus = messageStatus;
        this.message = message;
        this.transitionedAt = transitionedAt;
    }

    public MessageStatusTransition(long id, @NotNull MessageStatus messageStatus, @NotNull Message message, @NotNull Timestamp transitionedAt) {
        this.id = id;
        this.messageStatus = messageStatus;
        this.message = message;
        this.transitionedAt = transitionedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTransitionedAt() {
        return transitionedAt;
    }

    public void setTransitionedAt(Timestamp date) {
        this.transitionedAt = date;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
