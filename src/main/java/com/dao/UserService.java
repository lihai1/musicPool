package com.dao;

import com.entities.Rating;
import com.entities.UserMusicUrl;
import com.models.LikeState;
import com.repositories.RatingRepository;
import com.repositories.UsersMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LihaiMac on 4/20/17.
 */
@Service
public class UserService {



    @Autowired
    private RatingRepository ratingRepository;

    private UsersMusicRepository usersMusicRepository;

    @Autowired
    public UserService(UsersMusicRepository usersMusicRepository) {
        this.usersMusicRepository = usersMusicRepository;
        List<UserMusicUrl> list = new ArrayList<>(Arrays.asList(new UserMusicUrl[]{
                new UserMusicUrl(
                        "fgnghjmgjhjkgh",
                        130000L,
                        "203Z",
                        "cRGrIn2VHTE",
                        "calm",
                        "lihai"
                ),
                new UserMusicUrl(
                        "fgnghjmgjhjkgh",
                        130000L,
                        "203Z",
                        "rUWHOLDGwL0",
                        "calm",
                        "lihai"
                ),
                new UserMusicUrl(
                        "fgnghjmgjhjkgh",
                        130000L,
                        "test-name",
                        "NGLxoKOvzu4",
                        "calm",
                        "lihai"
                ),
                new UserMusicUrl(
                        "fgnghjmgjhjkgh",
                        130000L,
                        "new song name",
                        "NGLxoKOvzu4",
                        "strong",
                        "lihai")
        }));


        //Insert some random pies
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setLikes((int) (Math.random() * 10));
            list.get(i).setDislikes((int) (Math.random() * 10));
            usersMusicRepository.save(list.get(i));
        }
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
