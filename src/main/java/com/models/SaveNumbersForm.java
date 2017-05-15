package com.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LihaiMac on 5/3/17.
 */

//@Entity
//@Table(name = "form_request")
@Setter
@Getter
public class SaveNumbersForm implements Serializable {

    //public UserNumbers numbers;

    //public FormRequest request;

    public Long userId;

    public Long numbersId;

    public List<Integer> willBe;


    public SaveNumbersForm() {

    }


}
