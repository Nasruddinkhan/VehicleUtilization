<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
<script>

$(document).ready(function(){
	
	/* $("#loginId").on("blur",function(){
		if( $("#loginId").val() != 0 ){
			
		fetchPurchaseDetails();
	}
		else{
			 $('#result').text("");			 
		}
	}); */
	/* $("#submit").on("click",function(){
		alert("hi");
		$("#userForm").submit();	
	}); */
	
});


function fetchPurchaseDetails(){
	var base_url = window.location.origin;	
	base_url = base_url+"<%=request.getContextPath()%>";
	var id=$("#id").val();
	 id = ""==id ?0:id; 
		
		$.ajax({
			type : "post",
			url : base_url + "/user/checkLoginId.do",
			cache : false,
			data : 'loginId=' + $("#loginId").val()+'&id='+id,
			success : function(response) {												
					$('#result').text(response);				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#result').text("Error is validation login Id");				
			}
		});

	}

</script>
<style type="text/css">
form input{
height: 25px;
width: 300px;
border-radius: 5px;
border:none;
border:1px solid #1b85ae;
}
form #btnrest,#subbtn{
height: 35px;
width: 90px;
}
</style>
</head>


<body>
	<h2 style="margin-left:2%;color: #35a0b1;">Change Password
	</h2><span style="color: red;text-align: center;display:inline-block;margin-left:25%;">${message}</span>
	<form:form method="POST" id="userForm"  autocomplete="off"
		action="${pageContext.request.contextPath}/user/changePassword.do"		commandName="user">
		<input type="hidden" value="${sessionScope.User.loginId}" name="loginId" />
		<div class = "table-responsive col-lg-6">
		<table class ="table table-bordered table-striped table-highlight" >
			
			<tr>
				<td>Login ID</td>
				<td>${ sessionScope.User.loginId}</td>
				     
			</tr>
			
			<tr>
				<td>Current Password</td>
				<td><form:password path="password"  class="required" maxlength="12"/></td>
			</tr>
			<tr>
				<td>New Password</td>
				<td><form:password path="newPassword"  class="required password" maxlength="12"/></td>
			</tr>
			<tr>
				<td>Confirm New password</td>
				<td><input type="password" name="confirmPassword" class="confirm_password" maxlength="12"/></td>
				  
				   
			</tr>
			
			<tr>
				
				<td><input type="reset" class="btn btn-primary" value="Reset" id="btnrest" /></td>
				<td><input type="submit" style="margin-left: 36px;" id="subbtn" class="button_style btn_validate btn btn-primary" value="Submit" /></td>
			</tr>
		</table>
		</div>

	</form:form>

</body>
</html>