package com.andrey.spring.database.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Sms extends AuditingEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
