package ChatApp.UserDomain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "user_friend")
public class Friend {

    @Column(name = "friend_id")
    private long friendId;

    @Column(name = "friend_ship_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        ACCPECTED,
        BLOCKED,
        UN_FRIENDED
    }
}
