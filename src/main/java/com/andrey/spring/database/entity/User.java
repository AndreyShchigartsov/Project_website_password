package com.andrey.spring.database.entity;

//import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"userChats", "storages"})
@EqualsAndHashCode(of = "login", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends AuditingEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    private String email;

    private String password;

    @Column(name = "birth_day")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Role role;
    @NotAudited
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private List<Storage> storages = new ArrayList<>();

    @NotAudited
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserChat> userChats = new ArrayList<>();

    @NotAudited
    public void addStorage(Storage storage) {
        storages.add(storage);
        storage.setUser(this);
    }
}
