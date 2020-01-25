<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>
<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<%@ page import="de.uniba.dsg.dsam.model.CustomerOrder" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thank you</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

</head>
<body>
<div class="container" align="center">
    <form action="confirmOrder.jsp" method="post">
        <h3 style="color: red"><b>Thank you for your order, Here's the confirmation!</b></h3>
        <table border="2" class="table table-striped table-responsive">

            <tr>
                <th>Beverage Manufacturer</th>
                <th>Beverage Name</th>
                <th>Beverage Price</th>
                <th>Incentive</th>
                <th>Order Quantity</th>
            </tr>


            <tbody>

            <%
                List<CustomerOrder> selectedBeverages = (List<CustomerOrder>)request.getAttribute("selectedItems");
                for (CustomerOrder item  : selectedBeverages) {

            %>
            <tr>
                <td><div class="col-xs-1"><%= item.getOrderItems().getManufacturer() %></div></td>
                <td><div class="col-xs-1"><%= item.getOrderItems().getName()%></div></td>
                <td><div class="col-xs-1"><%= String.format("%.2f",item.getOrderItems().getPrice())%></div></td>
                <td><div class="col-xs-1"><%= item.getOrderItems().getIncentive().isPresent() ? "Name: "+item.getOrderItems().getIncentive().get().getName() :" No incentive"%></div></td>
                <td><div class="col-xs-1"><%= item.getOrderAmount()%></div></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        
        <p><a href="/frontend/" class="btn btn-primary">Home</a></p>

    </form>

</div>

</body>
</html>
