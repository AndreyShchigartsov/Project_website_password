package entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "storage",schema = "public")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "userid")
    private User user;

    private String password;

    private String website;

    private String comment;

    public void setUser(User user) {
        this.user = user;
        this.user.getStorages().add(this);
    }
}
