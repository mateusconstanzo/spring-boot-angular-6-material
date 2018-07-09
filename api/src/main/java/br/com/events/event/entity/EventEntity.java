package br.com.events.event.entity;

import br.com.events.auth.entity.UserEntity;
import br.com.events.commons.util.DateUtils;
import br.com.events.event.bean.Event;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date endDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user = null;


    public EventEntity() {
    }

    public EventEntity(Integer id, String name, String description, Date startDate, Date endDate, UserEntity user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public void update(Event event) throws ParseException {
        this.name = event.getName();
        this.description = event.getDescription();
        this.startDate = DateUtils.parse(event.getStart());
        this.endDate = DateUtils.parse(event.getEnd());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
