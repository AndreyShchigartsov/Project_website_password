package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userchat")
public class UserChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public void setChat(Chat chat){
        this.chat = chat;
//        this.chat.getUserChats().add(this);
        this.chat.getUserChats().add(this);
    }

    public void setUser(User user){
        this.user = user;
        this.user.getUserChats().add(this);
//        this.user.getUserChats().add(this);
    }
}
