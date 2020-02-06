package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.DTO.Candidates;
import com.example.appengine.java8.DTO.VoteTime;
import com.example.appengine.java8.Entity.CandidatesEntity;
import com.example.appengine.java8.Entity.VoteTimeEntity;
import com.example.appengine.java8.Management.CandidatesManagement;
import com.example.appengine.java8.Management.VoteTimeManagement;
import com.example.appengine.java8.Service.CandidateManagementService;
import com.example.appengine.java8.Service.VoteTimeManagementService;
import com.google.appengine.api.datastore.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "VoteCasting", value = "/public/votecastingingbooth")
public class VoteCastingServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(VoteCastingServlet.class.getName());

    private Calendar calendar = Calendar.getInstance();
    private CandidateManagementService<Candidates> candidateManagementService = new CandidatesManagement();
    private VoteTimeManagementService<VoteTime> voteTimeManagementService = new VoteTimeManagement();
    private CandidatesEntity candidatesEntity = new CandidatesEntity();
    private VoteTimeEntity voteTimeEntity = new VoteTimeEntity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VoteTime voteTime = new VoteTime();
        try {
            Query candidatesQuery = new Query(candidatesEntity.getCandidateKind());
            Query votingTimeQuery = new Query(voteTimeEntity.getVoteTimeKind());
            List<Candidates> candidatesList = candidateManagementService.get(candidatesQuery);
            List<VoteTime> voteTimes = voteTimeManagementService.get(votingTimeQuery);
            if(voteTimes != null) voteTime = voteTimes.get(0); // set voting schedule
            calendar.setTime(new Date()); // sets calender time
            Date today = calendar.getTime();
            Date voteTimeStartDate = voteTime.getStartdate();
            Date voteTimeEndDate = voteTime.getEnddate();

            if(today.after(voteTimeEndDate)) {
                req.getRequestDispatcher("/votingresults.jsp").forward(req, resp);
            }
            else if(today.after(voteTimeStartDate) && (today.before(voteTimeEndDate))) {
                if (candidatesList != null) req.getSession().setAttribute("candidates", candidatesList);
                req.getRequestDispatcher("/votecast.jsp").forward(req, resp);
            }
            else {
                req.getRequestDispatcher("/notice.jsp").forward(req, resp);
            }
        } catch (Exception e){
            logger.severe("Unable to load voting booth page " + e.getMessage());
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
