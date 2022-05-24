package org.example.model.entity;

import lombok.Data;

import javax.persistence.*;

/*
create table ci_event_activity (
    id serial primary key,
	event_id integer not null,
    start_time varchar(5) not null,
	constraint ci_event_new_fk foreign key(event_id) references ci_event_new(id) on delete cascade
);
 */

@Data
@Entity
@Table(name = "ci_event_activity")
public class EventActivityEntity {

    @Id
    @SequenceGenerator(name = "ci_event_activity_seq", sequenceName = "ci_event_activity_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "ci_event_activity_seq")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private EventEntity event;

    @Column(name = "start_time")
    private String startTime;
}
