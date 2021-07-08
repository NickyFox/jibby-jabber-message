package com.message.repository;

import com.message.model.table.MessageStatusTransition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageStatusTransitionRepository extends JpaRepository<MessageStatusTransition, Long> {

    List<MessageStatusTransition> findAllByMessageStatus_IdAndMessage_ReceiverAndMessage_Sender(long messageStatusId, long messageReceiver, long messageSender);

    MessageStatusTransition findByMessage_Id(long messageId);
}
