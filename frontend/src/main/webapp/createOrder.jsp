<%@ page import="de.uniba.dsg.dsam.model.Beverage" %>
<%@ page import="java.util.List" %>
<%@ page import="de.uniba.dsg.dsam.model.Incentive" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place Order</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
</head>
<body>

<div class="container" align="center">
    <form action="/frontend/createorder" method="post" onsubmit="return validate(this);">
        <h3>Beverages For Order</h3>

        <table border="1" class="table table-striped table-responsive">
            <tr>
                <th>Beverage Manufacturer</th>
                <th>Beverage Name</th>
                <th>Beverage Price</th>
                <th>Availabe Quantity</th>
                <th>Incentive</th>

                <th>Selection</th>
                <th>Order Quantity</th>
            </tr>
            <tbody>

            <%
                List<Beverage> beverages = (List<Beverage>) request.getAttribute("availableBeverageList");

                for (Beverage beverage : beverages) {
            %>

            <tr>

                <td>
                    <div class="col-xs"><%= beverage.getManufacturer()%>
                    </div>
                </td>
                <td>
                    <div class="col-xs"><%= beverage.getName()%>
                    </div>
                </td>
                <td>
                    <div class="col-xs"><%= beverage.getPrice()%>
                    </div>
                </td>
                <td>
                    <div class="col-xs"><%= beverage.getAvailableQuantity()%>
                    </div>
                </td>
                <td>
                    <div class="col-xs"><%= beverage.getIncentive().isPresent() ? "Name: "
                            + beverage.getIncentive().get().getName() : "No incentive"%>
                    </div>
                </td>
                <td>
                    <div class="col-xs"><input type="checkbox" name="selectedBeverage" value="<%=beverage.getId()%>">
                    </div>
                </td>
                <td>
                    <div class="col-xs"><input type="number" name="<%=beverage.getId()%>_quantity" min="0"
                                               max="<%=beverage.getQuantity()%>" value="0"></div>
                </td>

            </tr>

            <% } %>
            <tr></tr>

            </tbody>


        </table>

        <div class="col-1" align="center">

            <input type="submit" value="submit" class="btn btn-success" id="submit">
        </div>

    </form>
    <p><a href="/frontend/" class="btn btn-primary">Home</a></p>
</div>
<script>
    function validate(form) {
        var checkedCheckboxes = document.querySelectorAll("[name='selectedBeverage']:checked");
        if (checkedCheckboxes.length == 0) {

            console.log("No checkbox is checked...");
            alert("Nothing selected. Please make your selection!");
            return false;
        } else {
            var i;
            var str = "";
            var bool = false;
            for (i = 0; i < checkedCheckboxes.length; i++) {
                str = (checkedCheckboxes[i].value) + "_quantity";
                var qty = document.getElementsByName(str);
                if (qty[0].value > 0) {
                    return true;
                }

            }
            alert("Please choose a valid count of required beverages!")
            return false;
        }

        return true;
    }
</script>
</body>
</html>

