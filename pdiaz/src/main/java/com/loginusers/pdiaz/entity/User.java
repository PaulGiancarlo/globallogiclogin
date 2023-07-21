package com.loginusers.pdiaz.entity;

import com.loginusers.pdiaz.entity.PhonesType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    @Column(name = "name", length = 95)
    private String name;
    @Column(name = "email", nullable = false, length = 155)
    private String email;
    @Column(name = "password", nullable = false, length = 73)
    private String password;
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_modification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModification;
    @Column(name = "last_login", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy(value = "id desc")
    private List<PhonesType> phones;
}
