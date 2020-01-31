package com.example.appengine.java8.DTO;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;

public class VoteTime implements Serializable {
    private Key key;
    private String startDate;
    private String endDate;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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
