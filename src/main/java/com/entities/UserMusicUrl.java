package com.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Entity
public class UserMusicUrl {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Setter
    @JsonProperty
    @Temporal(TemporalType.TIMESTAMP)
    protected Date registered;

/*
    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    protected Set<Rating> rating = new HashSet<>();
*/

    @Getter
    protected String userId;

    @Setter
    @Getter
    protected String name;

    @Setter
    @Getter
    protected String url;

    @Setter
    @Getter
    protected String genre;

    @Setter
    @Getter
    protected String artist;

    public UserMusicUrl() {
        registered = new Date();
    }

    public void setId(String userId) {
        this.userId = userId;
    }
    public String getRegistered() {
        return registered.toString();
    }



}