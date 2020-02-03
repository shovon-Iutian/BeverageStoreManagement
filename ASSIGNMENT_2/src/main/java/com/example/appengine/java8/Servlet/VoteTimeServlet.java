package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.DTO.VoteTime;
import com.example.appengine.java8.Entity.VoteTimeEntity;
import com.example.appengine.java8.Management.VoteTimeManagement;
import com.google.appengine.api.datastore.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "VoteTime", value = "/votingtime")
public class VoteTimeServlet extends HttpServlet {

    private VoteTime voteTime = new VoteTime();

    private VoteTimeManagement voteTimeManagement = new VoteTimeManagement();
    private VoteTimeEntity voteTimeEntity = new VoteTimeEntity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
        Date startdate= null;
        try {
            startdate = simpleDateFormat.parse("2020-06-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        voteTime.setStartDate(startdate);
        Date enddate= null;

        try {
            enddate = simpleDateFormat.parse("2020-06-30 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        voteTime.setEndDate(enddate);
        voteTime = voteTimeManagement.create(voteTime);
        Query query = new Query(voteTimeEntity.getVoteTimeKind());
        List<VoteTime> voteTime = voteTimeManagement.get(query);
        if(voteTime !=null){
            req.getSession().setAttribute("voteTime",voteTime);
        }
        req.getRequestDispatcher("/votetimemanagement.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startdate = req.getParameter("startdate").trim();
        String enddate = req.getParameter("enddate").trim();
        //String id = req.getParameter("votetime_id").trim();
        VoteTime voteTime = new VoteTime();
        Query query = new Query(voteTimeEntity.getVoteTimeKind());
        List<VoteTime> voteTimes = voteTimeManagement.get(query);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //if(id!= null){
            //voteTime = voteTimes.stream().filter(voteTime1 -> voteTime1.getKey().getId()==Long.valueOf(id)).findAny().get();
            try {
                voteTime.setStartDate(simpleDateFormat.parse(startdate));
                voteTime.setEndDate(simpleDateFormat.parse(enddate));
                voteTimeManagement.create(voteTime);
            } catch (ParseException e) {
                System.out.println("error parsing the value startdate and enddate");
                e.printStackTrace();
            }
        //}
        req.getRequestDispatcher("/votetimemanagement.jsp").forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
