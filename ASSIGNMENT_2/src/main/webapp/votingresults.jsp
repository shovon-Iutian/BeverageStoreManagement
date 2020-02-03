<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voting Result</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
</head>
<body>
<h1>Voting Result</h1>
<%
    HashMap<String,Integer> votestats=new HashMap<>();
    votestats= (HashMap<String, Integer>) request.getAttribute("votestats");
    Float votepercentage=(Float)request.getAttribute("percentagevoter");
%>
<div>
    <table id="tableid" class="table table-responsive" border="1">
        <tr>
            <th>Total Voters</th>
            <th>Vote Casted</th>
            <th>Participation (%)</th>
            <th>Not Participated (%)</th>
        </tr>
        <tbody>
        <td>
            <%--<%= votestats.get("votercount")!=null?votestats.get("votercount"):"0"%>--%>
        </td>
        <td>
           <%-- <%= votestats.get("votecasted")!=null?votestats.get("votecasted"):"0"%>--%>
        </td>
        <td>
            <%--<%= votepercentage!=null?votepercentage:"0"%>--%>
        </td>
        <td>
           <%-- <%= votepercentage!=null?(100-votepercentage):"0"%>--%>
        </td>
        </tbody>
    </table>
</div>
</body>
</html>
