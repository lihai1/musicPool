package com.entities;

import com.dao.UserNumbersService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by LihaiMac on 5/6/17.
 */

@Entity
public class SavedNumbers implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    protected String numbers;

    @Setter
    //@JsonProperty
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateGenerated;

    @Setter
    //@JsonProperty
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateStart;

    @Setter
    //@JsonProperty
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateEnd;

    public String getDateGenerated() {
        return dateGenerated.toString();
    }

    public String getDateStart() {
        return dateStart.toString();
    }

    public String getDateEnd() {
        return dateEnd.toString();
    }

    @Getter
    @Setter
    @OneToMany(targetEntity = UserNumbers.class, mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<UserNumbers> user = new HashSet<>();

    public SavedNumbers() {

    }

    public SavedNumbers(List res) {
        dateGenerated = new Date();
        setNumbers(res);
    }

    public SavedNumbers(Date from,Date to,List res) {
        dateStart = from;
        dateEnd = to;
        dateGenerated = new Date();
        setNumbers(res);
    }
    public SavedNumbers(Date from,Date to,int[] res) {
        dateStart = from;
        dateEnd = to;
        dateGenerated = new Date();
        List<Integer> l = new ArrayList<>();
        for(int i=0;i<res.length;i++)
            l.add(res[i]);
        setNumbers(l);
    }

    public List<Integer> getNumbers() {
        return UserNumbersService.listFromString(numbers);
    }


    public void setNumbers(List<Integer> nums) {
        this.numbers = UserNumbersService.StringifyIntegerList(nums);
    }

}