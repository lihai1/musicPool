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

import com.dao.UserNumbersService;
import com.dao.UserService;
import com.entities.SavedNumbers;
import com.entities.UserNumbers;
import com.entities.User;
import com.models.FormRequest;
import com.models.SaveNumbersForm;
import com.models.user.UserLogin;
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
@RequestMapping(value = "/user")
public class UserCtrl {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(UserCtrl.class);

    @Autowired
    UserService apiRequestService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserNumbersService userNumbersService;

    //@Autowired
    public UserCtrl(/*UserService userService*/) {
    //    this.userService = userService;
        counter.incrementAndGet();
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity getUser(@RequestBody UserLogin userLogin) {
        User u = userService.getUser(userLogin);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity addUser(@RequestBody UserLogin userLogin) {
        User u = userService.saveUser(userLogin);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/addUserNumbers", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity addUserNumbers(@RequestBody SaveNumbersForm user) {
        UserNumbers u = userNumbersService.saveNumbers(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserNumbers", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity getUserNumbers(@RequestBody User user) {
        List<UserNumbers> u = userNumbersService.getNumbers(user.getId());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/generateUserNumbers", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity generateUserNumbers(@RequestBody FormRequest user) {
        List<SavedNumbers> u = userNumbersService.generateNumbers(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }



}


