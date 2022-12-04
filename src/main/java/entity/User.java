package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String email;

    private String password;

    @Column(name = "birth_day")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private List<Storage> storages = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserChat> userChats = new ArrayList<>();

    public void addStorage(Storage storage) {
        storages.add(storage);
        storage.setUser(this);
    }
}
