/**
 * 
 */
package de.uniba.dsg.dsam.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.model.CustomerOrder;

/**
 * @author Mohammed Mehedi Hasan
 *
 */
public class OrderJMSQueueServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(OrderJMSQueueServlet.class.getName());
	
	@EJB
	OrderJMSQueueSender sender;
	
	/**
	 * Default constructor.
	 */
	public OrderJMSQueueServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("beverage_name").trim();
		FileOutputStream writer = new FileOutputStream("D:\\Workspace\\DSAM\\Assignment01\\Abdullah\\debug.txt");
	    writer.write(name.getBytes(), 0, name.length());
	     
	    Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(name);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CustomerOrder customerOrder = new CustomerOrder();
		
		customerOrder.setIssueDate(date1);
		name = name + "update";
		
		sender.sendMessage(customerOrder);
		writer.write(name.getBytes(), 0, name.length());
		
		writer.close();
		
		resp.sendRedirect("/frontend");
	}
}
