package com.entities;

import com.dao.UserNumbersService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.user.UserLogin;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Entity
public class UserNumbers  implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    protected String willBe;

    @Setter
    @JsonProperty
    @Temporal(TemporalType.TIMESTAMP)
    protected Date registered;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;


    @Setter
    @Getter
    private String strategy;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private SavedNumbers numbers;

    public UserNumbers(User user, List<Integer> willBe, SavedNumbers saved) {
        setUser(user);
        setWillBe(willBe);
        setNumbers(saved);
        registered = new Date();
    }

    public void setNumbers(SavedNumbers numbers) {
        this.numbers = numbers;
    }

    public UserNumbers() {

    }

    public UserNumbers(String name1, String pass1) {
        registered = new Date();
    }

    public UserNumbers(UserLogin userLogin) {
        registered = new Date();
    }

    public void setUser(User user) {
        this.user = user;
        registered = new Date();
    }
    public String getRegistered() {
        return registered.toString();
    }

    public List<Integer> getWillBe() {
        return UserNumbersService.listFromString(willBe);
    }

    public void setWillBe(List<Integer> willBe) {
        this.willBe = UserNumbersService.StringifyIntegerList(willBe);
    }

}