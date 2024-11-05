package ChatApp.ConversationDomain.Repository;

import ChatApp.ConversationDomain.Entity.Participant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface ParticipantRepository extends
        CrudRepository<Participant, Long>,
        JpaSpecificationExecutor<Participant>,
        Serializable {
}
