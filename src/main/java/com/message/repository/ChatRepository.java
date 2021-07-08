package com.message.repository;

import com.message.model.table.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByUser1OrUser2(long user1, long user2);

    Optional<Chat> findByUser1AndUser2(long user1, long user2);
}
