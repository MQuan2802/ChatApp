package ChatApp.ConversationDomain.Repository;

import ChatApp.ConversationDomain.Entity.Message;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface MessageRepository extends
        CrudRepository<Message, Long>,
        JpaSpecificationExecutor<Message>,
        Serializable {

//    @Query(value = "SELECT m, conv FROM message m " +
//            "INNER JOIN conversation conv ON m.conversation_id = conv.id " +
//            "INNER JOIN group_member gr ON m.conversation_id = gr.conversation_id " +
//            "WHERE m.creation_time >= (:filterDate) " +
//            "AND gr.user_id = (:userId)", nativeQuery = true)
//    List<Object[]> getMessageByUserIdAfterDate(@Param("userId") long userId, @Param("filterDate") LocalDate filter);

    @Query(value = "SELECT ChatApp.ConversationDomain.Entity.ConversationDTO(m.conversation) FROM Message m " +
            "WHERE m.creationTime >= (:filterDate) " +
            "AND m.conversation.id IN (SELECT p.conversation.id FROM Participant p WHERE p.user.id = (:userId)")
    List<Message> getMessageByUserIdAfterDate(@Param("userId") long userId, @Param("filterDate") LocalDate filter);
}

