package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.DTO.Candidates;
import com.example.appengine.java8.Entity.CandidatesEntity;
import com.example.appengine.java8.Management.CandidatesManagement;
import com.google.appengine.api.datastore.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "Candidates", value = "/candidates")
public class CandidatesServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(CandidatesServlet.class.getName());

    private Candidates candidates = new Candidates();
//    private CandidateManagementService<Candidates> candidateManagementService; // Need to look into NullPointer Exception
    private CandidatesManagement candidateManagementService = new CandidatesManagement();
    private CandidatesEntity candidatesEntity = new CandidatesEntity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        candidates.setFirstName("Mehedi");
        candidates.setSurName("Shovon");
        candidates.setFaculty("CSE");
        candidates = candidateManagementService.create(candidates);
        System.out.println(candidates.toString());
        Query query = new Query(candidatesEntity.getCandidateKind());
        System.out.println(query.toString());
        List<Candidates> candidatesList = candidateManagementService.getAll(query);
        if(candidatesList != null) req.getSession().setAttribute("candidates", candidatesList);
        for (Candidates candidate : candidatesList) {
            System.out.println(candidate.toString());
        }
        req.getRequestDispatcher("/candidatesManagementForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Candidates candidates = new Candidates();
        candidates.setFirstName("Mehedi");
        candidates.setSurName("Shovon");
        candidates.setFaculty("CSE");
        candidates = candidateManagementService.create(candidates);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
