package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class IncentiveServlet
 */
public class IncentiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IncentiveManagement<?, Incentive> incentiveManager;

	@EJB
	private BeverageManagement<?, Beverage> beverageManagement;

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

		Map<String,String> messages = new HashMap<String, String>();
		String incentiveName = request.getParameter("incentive_name").trim();
		String incentiveType = request.getParameter("incentive_type");
		String id = request.getParameter("incentive_id");
		List<Incentive> incentives = this.incentiveManager.getAll();
		Incentive incentive;

		if(id != null){
			incentive = incentives.stream().filter(incnt -> incnt.getId() == Integer.valueOf(id)).findAny().get();
			if (incentiveName == null || incentiveName.isEmpty()){
				messages.put("incentive_name", "Please enter an Incentive name");
			}
			else{
				incentive.setName(incentiveName);
			}
			if (messages.isEmpty()){
				incentiveManager.update(incentive);
				messages.put("noErrors", "Updated successfully");
				request.setAttribute("messages", messages);
				request.getSession().setAttribute("incentives", incentives);
				request.getRequestDispatcher("/incentives.jsp").forward(request, response);
			}
			else{
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("/incentive_form.jsp?id="+id).forward(request, response);
			}

		}
		else{
			if(incentiveType.equals("trial_package")){
				incentive = new TrialPackage();
			}
			else{
				incentive = new PromotionalGift();
			}
			if (incentiveName == null || incentiveName.isEmpty()){
				messages.put("incentive_name", "Please enter Incentive name");
			}
			else{
				incentive.setName(incentiveName);
			}
			if (messages.isEmpty()){
				incentive = incentiveManager.create(incentive);
				incentives.add(incentive);
				messages.put("noErrors", "Incentive added successfully");
				request.setAttribute("messages", messages);
				request.getSession().setAttribute("incentives", incentives);
				request.getRequestDispatcher("/incentives.jsp").forward(request, response);
			}
			else{
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("/incentive_form.jsp").forward(request, response);
			}

		}

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int id = Integer.valueOf(request.getParameter("id"));
			int i =0;
			List<Incentive> incentives = this.incentiveManager.getAll();
			List<Beverage> beverages = this.beverageManagement.getAll();
			for(Beverage beverage : beverages){
				if(beverage.getIncentive().isPresent()){
					Incentive incentive_details= beverage.getIncentive().get();
					if(incentive_details.getId()== id){
						response.getWriter().write("Not Delete");
						break;
					}
					else{
						i=1;

					}
				}
			}
			if(i==1){
				Incentive incentive = incentives.stream().filter(incnt -> incnt.getId() == Integer.valueOf(id)).findAny().get();
				incentiveManager.deleteOne(incentive);
				response.getWriter().write("Delete");
			}
		}
		catch (NumberFormatException e){
			System.out.println("Invalid ID "+ e.getMessage());
		}
	}
}
