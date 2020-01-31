package com.example.appengine.java8.Management;

import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.modifiedexceptions.VotException;
import com.example.appengine.java8.Constants;
import com.example.appengine.java8.Service.VotService;
import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VotManagement implements VotService {
    private Key electionKey;
    private Transaction txn;
    private boolean invalid;
    private boolean valid = Constants.VALID;
    private String voterskind;
    private String nameProperty;
    private String emailProperty;
    private String tokenProperty;
    private String emailSentProperty;
    private String reminderProperty;
    private DatastoreService ds;
    private String isVotedProperty;


    public VotManagement() {
        electionKey = KeyFactory.createKey(Constants.ROOTKIND, Constants.ROOTANCESTOR);
        invalid = Constants.INVALID;
        voterskind = Constants.VOTERS;
        nameProperty = Constants.VOTER_NAME_PROPERTY;
        emailProperty = Constants.VOTER_EMAIL_PROPERTY;
        tokenProperty = Constants.VOTER_TOKEN_PROPERTY;
        emailSentProperty = Constants.VOTER_EMAILSENT_PROPERTY;
        reminderProperty = Constants.VOTER_REMINDER_PROPERTY;
        isVotedProperty = Constants.VOTER_ISVOTED_PROPERTY;
        ds = DatastoreServiceFactory.getDatastoreService();
    }


    @Override
    public List<Voter> getVoterList() throws VotException {

        List<Voter> voterList;
        Query q = new Query(voterskind).setAncestor(electionKey);
        PreparedQuery pq = ds.prepare(q);
        List<Entity> voters = pq.asList(FetchOptions.Builder.withDefaults());
        voterList = convert(voters);
        return voterList;
    }


    @Override
    public void addVoter(String name, String email) throws VotException {

        checkVoterName(name);
        checkVoterEmail(email);
        checkEmailUnique(email, null);
        txn = ds.beginTransaction();
        try {
            Entity voter = new Entity(voterskind, electionKey);
            voter.setProperty(nameProperty, name);
            voter.setProperty(emailProperty, email);
            voter.setProperty(tokenProperty, null);
            voter.setProperty(emailSentProperty, false);
            voter.setProperty(reminderProperty, false);
            voter.setProperty(isVotedProperty, false);
            ds.put(txn, voter);
            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    private void checkEmailUnique(String email, Key k) throws VotException {
        Query.FilterPredicate emailfilter=new Query.FilterPredicate(emailProperty, Query.FilterOperator.EQUAL,email);
        Query q = new Query(voterskind).setAncestor(electionKey).setFilter(emailfilter);
        PreparedQuery pq = ds.prepare(q);
        Entity voter = pq.asSingleEntity();
        if(k==null&&voter!=null)
            throw new VotException("A user with this email already exists");
        else if(k!=null && voter!=null && voter.getKey().getId()!=k.getId())
            throw new VotException("A user with this email already exists");
    }


    private List<Voter> convert(List<Entity> voters) throws VotException {

        List<Voter> voterList = new ArrayList<>();
        try {

            for (Entity voterEntity : voters) {
                Voter voter = new Voter(voterEntity.getKey(),
                        (String) voterEntity.getProperty(emailProperty),
                        (String) voterEntity.getProperty(nameProperty),
                        (String) voterEntity.getProperty(tokenProperty),
                        (Boolean) voterEntity.getProperty(emailSentProperty),
                        (Boolean) voterEntity.getProperty(reminderProperty),
                        (Boolean) voterEntity.getProperty(isVotedProperty));
                voterList.add(voter);
            }

        } catch (Exception e) {
            throw new VotException("Could not convert entity to object " + e.getMessage());
        }
        return voterList;
    }



    @Override
    public Boolean updateVoter(String id, String name, String email, String token,
                               Boolean emailSent, Boolean reminder) throws VotException {
        checkVoterId(id);
        Key k = KeyFactory.createKey(electionKey, voterskind, Long.valueOf(id));
        Entity voter;
        checkVoterName(name);
        checkVoterEmail(email);
        checkEmailUnique(email,k);
        txn = ds.beginTransaction();
        try {
            voter = ds.get(k);
            voter.setProperty(nameProperty, name);
            voter.setProperty(emailProperty, email);
            voter.setProperty(tokenProperty, token);
            voter.setProperty(emailSentProperty, emailSent);
            voter.setProperty(reminderProperty, reminder);
            ds.put(txn, voter);
            txn.commit();
            return this.valid;
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
        return this.invalid;
    }


    @Override
    public Boolean delete(String id) throws VotException {
        checkVoterId(id);
        txn = ds.beginTransaction();
        Key k = KeyFactory.createKey(electionKey, voterskind, Long.valueOf(id));
        try {
            ds.delete(txn, k);
            txn.commit();
            return valid;
        }finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }


    /*VALIDATIONS*/
    private boolean checkString(String stringval) {
        if (stringval.length() == 0 || stringval.equals(null))
            return this.invalid;
        else
            return this.valid;
    }

    private void checkVoterId(String id) throws VotException {
        if (!checkString(id))
            throw new VotException("Voter id is invalid");
    }

    private void checkVoterEmail(String email) throws VotException {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new VotException("voter email is invalid");
    }

    private void checkVoterName(String name) throws VotException {
        if (!checkString(name))
            throw new VotException("Voter name is invalid");
    }


}

