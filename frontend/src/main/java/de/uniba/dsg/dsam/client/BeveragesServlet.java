package de.uniba.dsg.dsam.client;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;
import de.uniba.dsg.dsam.persistence.OrderJMSQueueManagement;

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

	@EJB
	private OrderJMSQueueManagement<CustomerOrder> orderJMSQueueManagement;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Incentive> incentives = incentiveManagement.getAll();
		List<Beverage> beverages = beverageManagement.getAll();
		request.getSession().setAttribute("incentives", incentives);
		request.getSession().setAttribute("beverages", beverages);
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
			String id = req.getParameter("beverage_id");
			List<Beverage> beverages = beverageManagement.getAll();
			Beverage beverage;

			if(id != null){
				beverage = beverages.stream().filter(beverage1 -> beverage1.getId() == Integer.valueOf(id)).findAny().get();
			}
			else {
				beverage = new Beverage();
			}

			beverage.setName(name);
			beverage.setManufacturer(manufacturer);
			beverage.setPrice(price);
			beverage.setQuantity(quantity);
			if(incentiveId != -1){
				List<Incentive> incentives = incentiveManagement.getAll();
				Optional<Incentive> incentive = incentives.stream().filter(incntv -> incntv.getId() == incentiveId).
						findAny();
				incentive.ifPresent(beverage::setIncentive);
			}

			if(id != null){beverageManagement.update(beverage);}
			else {
				beverage = beverageManagement.create(beverage);
				beverages.add(beverage);
			}
			req.getSession().setAttribute("beverages", beverages);
			req.getRequestDispatcher("/beverages.jsp").forward(req, res);
		}
		catch (NumberFormatException e){
			System.out.println("error parsing the value of price/quantity");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		try{
			int id = Integer.valueOf(req.getParameter("id"));
			int flag=0;
			List<Beverage> beverages = beverageManagement.getAll();
			List<CustomerOrder> customerOrders = orderJMSQueueManagement.getAll();
			for(CustomerOrder customerOrder: customerOrders){
				if(customerOrder.getOrderItems().getId()==id){
					res.getWriter().write("Not Delete");
					break;
				}
				else {
					flag =1;
				}
				if (flag==1){
					Beverage beverage = beverages.stream().filter(beverage1 -> beverage1.getId() == id).findAny().get();
					beverageManagement.deleteOne(beverage);
					res.getWriter().write("Delete");
				}
			}
		}
		catch (NumberFormatException e){
			System.out.println("Invalid ID "+ e.getMessage());
		}


	}
}
