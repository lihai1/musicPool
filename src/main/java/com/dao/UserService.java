package com.dao;

import com.entities.User;
import com.entities.UserNumbers;
import com.models.user.UserLogin;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by LihaiMac on 4/20/17.
 */
@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    private UserNumbersService userNumbersService;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User saveUser(UserLogin userLogin) {
        try {
            User userData = userRepository.findAll().stream()
                    .filter(usr ->  usr.getName().equals(userLogin.getName()))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            User usr = new User(userLogin);
            UserNumbers un = new UserNumbers();
            un.setRegistered(new Date());
            return userRepository.save(usr);
        }

        User usr = new User();
        usr.setName("userLogin exist!");
        return usr;

    }

    public User getUser(UserLogin userLogin) {
        User userMatch;
        try {
            userMatch = userRepository.findAll().stream()
                    .filter(usr -> {
                        boolean flag = usr.getName().equals(userLogin.getName());
                        flag = flag && usr.getPass().equals(userLogin.getPass());
                        return flag;
                    })
                    .findFirst().get();
            //userMatch.setNumbers(userNumbersService.getUserNumbersByUser(userMatch.getId()));
        } catch (NoSuchElementException e) {
            userMatch = new User("not found", "not validated");
        }
        return userMatch;
    }
    public User getUser(Long user) {
        User userMatch;
        List<User> l = userRepository.findById(user);
        userMatch = l.get(0);
        return userMatch;
    }
}
