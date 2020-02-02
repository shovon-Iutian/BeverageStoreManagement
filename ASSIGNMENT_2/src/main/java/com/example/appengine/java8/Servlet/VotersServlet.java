package com.example.appengine.java8.Servlet;


import com.example.appengine.java8.DTO.Voter;
import com.example.appengine.java8.Constants;
import com.example.appengine.java8.modifiedexceptions.VotException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VotersServlet extends HttpServlet {

    private String idProperty= Constants.VOTER_ID_PROPERTY;
    private String emailProperty= Constants.VOTER_EMAIL_PROPERTY;
    private String nameProperty= Constants.VOTER_NAME_PROPERTY;
    private static Logger logger = Logger.getLogger
            (VotersServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Voter> voterList=new ArrayList<>();
        VotManagement voterManaging=new VotManagement();
        try {
            voterList = voterManaging.getVoterList();
        }catch (VotException e){
            logger.severe("Error fetching voters" + e.getMessage());
        }
        req.setAttribute("voterList",voterList);
//        req.getRequestDispatcher("/voterManagement.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            VotManagement voterManaging = new VotManagement();
            String email = req.getParameter(emailProperty);
            String name = req.getParameter(nameProperty);
            voterManaging.addVoter(name, email);
        }catch (VotException e){
            logger.severe("Error adding voter" + e.getMessage());
            resp.getWriter().write(e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean updatedResponse=false;
        try {
            VotManagement voterManaging = new VotManagement();
            String token = req.getParameter(Constants.VOTER_TOKEN_PROPERTY);
            Boolean reminder = Boolean.valueOf(req.getParameter(Constants.VOTER_REMINDER_PROPERTY));
            Boolean emailSent = Boolean.valueOf(req.getParameter(Constants.VOTER_EMAILSENT_PROPERTY));
            String name = req.getParameter(nameProperty);
            String id = req.getParameter(idProperty);
            String email = req.getParameter(emailProperty);
            updatedResponse = voterManaging.updateVoter(id, name, email,token,emailSent,
                    reminder);

            resp.getWriter().write(String.valueOf(updatedResponse));
        }catch (VotException e){
            logger.severe("Error updating voter" + e.getMessage());
            resp.getWriter().write(e.getMessage());
        }

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean deletedResult=false;
        try {
            VotManagement voterManaging = new VotManagement();
            String id = req.getParameter(idProperty);
            deletedResult = voterManaging.delete(id);
            resp.getWriter().write(String.valueOf(deletedResult));
        }catch (VotException e){
            logger.severe("Error deleting voter" + e.getMessage());
            resp.getWriter().write(e.getMessage());
        }
    }
}
