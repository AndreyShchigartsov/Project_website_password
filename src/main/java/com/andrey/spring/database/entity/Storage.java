package com.andrey.spring.database.entity;

//import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "storage")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Storage extends AuditingEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    private String password;

    private String website;

    private String comment;

    public void setUser(User user) {
        this.user = user;
        this.user.getStorages().add(this);
    }

    public Storage(User user, String password, String website, String comment) {
        this.user = user;
        this.password = password;
        this.website = website;
        this.comment = comment;
    }
}
