/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.lotteryTools.LotteryArray;
import com.lotteryTools.lotteryToolsCommon;

import java.util.Date;
import java.util.List;

/**
 * @author LihaiMac
 */
//@Entity
//@Table(name = "form_request")
public class FormRequest extends BaseRequest {

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "form_id")*/
    private Long id;

    public FormRequest() {
    }
    public FormRequest(int howMany, int type) {
        this.from = null;
        this.to = null;
        this.howMany = howMany;
        this.type = type;
    }

    public FormRequest(Date from, Date to, int howMany, int type, lotteryToolsCommon strong) {
        this.from = from;
        this.to = to;
        this.howMany = howMany;
        this.type = type;
        this.strong = strong;
    }

    public List<List> runRequest() {
        LotteryArray lo = initLottery();
        lo.generateNewCombination(howMany, type, willBe);
        return toLists(lo.results);
    }
    public int[][] runRequestAsArrays() {
        LotteryArray lo = initLottery();
        lo.generateNewCombination(howMany, type, willBe);
        return lo.results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
