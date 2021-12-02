package com.example.myapp.entityes;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")

public class User implements UserDetails , Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String username;
    @Column(columnDefinition = "VARCHAR(255) ")
    private String email;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;
    @Column(columnDefinition = "VARCHAR(255) ")
    private String avatar;

    private boolean active;

    @ElementCollection(targetClass  =Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Review> review;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comments> comments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserReviewRating> rating;



    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }
    public boolean isUser(){
        return roles.contains(Role.USER);
    }
    public boolean isActive() {
        return active;
    }

    public User() {
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String username, String email, String password, String avatar) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
}
