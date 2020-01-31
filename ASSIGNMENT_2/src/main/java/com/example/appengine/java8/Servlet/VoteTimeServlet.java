package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.DTO.VoteTime;
import com.example.appengine.java8.Entity.VoteTimeEntity;
import com.example.appengine.java8.Management.VoteTimeManagement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VoteTime", value = "/votingtime")
public class VoteTimeServlet extends HttpServlet {

    private VoteTime voteTime = new VoteTime();

    private VoteTimeManagement voteTimeManagement = new VoteTimeManagement();
    private VoteTimeEntity voteTimeEntity = new VoteTimeEntity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        voteTime.setStartDate("29/02/2020");
        voteTime.setEndDate("29/03/2020");
        voteTime = voteTimeManagement.create(voteTime);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
