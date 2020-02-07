package com.example.appengine.java8.Management;

import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.Service.UploadVoterInterface;
import com.google.appengine.api.datastore.Query;

import java.util.List;
import java.util.logging.Logger;

public class UploadVoter implements UploadVoterInterface {
    private static Logger logger = Logger.getLogger
            (UploadVoter.class.getName());


    @Override
    public void uploadFile(String[] data)  {
        if(data[0] !=null && data[1]!=null) {
            List<Voter> voterList = null;
            VoteManagement voterManaging = new VoteManagement();
            try {

                Query query = new Query("Election");
               voterList = voterManaging.get(query);
                if (!voterList.stream().filter(o -> o.getEmail().equals(data[1].trim())).findFirst().isPresent()) {
                    Voter voter = new Voter();
                    voter.setName(data[0]);
                    voter.setEmail(data[1]);
                    voter.setEmailSent(false);
                    voter.setVoted(false);
                    voter.setReminder(false);
                    voter.setToken("Empty");
                    voterManaging.create(voter);
                }
            } catch (Exception e) {

                logger.severe("Error adding voter" + e.getMessage());
            }
        }
    }
}
