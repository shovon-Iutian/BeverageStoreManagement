package com.example.appengine.java8.Entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class VoteTimeEntity {
    private String voteTimeKind = "VoteTime";
    private String voteTimeParentsKind = "Election";
    private String voteTimeParentsKey = "election";
    private Key voteTimeKey = KeyFactory.createKey(voteTimeParentsKind, voteTimeParentsKey);
    private String voteTimeFirstProperty = "StartDate";
    private String voteTimeSecondProperty = "EndDate";

    public String getVoteTimeKind() {
        return voteTimeKind;
    }

    public String getVoteTimeParentsKind() {
        return voteTimeParentsKind;
    }

    public String getVoteTimeParentsKey() {
        return voteTimeParentsKey;
    }

    public Key getVoteTimeKey() {
        return voteTimeKey;
    }

    public String getVoteTimeFirstProperty() {
        return voteTimeFirstProperty;
    }

    public String getVoteTimeSecondProperty() {
        return voteTimeSecondProperty;
    }
}
