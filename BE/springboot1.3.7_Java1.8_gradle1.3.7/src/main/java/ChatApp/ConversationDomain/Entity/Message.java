package ChatApp.ConversationDomain.Entity;


import ChatApp.ConversationDomain.Entity.Conversation;
import ChatApp.Domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "sender", nullable = false)
    private Long sender;

    @ManyToOne
    private Conversation conversation;

}
