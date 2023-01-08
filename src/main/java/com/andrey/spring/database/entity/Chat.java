package com.andrey.spring.database.entity;

//import jakarta.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"listSms", "userChats"})
@EqualsAndHashCode(of = "title", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "chat")
public class Chat extends AuditingEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(name = "time_creating")
    private LocalDate timeCreating;

    @Column(name = "login_creating")
    private String loginCreating;

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
