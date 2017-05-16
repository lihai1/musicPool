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

    @Getter
    @Setter
    protected String userId;

    @Getter
    @Setter
    protected Long duration;

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

    @Setter
    @Getter
    protected int likes=0;

    @Setter
    @Getter
    protected int dislikes=0;

    public UserMusicUrl() {
        registered = new Date();
    }

    public String getRegistered() {
        return registered.toString();
    }

    public void addLikes(){
        likes++;
    }
    public void addDislikes(){
        dislikes++;
    }

    public int compareTo(UserMusicUrl a) {
        return getLikes()-getDislikes() - a.getLikes() - a.getDislikes();
    }
}