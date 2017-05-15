package com.dao;

import com.entities.Rating;
import com.entities.UserMusicUrl;
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
        List<UserMusicUrl> saved = new ArrayList<>();
        try {
            for(UserMusicUrl url:playList){
                if(usersMusicRepository.findByNameOrUrl(url.getName(),url.getUrl()).size()>0)
                    saved.add(usersMusicRepository.save(url));
            }

        } catch (Exception e) {
             e.printStackTrace();
        }

        return saved;

    }

    public List<UserMusicUrl> getList() {
        return usersMusicRepository.findAll();
    }

    public Rating getUser(Long user) {
        Rating ratingMatch;
        List<Rating> l = ratingRepository.findById(user);
        ratingMatch = l.get(0);
        return ratingMatch;
    }

    public Rating rate(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Object removeList(List<UserMusicUrl> songs) {
        try {
            for(UserMusicUrl url:songs){
                usersMusicRepository.delete(songs);
            }
            return "Success";
        }
        catch(Exception e){
            return e;
        }
    }
}
