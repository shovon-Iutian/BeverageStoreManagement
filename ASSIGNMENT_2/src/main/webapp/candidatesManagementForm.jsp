<%@ page import="com.example.appengine.java8.DTO.Candidates" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 1/26/2020
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidates Management</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <p>
        <button href="/candidates/candidates_form" class="add-new btn btn-primary">Add Candidate</button>
    </p>

    <p> </p>


    <%
        List<Candidates> candidatesList =null;
        try {
            candidatesList = (List<Candidates>) request.getSession().getAttribute("candidates");
        }catch (Exception e){
        }
    %>
    <form class="formvalidation">
        <table id="tableid" class="table table-responsive" border="1">

            <tr>
                <th>First_Name</th>
                <th>Sur_Name</th>
                <th>Faculty</th>
                <th colspan="2">Actions</th>
            </tr>


            <tbody>
            <%
                if (candidatesList != null) {
                    for (Candidates candidate : candidatesList) {
            %>
            <tr class="keydata" data-id="<%=candidate.getKey()!=null?candidate.getKey().getId():""%>">

                <td>
                    <div class="col-xs">
                        <input type="text" readonly class="row-values form-control plaintext name"
                               name="name"
                               value="<%= candidate.getFirstName()!=null?candidate.getFirstName():""%>">
                    </div>
                </td>

                <td>
                    <div class="col-xs">
                        <input type="text" readonly class="row-values form-control plaintext surname"
                               name="surname"
                               value="<%= candidate.getSurName()!=null?candidate.getSurName():""%>">
                    </div>
                </td>


                <td>
                    <div class="col-xs">
                        <input type="text" readonly class="row-values form-control plaintext faculty"
                               name="faculty"
                               value="<%= candidate.getFaculty()!=null?candidate.getFaculty():""%>">
                    </div>
                </td>
                <td class="updateCan" style="display: none">
                    <div class="col-xs">
                        <button type="button" class="btn btn-primary updatecandidate submit">
                            Update
                        </button>
                    </div>
                </td>
                <td class="updateCan" style="display: none">
                    <div class="col-xs">
                        <button type="button" id="canceledit" class="btn btn-primary cancel">
                            Cancel
                        </button>
                    </div>
                </td>
                <td class="editCan">
                    <div class="col-xs">
                        <button type="button" class="glyphicon glyphicon-pencil btn btn-primary editcandidate">
                        </button>
                    </div>
                </td>
                <td class="deletecan">
                    <div class="col-xs">
                        <button type="button" class="glyphicon glyphicon-remove btn btn-danger deletecandidate">
                        </button>
                    </div>
                </td>
            </tr>
            <% }
            }%>
            <tr class="newitem" style="display: none">
                <td>
                    <input type="text" class="row-values form-control plaintext name hidden" name="name">
                </td>
                <td>
                    <input type="text" class="row-values form-control plaintext surname hidden"
                           name="surname">
                </td>
                <td>
                    <input type="text" class="row-values form-control plaintext faculty hidden"
                           name="faculty">
                </td>
                <td>
                    <button type="button" class="button btn btn-primary submit saveCandidate">Create</button>
                </td>

                <td>
                    <button type="button" class="button btn btn-primary newitemcancel cancel">Cancel</button>
                </td>
            </tr>


            </tbody>
        </table>
    </form>
    <div class="col-1" align="center">
        <a href="/" class="btn btn-danger">Home</a>
    </div>
</div>
</body>
</html>
