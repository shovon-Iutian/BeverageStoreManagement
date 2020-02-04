package com.example.appengine.java8.Servlet;


import com.example.appengine.java8.DTO.Candidates;
import com.example.appengine.java8.DTO.VoteTime;
import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.Constants;
import com.example.appengine.java8.Entity.VoteEntity;
import com.example.appengine.java8.Entity.VoteTimeEntity;
import com.example.appengine.java8.Management.VoteManagement;
import com.example.appengine.java8.Management.VoteTimeManagement;
import com.example.appengine.java8.modifiedexceptions.VotException;
import com.google.appengine.api.datastore.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@WebServlet(name = "Voter", value = "/voter")
public class VotersServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger
            (VotersServlet.class.getName());
    private VoteManagement voterManaging = new VoteManagement();
    private VoteEntity voteEntity = new VoteEntity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Query query = new Query(voteEntity.getROOTKIND());
        System.out.println(query.toString());
        List<Voter> voterList = voterManaging.getAll(query);
        if(voterList != null) req.getSession().setAttribute("voterList", voterList);
        req.getRequestDispatcher("/voterManagement.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Voter voter = new Voter();
        String email = req.getParameter(voteEntity.getVOTER_EMAIL_PROPERTY());
        String name = req.getParameter(voteEntity.getVOTER_NAME_PROPERTY());
//            voterManaging.addVoter(name, email);

        voter.setName(name);
        voter.setEmail(email);
        voter.setEmailSent(false);
        voter.setVoted(false);
        voter.setReminder(false);
        voter.setToken(null);
        voterManaging.create(voter);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean updatedResponse=false;

        Voter voter = new Voter();
        String token = req.getParameter(Constants.VOTER_TOKEN_PROPERTY);
        Boolean reminder = Boolean.valueOf(req.getParameter(Constants.VOTER_REMINDER_PROPERTY));
        Boolean emailSent = Boolean.valueOf(req.getParameter(Constants.VOTER_EMAILSENT_PROPERTY));
        String name = req.getParameter(voteEntity.getVOTER_NAME_PROPERTY());
        String email = req.getParameter(voteEntity.getVOTER_EMAIL_PROPERTY());
//            updatedResponse = voterManaging.updateVoter(id, name, email,token,emailSent,reminder);
        voter.setName(name);
        voter.setEmail(email);
        voter.setEmailSent(emailSent);
        voter.setReminder(reminder);
        voter.setToken(token);
        voterManaging.update(voter);
        resp.getWriter().write(String.valueOf(updatedResponse));

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean deletedResult=false;
        VoteManagement voterManaging = new VoteManagement();
        String id = req.getParameter(idProperty);
//            deletedResult = voterManaging.delete(id);
        resp.getWriter().write(String.valueOf(deletedResult));
    }
}
