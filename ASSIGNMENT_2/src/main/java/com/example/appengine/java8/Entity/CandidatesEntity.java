package com.example.appengine.java8.Entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class CandidatesEntity extends ParentsEntity {
    private String candidateKind = "Candidates";
    private Key candidateKey = KeyFactory.createKey(getParentsKind(), getParentsKey());
    private String candidateFirstProperty = "FirstName";
    private String candidateSecondProperty = "SurName";
    private String candidateThirdProperty = "Faculty";

    public String getCandidateKind() {
        return candidateKind;
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
