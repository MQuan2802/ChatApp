package ChatApp.ConversationDomain.Entity;


import ChatApp.Domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "sender", nullable = false)
    private Long sender;

    @Column(name = "content_type")
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public enum ContentType {
        TEXT,
        DOCUMENT,
        IMAGE
    }

}
