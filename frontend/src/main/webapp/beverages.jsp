<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<%@ page import="de.uniba.dsg.dsam.model.TrialPackage" %>
<%@ page import="java.util.List" %>
<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Beverage Store Demo</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Beverage Store</h1>
		<div class="table-responsive">
			<table class="table">
				<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">ID</th>
					<th scope="col">NAME</th>
					<th scope="col">MANUFACTURER</th>
					<th scope="col">QUANTITY</th>
					<th scope="col">PRICE</th>
					<th scope="col">INCENTIVE_ID</th>
					<th scope="col">INCENTIVE_NAME</th>
					<th scope="col">INCENTIVE_TYPE</th>
				</tr>
				</thead>
				<tbody>
				<%
					List<Beverage> beverages = (List<Beverage>) request.getAttribute("beverages");
					int i=1;
					for(Beverage beverage : beverages) {
				%>
				<tr><td><%= i++%></td>
					<td><%= beverage.getId()%></td>
					<td><%= beverage.getName()%></td>
					<td><%= beverage.getManufacturer()%></td>
					<td><%= beverage.getQuantity()%></td>
					<td><%= beverage.getPrice()%></td>
					<% if(beverage.getIncentive().isPresent()){
						Incentive incentive = beverage.getIncentive().get();
					%>
					<td><%= incentive.getId()%></td>
					<td><%= incentive.getName()%></td>
					<td><% if(incentive instanceof TrialPackage){
					%> Trial package <%
					}
					else {%>
						Promotional gift
						<%}}%></td>
					<% } %>
					</tr>
				</tbody>
			</table>
		</div>
		<p><a href="/frontend/beverages/beverage_form" class="btn btn-primary">Create new beverage</a></p>
	</div>
</body>
</html>
