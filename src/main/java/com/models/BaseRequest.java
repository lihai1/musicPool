package com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lotteryTools.LotteryArray;
import com.lotteryTools.lotteryToolsCommon;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dao.AppGlobalProperties.dataFile;


/**
 * Created by LihaiMac on 4/19/17.
 */
@MappedSuperclass
public abstract class BaseRequest implements Serializable{

    @Getter
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty
    protected Date from;

    @Getter
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty
    protected Date to;

    @JsonProperty
    protected int howMany;
    @JsonProperty
    protected int type;
    @JsonProperty
    protected int willBe[];
    @JsonProperty
    protected lotteryToolsCommon strong;
    @JsonProperty
    protected int form[];


    BaseRequest(){}
    abstract List<List> runRequest();

    public void setFrom(String from) {

        try {
            this.from = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(from).toGregorianCalendar().getTime();//Date.from( Instant.parse( from));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void setTo(String to) {

        try {
            this.to = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(to).toGregorianCalendar().getTime();//Date.from( Instant.parse( from));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int[] getWillbe() {
        return willBe;
    }

    public void setWillbe(int[] willbe) {
        this.willBe = willbe;
    }

    public lotteryToolsCommon getStrong() {
        return strong;
    }

    public void setStrong(lotteryToolsCommon strong) {
        this.strong = strong;
    }
    protected LotteryArray initLottery(){
        LotteryArray lo = new LotteryArray();
        switch (strong) {
            case STRONG:
                lo.setFrequentCombo();
                break;
            case WEAK:
                lo.setLessFrequentCombo();
                break;
            case RANDOM:
                lo.setRandomCombo();
                break;
        }
        lo = new LotteryArray(dataFile, "lottery");
        if (from != null && to != null)
            lo.privateArchive(from, to);
        return lo;
    }
    public List<List> toLists(int[][] arr){
        List res = new ArrayList<List>();
        for(int[] rs:arr){
            res.add(rs);
        }
        return res;
    }

}
