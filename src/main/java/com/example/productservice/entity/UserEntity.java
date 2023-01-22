package com.example.productservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = "tb_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String login;

    private String password;

    private Boolean status;

    @Column(name = "userRoleId", nullable = false)
    private Integer userRoleId;
    @ManyToOne
    @JoinColumn(name = "userRoleId", insertable = false, updatable = false)
    private UserRoleEntity userRole;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;

    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;

    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}