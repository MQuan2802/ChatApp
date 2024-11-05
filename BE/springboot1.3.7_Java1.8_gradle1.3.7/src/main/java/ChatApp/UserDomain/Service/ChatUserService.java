package ChatApp.UserDomain.Service;

import ChatApp.UserDomain.Entity.ChatUser;
import ChatApp.UserDomain.Repository.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    ChatUserRepository repository;

    List<ChatUser> getByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids))
            throw new IllegalArgumentException("Failed to query chat user (Reason: invalid ids).");
        return this.repository.findByIdIn(ids);
    }
}
