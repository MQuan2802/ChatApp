package ChatApp.ConversationDomain.Entity;

import ChatApp.Domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Conversation extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "creator_id")
    private Long creatorId;

    @OneToMany(mappedBy = "conversation")
    List<Message> messages;

    @OneToMany(mappedBy = "conversation")
    List<Participant> participants;

}
