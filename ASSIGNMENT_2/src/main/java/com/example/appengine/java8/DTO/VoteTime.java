package com.example.appengine.java8.DTO;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import java.util.Date;

public class VoteTime implements Serializable {
    private Key key;
    private Date startDate;
    private Date endDate;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return "VoteTime{" +
                "key=" + key +
                "startDate"+ startDate +'\'' +
                "endDate"+ endDate + '\'' +
                '}';
    }
}
