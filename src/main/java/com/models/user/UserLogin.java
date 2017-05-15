package com.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by LihaiMac on 4/19/17.
 */
//@MappedSuperclass
public class UserLogin {

    @JsonProperty
    protected String name;
    @JsonProperty
    protected String pass;


    UserLogin(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
