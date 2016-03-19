<%-- 
    Document   : LoginJSP
    Created on : Nov 9, 2015, 4:00:40 PM
    Author     : abhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Bootstrap Login Form</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link href="css/styles.css" rel="stylesheet">
	</head>
	<body>
<!--login modal-->
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h1 class="text-center">Login</h1>
          <% if("POST".equalsIgnoreCase(request.getMethod())){%>
          <div class="alert alert-danger" role="alert">${error}</div>
          <%}%>
      </div>
      
      <div class="modal-body">
          <form id="loginForm" class="form col-md-12 center-block" method="POST">
            <div class="form-group">
              <input id="emailInput" type="text" name="email" class="form-control input-lg" placeholder="Email">
            </div>
            <div class="form-group">
              <input type="password" name="password" class="form-control input-lg" placeholder="Password">
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary btn-lg btn-block">Sign In</button>
              <span class="pull-right"><a href="/AcroERP/signup">Register</a></span><span><a href="#">Need help?</a></span>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div class="col-md-12">
          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
		  </div>	
      </div>
  </div>
  </div>
</div>
	<!-- script references -->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
                <script>
                    $("#loginForm").submit(function(e){
                        e.preventDefault();
                        var form = this;
                        var email = $("#emailInput").val();
                        if(isEmail(email)){
                            form.submit(); 
                        }else{
                            alert("Please enter a valid email");
                        }
                    });
                    function isEmail(email) {
                        console.log(email);
                        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                        return regex.test(email);
                  }
                </script>
	</body>
</html>