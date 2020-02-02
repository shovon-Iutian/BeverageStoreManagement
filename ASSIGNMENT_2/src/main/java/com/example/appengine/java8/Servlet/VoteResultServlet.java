package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.DTO.Candidates;
import com.example.appengine.java8.DTO.VoteTime;
import com.example.appengine.java8.Entity.CandidatesEntity;
import com.example.appengine.java8.Management.CandidatesManagement;
import com.example.appengine.java8.Management.VoteTimeManagement;
import com.google.appengine.api.datastore.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "VoteTime", value = "/votingresult")
public class VoteResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VoteTimeManagement voteTimeManagement = new VoteTimeManagement();
        CandidatesManagement candidatesManagement = new CandidatesManagement();
        CandidatesEntity candidatesEntity = new CandidatesEntity();
        Query query = new Query(candidatesEntity.getCandidateKind());
        List<Candidates> candidatesList = new ArrayList<>();
        candidatesList = candidatesManagement.getAll(query);
        VoteTime voteTime = new VoteTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY,1);
        Date date = calendar.getTime();
        Date enddate= voteTime.getEndDate();
        if(date.after(enddate)){
            req.getRequestDispatcher("/votingresults.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/votingresultpublish.jsp").forward(req, resp);
        }




    }
}
