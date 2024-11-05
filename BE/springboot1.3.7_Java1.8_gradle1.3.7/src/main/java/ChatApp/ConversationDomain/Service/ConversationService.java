package ChatApp.ConversationDomain.Service;

import ChatApp.ConversationDomain.Dto.MessageDTO;
import ChatApp.ConversationDomain.Entity.Message;
import ChatApp.ConversationDomain.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    MessageRepository conversationRepository;

    List<MessageDTO> fetchMessageByUserIdAfterDate(long userId, LocalDate startDate) {
        List<Message> messages = this.conversationRepository.getMessageByUserIdAfterDate(userId, startDate);
        if (CollectionUtils.isEmpty(messages))
            return new ArrayList<>();
        return messages.stream().map(message -> new MessageDTO(message)).collect(Collectors.toList());
    }
}
