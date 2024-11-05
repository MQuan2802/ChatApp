package ChatApp.ConversationDomain.Dto;

import ChatApp.ConversationDomain.Entity.Conversation;
import ChatApp.ConversationDomain.Entity.Message;
import ChatApp.ConversationDomain.Entity.Participant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ConversationDTO {
    List<Message> messages;

    List<Participant> participants;

    Long creatorId;

    String name;

    Long id;

    public ConversationDTO(Conversation conversation) {
        this.messages = conversation.getMessages();
        this.participants = conversation.getParticipants();
        this.creatorId = conversation.getCreatorId();
        this.id = conversation.getId();

    }
}
