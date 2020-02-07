package com.example.appengine.java8.Management;

import com.example.appengine.java8.DTO.Candidates;
import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.Entity.CandidatesEntity;
import com.example.appengine.java8.Entity.VoteEntity;
import com.example.appengine.java8.Service.CandidateManagementService;
import com.example.appengine.java8.Service.VotService;
import com.example.appengine.java8.Service.VoteCastingService;
import com.example.appengine.java8.modifiedexceptions.VotException;
import com.google.appengine.api.datastore.*;

import java.util.List;
import java.util.logging.Logger;

public class VoteCastingManagement implements VoteCastingService {
    private Logger logger = Logger.getLogger(VoteCastingManagement.class.getName());

    @Override
    public Boolean castVote(Candidates candidate, Voter voter) {
        CandidateManagementService<Candidates> candidateManagementService = new CandidatesManagement();
        VotService<Voter> voterVoteService = new VoteManagement();
        VoteEntity voteEntity = new VoteEntity();
        CandidatesEntity candidatesEntity = new CandidatesEntity();
        try {
            Query.Filter filteredByToken = new Query.FilterPredicate(voteEntity.getVOTER_TOKEN_PROPERTY(), Query.FilterOperator.EQUAL, voter.getToken());
            Query.Filter filteredByCastedVote = new Query.FilterPredicate(voteEntity.getVOTER_ISVOTED_PROPERTY(), Query.FilterOperator.EQUAL, false);
            Query.Filter tokenAndCastedVoteFilter = Query.CompositeFilterOperator.and(filteredByToken, filteredByCastedVote);
            Query uncastedVoteQuery = new Query(voteEntity.getVoterKind()).setAncestor(voteEntity.getVoterKey()).setFilter(tokenAndCastedVoteFilter);
            List<Voter> eligibleVoters = voterVoteService.get(uncastedVoteQuery);
            if(eligibleVoters != null && !eligibleVoters.isEmpty()) {
                Voter eligibleVoter = eligibleVoters.get(0);
                if (candidate != null) {
                    candidate = candidateManagementService.getById(KeyFactory.createKey(candidatesEntity.getCandidateKey(), candidatesEntity.getCandidateKind(), candidate.getId()));
                    long earnedVote = candidate.getEarnedVote();
                    earnedVote++;
                    candidate.setEarnedVote(earnedVote);
                    candidate = candidateManagementService.update(candidate);
                    eligibleVoter.setId(eligibleVoter.getKey().getId());
                    eligibleVoter.setVoted(true);
                    voter = voterVoteService.update(eligibleVoter);
                }
                else {
                    throw new VotException("If you have already casted your vote than please come back after the voting period is over to see " +
                            "the results.If not make sure you are a registered voter.");
                }
            }
            else {
                throw new VotException("If you have already casted your vote than please come back after the voting period is over to see " +
                        "the results.If not make sure you are a registered voter.");
            }
            return true;
        } catch (VotException | EntityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
