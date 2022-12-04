package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "chatid")
    private Chat chat;

    private String sms;

    private LocalDate time;

    @Column(name = "login_sender")
    private String loginSender;

    public void setChat(Chat chat) {
        this.chat = chat;
        this.chat.getListSms().add(this);
    }
}
