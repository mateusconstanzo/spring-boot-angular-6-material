package br.com.events.event.bean;

import br.com.events.auth.entity.UserEntity;
import br.com.events.commons.util.DateUtils;
import br.com.events.event.entity.EventEntity;

import java.beans.Transient;
import java.text.ParseException;

public class Event {

    private static final String FORMAT_DATE = "%s %s";

    private Integer id;
    private String name;
    private String description;
    private String startDate;
    private String startHour;
    private String endDate;
    private String endHour;

    public Event() {

    }

    public Event(EventEntity entity) {

        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();

        try {

            this.startDate = DateUtils.formatDate(entity.getStartDate());
            this.startHour = DateUtils.formatHour(entity.getStartDate());
            this.endDate = DateUtils.formatDate(entity.getEndDate());
            this.endHour = DateUtils.formatHour(entity.getEndDate());

        } catch (ParseException e) {
            ;
        }
    }

    public EventEntity toEntity(UserEntity user) throws ParseException {
        return new EventEntity(
                id,
                name,
                description,
                DateUtils.parse(this.getStart()),
                DateUtils.parse(this.getEnd()),
                user
        );
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    @Transient
    public String getStart() {
        return String.format(FORMAT_DATE, startDate, startHour);
    }

    @Transient
    public String getEnd() {
        return String.format(FORMAT_DATE, endDate, endHour);
    }

}
