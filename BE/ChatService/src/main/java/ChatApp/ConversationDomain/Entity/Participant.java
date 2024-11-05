package ChatApp.ConversationDomain.Entity;

import ChatApp.Domain.BaseEntity;
import ChatApp.UserDomain.Entity.ChatUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
public class Participant extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @OneToOne
    private ChatUser user;

    @Column(name = "last_view")
    private Calendar lastView;
}
