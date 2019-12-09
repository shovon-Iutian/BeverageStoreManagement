package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class IncentiveServlet
 */
public class IncentiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IncentiveManagement<?, Incentive> incentiveManager;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Incentive> incentives = this.incentiveManager.getAll();
		System.out.println(incentives.size());
		request.getSession().setAttribute("incentives", incentives);
		request.getRequestDispatcher("/incentives.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String incentiveName = request.getParameter("incentive_name").trim();
		String incentiveType = request.getParameter("incentive_type");
		String id = request.getParameter("incentive_id");
		List<Incentive> incentives = this.incentiveManager.getAll();
		Incentive incentive;

		if(id != null){
			incentive = incentives.stream().filter(incnt -> incnt.getId() == Integer.valueOf(id)).findAny().get();
			incentive.setName(incentiveName);
			incentiveManager.update(incentive);
		}
		else{
			if(incentiveType.equals("trial_package")){
				incentive = new TrialPackage();
			}
			else{
				incentive = new PromotionalGift();
			}
			incentive.setName(incentiveName);
			incentive = incentiveManager.create(incentive);
			incentives.add(incentive);
		}
		request.getSession().setAttribute("incentives", incentives);
		request.getRequestDispatcher("/incentives.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		List<Incentive> incentives = this.incentiveManager.getAll();
		Incentive incentive = incentives.stream().filter(incnt -> incnt.getId() == Integer.valueOf(id)).findAny().get();
		incentiveManager.deleteOne(incentive);
	}

}
