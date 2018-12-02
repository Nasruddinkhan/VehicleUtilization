<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
   <head>
   
      <!-- <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
       -->
      <title>SMS</title>
      <!-- Bootstrap Core CSS -->
      <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	  <script src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
      <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <!-- MetisMenu CSS -->
      <link href="${pageContext.request.contextPath}/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
      <!-- Custom CSS -->
      <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">
      <!-- Custom Fonts -->
      <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
      <!-- Custom Fonts -->
      <link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css">
      <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
      <style type="text/css">
      @media (min-width: 768px) {
  #page-wrapper {
    /* position: inherit !important;
   margin: 0 0 0 250px !important; 
    padding: 0 30px !important;
    border-left: 1px solid #e7e7e7 !important; */
  }
  
 
}
body{
background: url(./suhel/bg1.jpg);
background-repeat:no-repeat;
background-size: 1570px 720px;
height: auto;
}

 #uploadingImg{
  position:fixed; 
  z-index:50;  
  display:none;
  margin-top:100px;
  }
      </style>
      <script>
$(document).ready(function(){	
	$('.formSubmit').on('click',function(){	
		 var div = document.getElementById('uploadingImg');
		 div.style.display = 'block';
		if(''!=$('#errormsg').val() || ''==$('#loginId').val() ){
		    $('#uploadingImg').hide();
		}
		});
	}); 
</script>
   </head>
   
   <body>
   <!-- <h2 style="color:#1b85ae; margin-left:500px; margin-top:50px;">NavDurga Infra Ventures Ltd.</h2> -->
    <div class="col-md-5 col-md-offset-5 panel-default" id="uploadingImg" >
			<img alt="" src="${pageContext.request.contextPath}/img/logo2.gif">
	</div>
      <div class="container">
         <div class="row">
            <div class="col-md-4 col-md-offset-4">
               <div class="login-panel panel panel-default" style="border-color: midnightblue;">
			     <div class="imgwrapper">
			     <img src="${pageContext.request.contextPath}/suhel/volvo-construction-equipment.jpg" class="img-responsive"   style="width:358px;height:160px;">
					<%-- <img src="${pageContext.request.contextPath}/img/MasrafAlRayan1.jpg"    style="width:358px;height:80px;">--%>
 				
				 </div>
                  <div class="panel-body">
                  <fieldset>
                     <form:form action="${pageContext.request.contextPath}/login.do" commandName="login">
                        
                        <legend>Login</legend>
							
                        <div> <p style="color:red;" id="errormsg">${Error}</p></div>   
                                          
                              <div class="form-group">
                                 <form:input path="loginId" placeholder="User Name" class="required form-control " />
                              </div>
                              <div class="form-group">
                                 <form:password path="password" placeholder="Password" cssClass="required form-control"/>
                              </div>
                             <!--  <div class="checkbox">
                                 <label>
                                 <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                 </label>
                              </div> -->
                              <!-- Change this to a button or input when using this as a form -->
                              <input type="submit"  value="Submit" class="btn_validate formSubmit btn btn-lg btn-success btn-block" />
                     
                     </form:form>
                       </fieldset>
                       
                  </div>
               </div>
            </div>
         </div>
      </div>
      <script type="text/javascript">
      $(document).ready(
    		    function()
    		    {
    		        $(this).removeClass('#page-wrapper');
    		    }
    		);
      
      </script>
      <!-- jQuery -->
      <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
      <!-- Bootstrap Core JavaScript -->
      <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
      <!-- Metis Menu Plugin JavaScript -->
      <script src="${pageContext.request.contextPath}/vendor/metisMenu/metisMenu.min.js"></script>
      <!-- Custom Theme JavaScript -->
      <script src="${pageContext.request.contextPath}/dist/js/sb-admin-2.js"></script>
   </body>
</html>