package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "time_creating")
    private LocalDate timeCreating;

    @Column(name = "login_creation")
    private String loginCreation;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chatid")
    private List<Sms> listSms = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private List<UserChat> userChats = new ArrayList<>();

    public void addSms(Sms sms){
        listSms.add(sms);
        sms.setChat(this);
    }
}
