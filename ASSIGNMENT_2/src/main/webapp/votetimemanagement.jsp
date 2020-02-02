<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.appengine.java8.DTO.VoteTime" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vote Time Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">
</head>
<body>
<h1>Vote Time Management</h1>
<%

    List<VoteTime> voteTime = null;
    try {
        voteTime = (List<VoteTime>) request.getSession().getAttribute("voteTime");
    }catch (Exception e){
    }
%>
<form action="/votingtime" id="votetime" method="POST" class="formvalidation">
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
                if(votetime.getStartDate()!=null){
                    startdate= simpleDateFormat.format(votetime.getStartDate());
                }
            %>
            <td>
                <div class="form-group">
                    <div class="col-xs-12 input-group date form_datetime" data-date="1979-09-16T05:25:07Z"
                         data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input1">
                        <input type="text" onkeydown="return false" required class="form_datetime form-control" <%=voteTime!=null ?"readonly":""%>
                               id="startdate" aria-describedby="startdateHelp" name="startdate" value=<%=voteTime!=null ?"'"+startdate+"'":""%>>

                    </div>
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
                <div class="form-group">
                    <div class="col-xs-12 input-group date form_datetime" data-date="1979-09-16T05:25:07Z"
                         data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input2">
                        <input type="text" onkeydown="return false" required class="form_datetime form-control" <%=voteTime!=null ?"readonly":""%>
                               id="enddate" aria-describedby="enddateHelp" name="enddate" value=<%=voteTime!=null ?"'"+enddate+"'":""%>>

                    </div>
                </div>
            </td>
            <td class="editCan">
                <button type="button" <%=voteTime!=null ? "hidden" : ""%> class="edit btn btn-primary">Edit</button>
            </td>
            
        </tr>
        <% }
        }%>
        </tbody>
    </table>
    <button type="button" align="center" class="btn btn-danger cancel <%=voteTime!=null ?"hidden":""%>">Cancel</button>
    <button type="submit" align="center" class="btn btn-primary save <%=voteTime!=null ?"hidden":""%>">Update</button>
</form>

</body>
</html>
<script type="application/javascript">
    $(".edit").click(function () {
        $("input").removeAttr("readonly");
        $(".save").removeClass("hidden");
        $(".cancel").removeClass("hidden");
    });

    $(".cancel").click(function () {
        $("input").attr("readonly","readonly");
        $(".save").addClass("hidden");
        $(".cancel").addClass("hidden");
    });
    var setdate = new Date();
    setdate.setDate(setdate.getDate()-1);
    $('.form_datetime').datetimepicker({
        format : "YYYY-MM-DD HH:mm:ss",
        minDate: setdate
    });
    $("#timeform").submit(function(e) {
        var form = $(this);
        var url = form.attr('action');
        var sdate= startdate.value();
        var edate= enddate.value();
        console.log(sdate);
        console.log(edate);
        e.preventDefault();
    });
</script>
