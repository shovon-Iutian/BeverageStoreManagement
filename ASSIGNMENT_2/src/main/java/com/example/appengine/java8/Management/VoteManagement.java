package com.example.appengine.java8.Management;

import com.example.appengine.java8.DTO.Candidates;
import com.example.appengine.java8.DTO.VoteTime;
import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.Entity.VoteEntity;
import com.example.appengine.java8.Service.VotService;
import com.example.appengine.java8.Service.VoteTimeManagementService;
import com.example.appengine.java8.modifiedexceptions.CandidateException;
import com.example.appengine.java8.modifiedexceptions.VotException;
import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoteManagement extends AbstractCrudManagement<Voter> implements VotService<Voter> {

    private VoteEntity voteEntity = new VoteEntity();
    private Transaction txn;
    private DatastoreService ds;

    @Override
    protected Entity convertDtoToEntity(Voter voter) {
        Entity votingEntity= new Entity(voteEntity.getROOTKIND(),voteEntity.getROOTANCESTOR());
        votingEntity.setProperty(voteEntity.getVOTER_NAME_PROPERTY(), voter.getName());
        votingEntity.setProperty(voteEntity.getVOTER_EMAIL_PROPERTY(), voter.getEmail());
        votingEntity.setProperty(voteEntity.getVOTER_EMAILSENT_PROPERTY(), voter.getEmailSent());
        votingEntity.setProperty(voteEntity.getVOTER_ISVOTED_PROPERTY(), voter.getVoted());
        votingEntity.setProperty(voteEntity.getVOTER_REMINDER_PROPERTY(), voter.getReminder());
        votingEntity.setProperty(voteEntity.getVOTER_TOKEN_PROPERTY(), voter.getToken());

        return votingEntity;
    }

    @Override
    protected Voter convertEntityToDto(Entity entity) {
        Voter voter = new Voter();
        voter.setKey(entity.getKey());
        voter.setName((String) entity.getProperty(voteEntity.getVOTER_NAME_PROPERTY()));
        voter.setEmail((String) entity.getProperty(voteEntity.getVOTER_EMAIL_PROPERTY()));
        voter.setEmailSent((boolean) entity.getProperty(voteEntity.getVOTER_EMAILSENT_PROPERTY()));
        voter.setVoted((boolean) entity.getProperty(voteEntity.getVOTER_ISVOTED_PROPERTY()));
        voter.setReminder((boolean) entity.getProperty(voteEntity.getVOTER_REMINDER_PROPERTY()));
        voter.setToken((String) entity.getProperty(voteEntity.getVOTER_TOKEN_PROPERTY()));

        return voter;
    }
    private boolean checkString(String stringval) {
        if (stringval.length() == 0 || stringval.equals(null))
            return false;
        else
            return true;
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

    private List<Voter> convert(List<Entity> voters) throws VotException {

        List<Voter> voterList = new ArrayList<>();
        try {

            for (Entity voterEntity : voters) {
                voterList.add(convertEntityToDto(voterEntity));
            }

        } catch (Exception e) {
            throw new VotException("Could not convert entity to object " + e.getMessage());
        }
        return voterList;
    }

    @Override
    public List<Voter> getUnnotifiedVoters() throws VotException {
        Query.Filter keyFilter = new Query.FilterPredicate(voteEntity.getVOTER_EMAILSENT_PROPERTY(),
                Query.FilterOperator.EQUAL, false);
        Query query = new Query(voteEntity.getVOTERS()).setAncestor(voteEntity.getElectionKey()).setFilter(keyFilter);
        PreparedQuery pq1 = ds.prepare(query);
        List<Entity> voters = pq1.asList(FetchOptions.Builder.withDefaults());
        List<Voter> voterList = convert(voters);
        return voterList;
    }


    @Override
    public List<Voter> getVoterListForReminderEmail() throws VotException {
        Query.Filter keyFilter = new Query.FilterPredicate(voteEntity.getVOTER_REMINDER_PROPERTY(),
                Query.FilterOperator.EQUAL, false);
        Query q = new Query(voteEntity.getVOTERS()).setAncestor(voteEntity.getElectionKey()).setFilter(keyFilter);
        PreparedQuery pq1 = ds.prepare(q);
        List<Entity> voters = pq1.asList(FetchOptions.Builder.withDefaults());
        List<Voter> voterList = convert(voters);
        return voterList;
    }


    @Override
    public int getPendingVoterCount() {
        Query.Filter keyFilter = new Query.FilterPredicate(voteEntity.getVOTER_ISVOTED_PROPERTY(),
                Query.FilterOperator.EQUAL, false);
        Query q = new Query(voteEntity.getVOTERS()).setAncestor(voteEntity.getElectionKey()).setFilter(keyFilter);
        PreparedQuery pq1 = ds.prepare(q);
        List<Entity> voters = pq1.asList(FetchOptions.Builder.withDefaults());
        return voters.size();
    }


    @Override
    public int getCastedVoterCount() {
        Query.Filter keyFilter = new Query.FilterPredicate(voteEntity.getVOTER_ISVOTED_PROPERTY(),
                Query.FilterOperator.EQUAL, true);
        Query q = new Query(voteEntity.getVOTERS()).setAncestor(voteEntity.getElectionKey()).setFilter(keyFilter);
        PreparedQuery pq1 = ds.prepare(q);
        List<Entity> voters = pq1.asList(FetchOptions.Builder.withDefaults());
        return voters.size();
    }


    @Override
    public Boolean castVoteWithToken(String candidateid, String token) throws VotException,CandidateException {

        Candidates v;
        txn = ds.beginTransaction();
        try {
            //voter list: with matched token ID and isvoted false
            Query.Filter tokenfilter = new Query.FilterPredicate(voteEntity.getVOTER_TOKEN_PROPERTY(),
                    Query.FilterOperator.EQUAL, token);
            Query.Filter isvotedfilter = new Query.FilterPredicate(voteEntity.getVOTER_ISVOTED_PROPERTY(),
                    Query.FilterOperator.EQUAL, false);
            Query.Filter tokenandvoted =
                    Query.CompositeFilterOperator.and(tokenfilter, isvotedfilter);
            Query q = new Query(voteEntity.getVOTERS()).setAncestor(voteEntity.getElectionKey()).setFilter(tokenandvoted);
            PreparedQuery pq1 = ds.prepare(q);
            Entity voter = pq1.asSingleEntity();

            // candidate list, to whom vote was casted
            if (voter != null) {
                CandidatesManagement candidateManagement = new CandidatesManagement();
                v = candidateManagement.getCandidateAndUpdateCount(candidateid);
                if (v != null) {
                    voter.setProperty(voteEntity.getVOTER_ISVOTED_PROPERTY(), true);
                    ds.put(txn, voter);
                }
            } else {
                throw new VotException("If you have voted please come back after the voting period to see" +
                        " the results.If not make sure you are a registered voter");
            }
            txn.commit();
            return true;
        }finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }
    }

    @Override
    public Voter getVoterByEmail(String email) throws VotException {
        checkVoterEmail(email);
        Query.FilterPredicate emailfilter=new Query.FilterPredicate(voteEntity.getVOTER_EMAIL_PROPERTY(), Query.FilterOperator.EQUAL,email);
        Query q = new Query(voteEntity.getVOTERS()).setAncestor(voteEntity.getElectionKey()).setFilter(emailfilter);
        PreparedQuery pq = ds.prepare(q);
        Entity voter = pq.asSingleEntity();

        Voter voter1=voter!=null?convert(Arrays.asList(voter)).get(0):null;
        return voter1;

    }
}
