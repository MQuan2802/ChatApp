package ChatApp.ConversationDomain.Dto;

import ChatApp.ConversationDomain.Entity.Message;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MessageDTO {

    private String content;

    private Message.ContentType contentType;

    private Long sender;

    private String conversationName;

    private Long conversationId;

    public MessageDTO (Message message) {
        this.content = String.valueOf(message.getContent());
        this.contentType = message.getContentType();
        this.sender = message.getSender();
        this.conversationId = message.getConversation().getId();
        this.conversationName = message.getConversation().getName();
    }
}
