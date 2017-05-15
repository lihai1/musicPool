/**
 * Created by LihaiMac on 3/19/17.
 */
/**
 * @author LihaiMac
 */
package com.models;

import com.lotteryTools.LotteryArray;

import java.util.List;

import static com.dao.AppGlobalProperties.dataFile;

public class formAnalyzeRequest extends BaseRequest {
    public formAnalyzeRequest() {
    }

    public List<List> runRequest() {
        LotteryArray lo = initLottery();
        int[] arr = new  int[form.length-1];
        for(int i=0;i<form.length-1;i++) arr[i]=form[i];
        return lo.analyzeForm(arr);
    }
    protected LotteryArray initLottery(){
        LotteryArray lo = new LotteryArray(dataFile, "lottery");
        if (from != null && to != null)
            lo.privateArchive(from, to);
        return lo;
    }
}
