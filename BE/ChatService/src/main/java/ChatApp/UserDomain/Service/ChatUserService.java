package ChatApp.UserDomain.Service;

import ChatApp.UserDomain.Entity.ChatUser;
import ChatApp.UserDomain.Repository.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ChatUserService {

    @Autowired
    ChatUserRepository repository;

    public List<ChatUser> getByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids))
            throw new IllegalArgumentException("Failed to query chat users (Reason: invalid ids).");
        return this.repository.findByIdIn(ids);
    }

    public ChatUser getById(Long id) {
        Assert.isTrue(Objects.nonNull(id), "Failed to query chat user (Reason: invalid id).");
        return this.repository.findById(id);
    }
}
