package ChatApp.ConversationDomain.Service;

import ChatApp.ConversationDomain.Dto.ConversationDTO;
import ChatApp.ConversationDomain.Entity.Conversation;
import ChatApp.ConversationDomain.Entity.Participant;
import ChatApp.ConversationDomain.Repository.ConversationRepository;
import ChatApp.ConversationDomain.Request.ConversationCreateRequest;
import ChatApp.UserDomain.Entity.ChatUser;
import ChatApp.UserDomain.Service.ChatUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    @Autowired
    ConversationRepository repository;

    @Autowired
    ChatUserService chatUserService;

    @Autowired
    ParticipantService participantService;

    public List<ConversationDTO> fetchConversationByUserIdAfterDate(long userId, LocalDate startDate) {
        return this.repository.getConversationByUserIdAfterDate(userId, startDate);

    }

    @Transactional
    public ConversationDTO create(ConversationCreateRequest request) {
        if (CollectionUtils.isEmpty(request.getParticipants()))
            throw new IllegalArgumentException("Failed to create conversation (Reason: invalid participants).");
        List<ChatUser> users = this.chatUserService.getByIds(request.getParticipants());

        if (CollectionUtils.isEmpty(request.getParticipants()))
            throw new IllegalArgumentException("Failed to create conversation (Reason: invalid users).");

        Conversation conversation = new Conversation();
        conversation.setCreatorId(request.getCreatorId());
        conversation.setName(StringUtils.isBlank(request.getName()) ?  request.getName() :
                StringUtils.join(users.stream().map(user -> user.getName()).collect(Collectors.toList()), ','));
        Conversation savedConversation = this.repository.save(conversation);

        List<Participant> savedParticipants = users.stream()
                .map(user -> this.participantService.create(savedConversation, user)).collect(Collectors.toList());
        savedConversation.setParticipants(savedParticipants);

        return new ConversationDTO(savedConversation);
    }

    @Transactional
    public Long addConversationParticipant(Long userId, Long conversationId) {
        Assert.isTrue(Objects.nonNull(userId), "Failed to add participant to conversation (Reason: invalid userId).");
        Assert.isTrue(Objects.nonNull(conversationId), "Failed to add participant to conversation  (Reason: invalid conversationId).");

        ChatUser user = this.chatUserService.getById(userId);
        Conversation conversation = this.repository.findById(conversationId);

        Assert.isTrue(Objects.nonNull(user), "Failed to add participant to conversation (Reason: cannot find user).");
        Assert.isTrue(Objects.nonNull(conversation), "Failed to add participant to conversation (Reason: cannot find conversation).");

        Participant savedParticipant = this.participantService.create(conversation, user);
        conversation.getParticipants().add(savedParticipant);
        return savedParticipant.getId();
    }
}
