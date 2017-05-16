/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

/**
 *
 * @author LihaiMac
 */

import com.dao.LiveService;
import com.dao.UserService;
import com.entities.LiveData;
import com.entities.Rating;
import com.entities.UserMusicUrl;
import com.models.LikeState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin
@RestController
@EnableAutoConfiguration
//@RequestMapping(value = "/user")
public class UserCtrl {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LiveService liveService;

    //@Autowired
    public UserCtrl(/*UserService userService*/) {
    //    this.userService = userService;
        counter.incrementAndGet();
    }

    @GetMapping(value = "/getList")
    //@ResponseBody
    public ResponseEntity getList() {
        List<UserMusicUrl> u = userService.getList();
        u.stream().sorted((a, b) -> a.compareTo(b));
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/setList")
    //@ResponseBody
    public ResponseEntity setList(@RequestBody List<UserMusicUrl> songs) {
        List<UserMusicUrl> u = userService.saveList(songs);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity removeList(@RequestBody List<UserMusicUrl> songs) {
        Object u = userService.removeList(songs);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity rate(@RequestBody Rating rating) {
        Object u;
        if(rating.getLiked() == LikeState.NONE){
            u = userService.cancelRate(rating);
        }
        else
            u = userService.rate(rating);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @RequestMapping(value = "/currentPlaying", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity setCurrent(@RequestBody LiveData live) {
        liveService.saveCurrent(live);
        List<UserMusicUrl> res = userService.getList();
        return new ResponseEntity<>(res, HttpStatus.OK);
        /*List<LiveData> played = liveService.getCurrent();
        UserMusicUrl max = new UserMusicUrl();
        max.setLikes(-10);
        boolean flag = true;
        for(UserMusicUrl url:res){
            if(max.compareTo(url)<0){
                for(LiveData li:played){
                    if(li.getUrlPlayed() == url.getId())
                        flag = false;
                }
                if(flag)
                    max = url;
                else flag = true;
            }
        }
        return new ResponseEntity<>(max, HttpStatus.OK);
*/
    }

    @GetMapping(value = "/currentPlaying")
    public @ResponseBody ResponseEntity setCurrent() {

        return new ResponseEntity<>(liveService.getCurrent(), HttpStatus.OK);

    }

}

