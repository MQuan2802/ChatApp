package ChatApp.UserDomain.Entity;

import ChatApp.Domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class ChatUser extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @ElementCollection(targetClass = Friend.class)
    List<Friend> friendList;
}
