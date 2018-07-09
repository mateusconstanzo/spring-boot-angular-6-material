package br.com.events.auth.entity;

import br.com.events.event.entity.EventEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<EventEntity> events = new TreeSet<>();

    public UserEntity() {
    }

    public UserEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(Set<EventEntity> events) {
        this.events = events;
    }
}
