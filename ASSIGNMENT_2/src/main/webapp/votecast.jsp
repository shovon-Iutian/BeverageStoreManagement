<html>
<head>
    <title>Votingwindow</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">


</head>
<body>
  

       <marquee> <h1 style="color: red">Voting Booth. Select your candidate and submit your vote carefully</h1></marquee>

    <div style="width:900px">
  
        <form id="votting_form" action = "" method="post">
        <table id="table_id" border="3">

            <tr border="3" >
                <th style="color:green">Select your candidate here</th>
                <th style="color:green">Candidate Name and info</th>
                <th style="color:green">Faculty of the candidate</th>

            </tr>


            <tbody>
                
            <tr style="height: 60px">

                <td>
                    <div class="radio">
                        <input type="radio"  style="width:30px; height:20px; left:30px;bottom:-15px;margin-left: 90px"  name="" value="" required >
                    </div>
                </td>
                <td>
                       NAME

                </td>


                <td>

                      Faculty

                </td>
            </tr>

           
        </table><br>
            <div  >
                <h3>Voter token</h3>
                <input type="text"  id="token" style="border-radius:50px;"  border:7; required name="token" >
            </div><br>
       
            <p>
               <button style="border-radius:50px"  > <input type="submit" value="submit my vote now!!!" style="height: 60px; width: 300px;background-color: wheat;border-radius:70px" ></input></button>
            </p>

       
        </form>



        <p>&nbsp;</p>
    </div>


</body>
</html>
