package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.ReportEntity;
import de.uniba.dsg.dsam.persistence.SalesManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReportServlet")
public class ReportServlet extends HttpServlet {

    @EJB
    private SalesManagement salesManagement;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ReportEntity> summaryEntities =  salesManagement.getSummaryReport();
        List<ReportEntity> detailedEntities =  salesManagement.getReportOnIncentive();
        request.getSession().setAttribute("summaryReport", summaryEntities);
        request.getSession().setAttribute("detailedReport", detailedEntities);
        request.getRequestDispatcher("/report.jsp").forward(request, response);
    }
}
