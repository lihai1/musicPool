/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.lotteryTools.LotteryArray;
import com.lotteryTools.lotteryToolsCommon;

import java.util.List;

/**
 * @author LihaiMac
 */
public class statsRequest extends BaseRequest{
    public statsRequest() {
    }

    public List<List> runRequest() {
        LotteryArray lo=super.initLottery();
        lo.buildTree(howMany);
        int[][] ans=lo.repeatingPares(howMany, type,strong==lotteryToolsCommon.STRONG?"strong":"weak");

        return toLists(ans);
    }
}
