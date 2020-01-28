package com.example.appengine.java8.Entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class CandidatesEntity {
    private String candidateKind = "Candidate";
    private String candidateParentsKind = "Election";
    private String candidateParentsKey = "election";
    private Key candidateKey = KeyFactory.createKey(candidateParentsKind, candidateParentsKey);
    private String candidateFirstProperty = "FirstName";
    private String candidateSecondProperty = "SurName";
    private String candidateThirdProperty = "Faculty";

    public String getCandidateKind() {
        return candidateKind;
    }

    public String getCandidateParentsKind() {
        return candidateParentsKind;
    }

    public String getCandidateParentsKey() {
        return candidateParentsKey;
    }

    public Key getCandidateKey() {
        return candidateKey;
    }

    public String getCandidateFirstProperty() {
        return candidateFirstProperty;
    }

    public String getCandidateSecondProperty() {
        return candidateSecondProperty;
    }

    public String getCandidateThirdProperty() {
        return candidateThirdProperty;
    }
}
