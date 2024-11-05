package ChatApp.ConversationDomain.Entity;

import ChatApp.Domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Conversation extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "is_multiple_recipients")
    private boolean multiple_recipients;
}
