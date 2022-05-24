package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
create table ci_event_new (
    id serial primary key,
    owner varchar(30) not null
);
 */

@Data
@Entity
@Table(name = "ci_event_new")
public class EventEntity {

    @Id
    @SequenceGenerator(name = "ci_event_new_seq", sequenceName = "ci_event_new_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "ci_event_new_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "owner")
    private String owner;

    @OneToMany(mappedBy="event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventActivityEntity> activities = new ArrayList<>();

    public void addActivity(EventActivityEntity activity) {
        activity.setEvent(this);
        activities.add(activity);
    }
}
