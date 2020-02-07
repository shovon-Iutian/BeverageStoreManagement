<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidate Management</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">


</head>
<body>
<div style="width: 900px;margin-left: 300px;">
    <br><br>
    <button  style="background-color: bisque;color:black;width:200px;height:50px;margin-left: 30px"><a href="/" ></a>
        Home
    </a></button><br><br>
    <p>
        <button class="add-new" style="background-color: bisque;color:black;width:200px;height:50px;margin-left: 30px ">Add New Candidate</button>
    </p>

    <p>&nbsp;</p>

    <form class="Validation">
        <table id="tableid" class="table table-responsive" border="3">

            <tr>
                <th>Candidate Firstname</th>
                <th> Candidate Lastname</th>
                <th>Faculty name</th>
                <th colspan="2">Action</th>
            </tr>


            <tbody>
           
            <tr id="">

                <td>
                    <div >
                        <input type="text" readonly class="firstname"
                               name="firstname"
                               value="">
                    </div>
                </td>

                <td>
                    <div >
                        <input type="text" readonly class=" formValidate lastname"
                               name="lastname"
                               value="">
                    </div>
                </td>


                <td>
                    <div >
                        <input type="text" readonly class="formValidate faculty"
                               name="faculty"
                               value="">
                    </div>
                </td>
                <td class="updateCan" style="display: none">
                    <div >
                        <button type="button" class="updatecandidate submit">
                            Update
                        </button>
                    </div>
                </td>
                <td class="updateCan" style="display: none">
                    <div>
                        <button type="button" id="canceledit" class=" canceledit">
                            Cancel
                        </button>
                    </div>
                </td>
                <td class="infoedit">
                    <div ">
                        <button type="button" class=" candidateEdit"> EDIT
                        </button>
                    </div>
                </td>
                <td class="canDel">
                    <div ">
                        <button type="button" class=" candidatedel">DELETE
                        </button>
                    </div>
                </td>
            </tr>
          
            <tr class="infoadd" style="display: none">
                <td>
                    <input type="text" class="formValidate firstname hidden" name="name">
                </td>
                <td>
                    <input type="text" class="formValidate lastname hidden"
                           name="lastname">
                </td>
                <td>
                    <input type="text" class="formValidate faculty hidden"
                           name="faculty">
                </td>
                <td>
                    <button type="button" class="button submit saveCandidate">Create</button>
                </td>

                <td>
                        <button type="button" class="button infoadd cancel canceledit">Cancel</button>
                    </td>
            </tr>


            </tbody>
        </table>
    </form>
    
</div>


<script type="application/javascript">
    $(".add-new").click(function () {
        $(this).attr("disabled", "disabled");
        $(".infoadd").show();
        $(".infoadd").children("td").children().removeClass("hidden");
        $(".infoadd").children("td").children(".formValidate").attr("required", "required");
    });


    $(".canceledit").click(function () {
        location.reload();
    });
    $(".submit").click(function () {

        if (!$(".Validation")[0].checkValidity({ignore: ':readonly,:hidden'})) {
            $(".Validation")[0].reportValidity();
        }
        else {
            if ($(this).hasClass("saveCandidate")) {
                saveCandidate();
            }
            else if ($(this).hasClass("updatecandidate")) {
                var tr = $(this).closest('tr');
                updateCandidateFunc(tr);
            }
        }

    });
    saveCandidate = function () {
        var firstname = $(".infoadd").children("td").children(".firstname").val();
        var lastname = $(".infoadd").children("td").children(".lastname").val();
        var faculty = $(".infoadd").children("td").children(".faculty").val();

        $.ajax
        ({
            // manage candidate url here
            data: {
                "firstname": firstname, "lastname": lastname,
                "faculty_name": faculty_name
            },
            type: 'POST',
            success: function (data) {
                location.reload();
            }
        });

    }


    updateCandidateFunc = function (sRow) {
        try {
            var tr = sRow;
            var elem = tr.children("td").children("div");
            var id = tr.data("id");
            var firstname = elem.children(".firstname").val();
            var lastname = elem.children(".lastname").val();
            var faculty = elem.children(".faculty").val();

            $.ajax
            ({
               // manage candidate url here
                data: {
                    "id":id,"firstname": firstname, "lastname": lastname,
                    "faculty": faculty
                },
                type: 'put',
                success: function (data) {
                    if(data=="true")
                    location.reload();
                }
            });
        } catch (err) {
        }
    }


    $(".candidateEdit").click(function () {
        var update_info = $(this).closest('tr');
        var elem = update_info.children("td").children("div");
        update_info.children('.infoedit').hide();
        update_info.children('.canDel').hide();
        update_info.children('.updateCan').show();
        elem.children("input").removeAttr("readonly");
        elem.children("input").attr("required", "required");
        elem.children().removeAttr("disabled");

    });


    $(".candidatedel").click(function () {
        
        if (confirm("Are you sure you want to delete this candidate Infromation?")) {
            var strd = $(this).closest('tr');
            var id = strd.data("id");
            $.ajax
            ({
                // manage candidate with ID url here
                contentType: "application/json; charset=utf-8",
                type: 'DELETE',
                success: function (data) {
                    if(data=="true")
                    location.reload();
                }
            });
        }
    });
</script>
</body>
</html>
