package com.models;

import com.entities.Rating;
import com.entities.UserMusicUrl;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Setter
@Getter
public class SongAndRating {

    UserMusicUrl song;

    List<Rating> users;
}