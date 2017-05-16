package com.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Entity
public class LiveData {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;


    public void setStarted(Date started) {
        this.started = started;
    }

    @Temporal(TemporalType.TIMESTAMP)
    protected Date started;

    @Setter
    @Getter
    protected Long urlPlayed;

    public String getStarted() {
        return started.toString();
    }

    public LiveData() {
        started = new Date();
    }

}