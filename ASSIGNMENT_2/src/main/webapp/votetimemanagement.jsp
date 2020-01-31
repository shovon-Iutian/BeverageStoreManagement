<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.appengine.java8.DTO.VoteTime" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vote Time Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<%

    List<VoteTime> voteTime = null;
    try {
        voteTime = (List<VoteTime>) request.getSession().getAttribute("voteTime");
    }catch (Exception e){
    }
%>

<form class="formvalidation">
    <table id="tableid" class="table table-responsive" border="1">
        <tr>
            <th>Start Date</th>
            <th>End Date</th>
            <th colspan="2">Actions</th>
        </tr>
        <tbody>
        <%
            if (voteTime != null) {
                for (VoteTime votetime : voteTime) {
        %>
        <tr class="keydata" data-id="<%=votetime.getKey()!=null?votetime.getKey().getId():""%>">
            <%
                SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String startdate="";
                if(votetime.getEndDate()!=null){
                    startdate= simpleDateFormat.format(votetime.getStartDate());
                }
            %>
            <td>
                <div class="col-xs">
                    <input type="text" readonly class="row-values form-control plaintext name"
                           name="name"
                           value="<%= startdate%>">
                </div>
            </td>
            <%
                SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String enddate="";
                if(votetime.getEndDate()!=null){
                    enddate= simpleDateFormat.format(votetime.getEndDate());
                }
            %>
            <td>
                <div class="col-xs">
                    <input type="text" readonly class="row-values form-control plaintext name"
                           name="name"
                           value="<%= enddate%>">
                </div>
            </td>
            <td class="editCan">
                <div class="col-xs">
                    <button type="button" class="glyphicon glyphicon-pencil btn btn-primary editcandidate">
                    </button>
                </div>
            </td>
            
        </tr>
        <% }
        }%>
        </tbody>
    </table>
</form>
</body>
</html>
