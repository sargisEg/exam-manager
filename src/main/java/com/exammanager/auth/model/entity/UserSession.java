
package com.exammanager.auth.model.entity;

import com.exammanager.common.model.entity.BaseEntity;
import com.exammanager.user.model.entity.User;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "USER_SESSIONS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "id"})
)
public class UserSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_SESSIONS_USER_ID_USERS_ID"))
    private User user;

    @Column(name = "exp_at", nullable = false)
    private Long expAt;

    public UserSession(String id, Long createdAt, Long updated, User user, Long expAt) {
        super(id, createdAt, updated);
        this.user = user;
        this.expAt = expAt;
    }
}
