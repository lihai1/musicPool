package com.models;

import com.entities.UserMusicUrl;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Setter
@Getter
public class SongAndRating {

    UserMusicUrl song;

    int likes;
    int dislikes;

    public void addLikes(){
        likes++;
    }
    public void addDislikes(){
        dislikes++;
    }
}