package com.dao;

import com.entities.Rating;
import com.entities.UserMusicUrl;
import com.models.LikeState;
import com.repositories.RatingRepository;
import com.repositories.UsersMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LihaiMac on 4/20/17.
 */
@Service
public class UserService {

    @Autowired
    private UsersMusicRepository usersMusicRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public UserService() {

    }


    public List<UserMusicUrl> saveList(List<UserMusicUrl> playList) {
        UserMusicUrl tmp;
        List<UserMusicUrl> saved = new ArrayList<>();
        for (UserMusicUrl url : playList) {
            try {
                if (usersMusicRepository.findByNameOrUrl(url.getName(), url.getUrl()).size() == 0) {
                    tmp = usersMusicRepository.save(url);
                    saved.add(tmp);
                }
            } catch (Exception e) {
                tmp = usersMusicRepository.save(url);
                saved.add(tmp);
            }
        }

        return saved;

    }

    public List<UserMusicUrl> getList() {
        return usersMusicRepository.findAll();
    }


    public Rating rate(Rating rating) {
        Rating rate = ratingRepository.save(rating);
        UserMusicUrl url = usersMusicRepository.getById(rating.getUrlId());
        if (rate.getLiked() == LikeState.LIKE)
            url.addLikes();
        else
            url.addDislikes();
        usersMusicRepository.save(url);
        return rate;
    }

    public Object removeList(List<UserMusicUrl> songs) {
        try {
            for (UserMusicUrl url : songs) {
                usersMusicRepository.deleteById(url.getId());
            }
            return "Success";
        } catch (Exception e) {
            return e;
        }
    }

    public Object cancelRate(Rating rating) {
        try {
            ratingRepository.delete(rating);
            return "Success";
        } catch (Exception e) {
            return e;
        }
    }

/*
    public static UserMusicUrl getUrlById(Long currentMusicUrlId) {
        return usersMusicRepository.getOne(currentMusicUrlId);
    }*/
}
