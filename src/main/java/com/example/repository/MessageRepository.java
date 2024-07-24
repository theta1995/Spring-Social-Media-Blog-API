package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    
    List<Message> findAllByPostedBy(int postedBy);
    
    @Modifying
    @Query(nativeQuery = true,
        value = "DELETE FROM message WHERE message.messageId = :messageId")
        Integer deleteByIdAndGetCount(@Param("messageId") int messageId);

    @Modifying
    @Query(nativeQuery = true, 
        value = "UPDATE message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    Integer updateMessageByIdAndGetCount(
        @Param("messageId") int messageId,
        @Param("messageText") String messageText);
}
