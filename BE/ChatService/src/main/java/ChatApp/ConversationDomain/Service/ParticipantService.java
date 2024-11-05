package ChatApp.ConversationDomain.Service;

import ChatApp.ConversationDomain.Entity.Conversation;
import ChatApp.ConversationDomain.Entity.Participant;
import ChatApp.ConversationDomain.Repository.ParticipantRepository;
import ChatApp.UserDomain.Entity.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    @Autowired
    ParticipantRepository repository;

    public Participant create(Conversation conversation, ChatUser user) {
        Participant participant = new Participant();
        participant.setConversation(conversation);
        participant.setUser(user);
        return this.repository.save(participant);
    }
}
