package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BeveragesServlet extends HttpServlet {

	@EJB
	private BeverageManagement<?, Beverage> beverageManagement;

	@EJB
	private IncentiveManagement<?, Incentive> incentiveManagement;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Incentive> incentives = this.incentiveManagement.getAllIncentives();
		System.out.println(incentives.size());
		request.getSession().setAttribute("incentives", incentives);
		request.getRequestDispatcher("/beverages.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String name = req.getParameter("beverage_name").trim();
			String manufacturer = req.getParameter("beverage_manufacturer").trim();
			double price = Double.valueOf(req.getParameter("beverage_price").trim());
			int quantity = Integer.valueOf(req.getParameter("beverage_quantity").trim());
			int incentiveId = Integer.valueOf(req.getParameter("incentive"));
			Beverage beverage = new Beverage();
			beverage.setName(name);
			beverage.setManufacturer(manufacturer);
			beverage.setPrice(price);
			beverage.setQuantity(quantity);
			if(incentiveId != -1){
				List<Incentive> incentives = (List<Incentive>) req.getSession().getAttribute("incentives");
				Optional<Incentive> incentive = incentives.stream().filter(incntv -> incntv.getId() == incentiveId).
						findAny();
				incentive.ifPresent(beverage::setIncentive);
			}
			beverageManagement.create(beverage);
			res.getWriter().println(beverage);
		}
		catch (NumberFormatException e){
			System.out.println("error parsing the value of price/quantity");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {

	}
}
