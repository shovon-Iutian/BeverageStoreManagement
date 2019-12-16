<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>
<%@ page import="java.util.List" %>
<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Incentive</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<%
			String strId = request.getParameter("id");
			Beverage beverage = null;
			if(strId != null){
				int id = Integer.valueOf(strId);
				List<Beverage> beverages = (List<Beverage>) request.getSession().getAttribute("beverages");
				for(Beverage bev: beverages){
					if(bev.getId() == id){
						beverage = bev;
						break;
					}
				}
		%>
		<h1>Update Beverages</h1>
		<% }else{ %>
		<h1>New Beverage</h1>
		<% } %>
		<p>&nbsp;</p>
		
		<form role="form" action="/frontend/beverages" method="post">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Beverage Name</span>
					<span class="text-danger">${messages.beverage_name}</span>
					<input name="beverage_name" type="text" class="form-control" <% if(beverage != null){ %>
						   value="<%= beverage.getName()%>">
					<input type="hidden" name="beverage_id" value="<%= beverage.getId()%>">
					<% }%>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Beverage Manufacturer</span>
					<span class="text-danger">${messages.manufacturer_name}</span>
					<input name="beverage_manufacturer" type="text" class="form-control" <% if(beverage != null){ %>
						   value="<%= beverage.getManufacturer()%>">
					<% }%>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Quantity</span>
					<span class="text-danger">${messages.quantity_value}</span>
					<span class="text-danger">${messages.quantity}</span>
					<input name="beverage_quantity" type="number" class="form-control" <% if(beverage != null){ %>
						   value="<%= beverage.getQuantity()%>">
					<% }%>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Price</span>
					<span class="text-danger">${messages.price_value}</span>
					<span class="text-danger">${messages.price}</span>
					<input name="beverage_price" type="text" class="form-control" <% if(beverage != null){ %>
						   value="<%= beverage.getPrice()%>">
					<% }%>
				</div>
			</div>
			<% if(beverage != null){ %>
			<div class="form-group">
				<div class="input-group">
					<select class="custom-select" name="incentive">
						<option value="-1">Open this select menu</option>
						<%
							List<Incentive> incentives = (List<Incentive>) request.getSession().getAttribute("incentives");
							if(incentives != null){for(Incentive incentive: incentives) {
						%>
						<option value="<%= incentive.getId()%>" <%
							if(beverage != null && beverage.getIncentive().isPresent() && beverage.getIncentive().get().getId() == incentive.getId()){%>
								selected
								<%}%>><%= incentive.getName().toUpperCase()%></option>
						<% }}%>
					</select>
				</div>
			</div>
			<% }else{ %>
				<input type="hidden" name="incentive" value="-1">
			<% }%>

			<a href="/frontend/beverages" class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-success">Save</button>
		</form>					
		
	</div>
</body>
</html>