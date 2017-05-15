package com.entities;

import com.models.LikeState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Entity
public class Rating {

    @Getter
    @Setter
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name = "native", strategy = "native")
    //@Column(name = "id", updatable = false, nullable = false)
    protected String userId;

    @Getter
    @Setter
    protected Long urlId;

    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    protected Date registered;

    public String getRegistered() {return registered.toString();}

    /*@Getter
    @Setter
    @OneToMany(targetEntity = UserMusicUrl.class, mappedBy = "url",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    protected UserMusicUrl songUrl;*/

    @Setter
    @Getter
    protected LikeState liked;

    public Rating() {
        registered = new Date();
    }
    
}