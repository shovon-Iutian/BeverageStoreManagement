package com.example.appengine.java8.Entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class VoteEntity {

    private String VOTERS = "voters";
//    private String VOTER_ID_PROPERTY = "id";
    private String VOTER_NAME_PROPERTY = "name";
    private String VOTER_EMAIL_PROPERTY = "email";
    private String VOTER_TOKEN_PROPERTY = "token";
    private String VOTER_EMAILSENT_PROPERTY = "emailsent";

    //EMAIL RELATED

    private String VOTER_REMINDER_PROPERTY = "reminder";
    private String VOTER_ISVOTED_PROPERTY = "isvoted";

    //VALIDATION VALUES AND MESSAGES
    private boolean VALID = true;
    private boolean INVALID = false;

    //ROOT ANCESTOR FOR ALL KINDS
    private String ROOTANCESTOR = "election";
    private String ROOTKIND = "Election";

    public Key getElectionKey() {
        return electionKey;
    }

    private Key electionKey = KeyFactory.createKey(ROOTKIND, ROOTANCESTOR);

    public String getVOTERS() {
        return VOTERS;
    }

//    public String getVOTER_ID_PROPERTY() {
//        return VOTER_ID_PROPERTY;
//    }

    public String getVOTER_NAME_PROPERTY() {
        return VOTER_NAME_PROPERTY;
    }

    public String getVOTER_EMAIL_PROPERTY() {
        return VOTER_EMAIL_PROPERTY;
    }

    public String getVOTER_TOKEN_PROPERTY() {
        return VOTER_TOKEN_PROPERTY;
    }

    public String getVOTER_EMAILSENT_PROPERTY() {
        return VOTER_EMAILSENT_PROPERTY;
    }

    public String getVOTER_REMINDER_PROPERTY() {
        return VOTER_REMINDER_PROPERTY;
    }

    public String getVOTER_ISVOTED_PROPERTY() {
        return VOTER_ISVOTED_PROPERTY;
    }

    public boolean isVALID() {
        return VALID;
    }

    public boolean isINVALID() {
        return INVALID;
    }

    public String getROOTANCESTOR() {
        return ROOTANCESTOR;
    }

    public String getROOTKIND() {
        return ROOTKIND;
    }

}
