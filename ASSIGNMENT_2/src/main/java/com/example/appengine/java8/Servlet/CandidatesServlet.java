package com.example.appengine.java8.Servlet;

import com.example.appengine.java8.DTO.Candidates;
import com.example.appengine.java8.Entity.CandidatesEntity;
import com.example.appengine.java8.Management.CandidatesManagement;
import com.example.appengine.java8.Service.CandidateManagementService;
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
    private CandidateManagementService<Candidates> candidateManagementService = new CandidatesManagement();
    private CandidatesEntity candidatesEntity = new CandidatesEntity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Candidates "creation" mechanism
        candidates.setKey(candidatesEntity.getCandidateKey());
        candidates.setFirstName("Mehedi");
        candidates.setSurName("Shovon");
        candidates.setFaculty("CSE");
        candidates = candidateManagementService.create(candidates);
        candidates.setKey(candidatesEntity.getCandidateKey());
        candidates.setFirstName("Mehedi");
        candidates.setSurName("Shovon");
        candidates.setFaculty("SWT");
        candidates = candidateManagementService.create(candidates);

        // Candidates "deletion" mechanism
        Query.Filter newFilter = new Query.FilterPredicate("Faculty", Query.FilterOperator.EQUAL, "SWT");
        Query filterQuery = new Query(candidatesEntity.getCandidateKind()).setFilter(newFilter);
        candidateManagementService.delete(filterQuery);

        // Candidates "update" mechanism
        newFilter = new Query.FilterPredicate("Faculty", Query.FilterOperator.EQUAL, "CSE");
        filterQuery = new Query(candidatesEntity.getCandidateKind()).setFilter(newFilter);
        List<Candidates> specificCandidatesList = candidateManagementService.get(filterQuery);
        for (Candidates specificCandidate : specificCandidatesList) {
            candidates.setKey(specificCandidate.getKey());
            candidates.setFaculty("ISSYSC");
            candidates = candidateManagementService.update(candidates);
        }

        // Candidates "getall" mechanism
        Query query = new Query(candidatesEntity.getCandidateKind());
        List<Candidates> candidatesList = candidateManagementService.get(query);
        if(candidatesList != null) req.getSession().setAttribute("candidates", candidatesList);
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
