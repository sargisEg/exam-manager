package com.exammanager.user.model.entity;

import com.exammanager.common.model.entity.BaseEntity;
import com.exammanager.user.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type_discriminator")
public abstract class User extends BaseEntity {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "password", nullable = false)
    @ToString.Exclude
    private String password;

    public User(String id, Long createdAt, Long updated, String fullName, String email, String phone, String password) {
        super(id, createdAt, updated);
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
