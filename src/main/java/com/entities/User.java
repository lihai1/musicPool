package com.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.models.user.UserLogin;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


/**
 * Created by LihaiMac on 4/19/17.
 */

@Entity
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Getter
    @Setter
    @NotNull
    @Size(min = 3, max = 100)
    protected String name;

    @Getter
    @Setter
    @NotNull
    @Size(min = 4, max = 100)
    protected String pass;

    //protected List<Long> forms;
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    protected Date registered;


    @OneToMany(targetEntity = UserNumbers.class, mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<UserNumbers> numbers = new HashSet<>();

    public Set<UserNumbers> getNumbers() {
        return numbers;
    }

    public void addNumbers(UserNumbers numbers) {
        numbers.setUser(this);
        this.numbers.add(numbers);
    }


    public User() {
        name = "not found";
        pass = "not valid";
        registered = new Date();
    }

    public User(String name1, String pass1) {
        //id = id1;

        name = name1;
        pass = pass1;
        registered = new Date();
    }

    public User(UserLogin userLogin) {
        name = userLogin.getName();
        pass = userLogin.getPass();
        registered = new Date();
    }


    public String getRegistered() {
        return registered.toString();
    }

    /*@Override
    public String toString() {
        List nums = new ArrayList();
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", registered=" + registered +
                ", numbers=" + numbers +
                '}';
    }*/
}