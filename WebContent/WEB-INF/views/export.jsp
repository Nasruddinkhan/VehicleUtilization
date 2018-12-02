<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Export</title>
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
color:red;
}
form #btnrest,#subbtn{
height: 35px;
width: 90px;
}
</style>
</head>


<body>

	<h2 style="margin-left:40%;color: #35a0b1;">Export Data
	</h2><span style="color: green;text-align: center;display:inline-block;margin-left:25%;">${message}</span>
	<form:form acceptCharset="UTF-8" method="POST" id="userForm" action="${pageContext.request.contextPath}/importExport/Export.do" commandName="export">	
		<input type="hidden" value="${sessionScope.User.loginId}" name="loginId" />
		<div class = "table-responsive col-lg-8" style="
    margin-left: 13%;">
		<table class ="table table-bordered table-striped table-highlight" >
			<tr>
			
	<td><input type="submit" style="margin-left: 290px; " id="subbtn" class="button_style btn_validate btn btn-primary" value="Export" /></td>   
			</tr>
		</table>
		</div>

	</form:form>

</body>
</html>