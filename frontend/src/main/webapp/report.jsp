<%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>
<%@ page import="de.uniba.dsg.dsam.model.TrialPackage" %>
<%@ page import="de.uniba.dsg.dsam.model.ReportEntity" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Report</title>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h1>Report summary</h1>

	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">ORDER_ID</th>
				<th scope="col">REVENUE</th>
			</tr>
			</thead>
			<tbody>
			<%
					List<ReportEntity> summaryReport = (List<ReportEntity>) request.getSession().getAttribute("summaryReport");
					int i=1;
					for(ReportEntity reportEntity : summaryReport) {
				%>
				<tr>
					<td><%= i++%></td>
					<td><%= reportEntity.getCustomer_order_id()%></td>
					<td><%= reportEntity.getSum()%></td>
				</tr>
				<% }%>
				</tbody>
			</table>
		</div>

	<h2>Report broken down to incentive type</h2>

	<div class="table-responsive">
		<table class="table">
			<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">INCENTIVE_TYPE</th>
				<th scope="col">REVENUE</th>
			</tr>
			</thead>
			<tbody>
			<%
				List<ReportEntity> detailedReport = (List<ReportEntity>) request.getSession().getAttribute("detailedReport");
				i=1;
				for(ReportEntity reportEntity : detailedReport) {
			%>
			<tr>
				<td><%= i++%></td>
				<td><%= reportEntity.getDtype()%></td>
				<td><%= reportEntity.getSum()%></td>
			</tr>
			<% }%>
			</tbody>
		</table>
	</div>
	<p><a href="/frontend/" class="btn btn-primary">Home</a></p>
	</div>
</body>
</html>
