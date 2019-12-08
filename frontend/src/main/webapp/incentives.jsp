<%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>
<%@ page import="de.uniba.dsg.dsam.model.TrialPackage" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Incentives</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Incentives</h1>

		<p><a href="/frontend/incentives/incentive_form" class="btn btn-primary">Create new incentive</a></p>
		<div class="table-responsive">
			<table class="table">
				<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">ID</th>
					<th scope="col">NAME</th>
					<th scope="col">TYPE</th>
					<th scope="col">ACTION</th>
				</tr>
				</thead>
				<tbody>
				<%
					List<Incentive> incentives = (List<Incentive>) request.getSession().getAttribute("incentives");
					int i=1;
					for(Incentive incentive: incentives) {
				%>
				<tr><td><%= i++%></td>
				<td><%= incentive.getId()%></td>
				<td><%= incentive.getName()%></td>
				<td><% if(incentive instanceof TrialPackage){
				%> Trial package <%
				}
				else {%>
					Promotional gift
					<%}%></td>
					<td>
						<p><a href="/frontend/incentives/incentive_form?id=<%= incentive.getId()%>"
							  class="btn btn-primary">Update</a>
							<a href="/frontend/incentives" class="btn btn-primary"
							   name="incentive_id" value="<%= incentive.getId()%>">Delete</a>
						</p>
					</td>
				</tr>
				<%}%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
