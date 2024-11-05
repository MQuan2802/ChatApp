package ChatApp.UserDomain.Repository;

import ChatApp.UserDomain.Entity.ChatUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface UserRepository  extends
        CrudRepository<ChatUser, Long>,
        JpaSpecificationExecutor<ChatUser>,
        Serializable {

    List<ChatUser> findByIdIn(Collection<Long> ids);
}
