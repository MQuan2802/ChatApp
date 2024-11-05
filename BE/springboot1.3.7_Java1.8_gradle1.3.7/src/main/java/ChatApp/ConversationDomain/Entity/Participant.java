package ChatApp.ConversationDomain.Entity;

import ChatApp.Domain.BaseEntity;
import ChatApp.UserDomain.ChatUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class GroupMember extends BaseEntity {

//    @ManyToOne
//    private Conversation conversation;

    @ManyToOne
    private ChatUser user;
}
