package com.example.appengine.java8.Service;

import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.modifiedexceptions.VotException;

import java.util.List;

public interface VotService {

    List<Voter> getVoterList() throws VotException;

    void addVoter(String name, String email) throws VotException;

    Boolean updateVoter(String id, String name, String email, String token,
                        Boolean emailSent, Boolean reminder) throws VotException;

    Boolean delete(String id) throws VotException;


}
