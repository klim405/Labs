package models;

import jakarta.persistence.*;
import models.convertors.PasswordConvertor;
import models.fields.Password;

import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "USERDATA")
public class Userdata implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Convert(converter = PasswordConvertor.class)
    @Column(name = "password", nullable = false)
    private Password password;

    @OneToMany(mappedBy = "userdata")
    private Set<HitInfo> hitInfos;

    public Userdata() {};

    public Userdata(String username, String password) {
        this.username = username;
        this.password = new Password(password);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
