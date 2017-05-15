package com.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.models.LikeState;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Entity
public class Rating {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    protected Long userId;

    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    protected Date registered;

    public String getRegistered() {return registered.toString();}

    @Getter
    @Setter
    @OneToMany(targetEntity = UserMusicUrl.class, mappedBy = "url",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    protected Set<UserMusicUrl> songUrl;

    @Setter
    @Getter
    protected LikeState liked;

    public Rating() {
        registered = new Date();
    }
    
}