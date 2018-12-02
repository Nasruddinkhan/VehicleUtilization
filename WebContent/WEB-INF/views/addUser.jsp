
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.super{
color:red;
}
form input{
height: 25px;
width: 300px;
border-radius: 5px;
border:none;
border:1px solid #1b85ae;
}
form #selecter{
height: 25px;
width: 300px;
border-radius: 5px;
border:1px solid #1b85ae;
}

form #txtarea{
width: 300px;
border-radius: 5px;
border:none;
border:1px solid #1b85ae;
}
form #btnrest,#subtn{
height: 35px;
width: 90px;
}
.gender{
height: 15px;
width:20px;
}
</style>

<link	href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">    
<script>
$(document).ready(function(){
	
	$("#loginId").on("blur",function(){
		if( $("#loginId").val() != 0 ){
			
			checkLoginId();
	}
		else{
			 $('#result').text("");			 
		}
	});
	/* $("#submit").on("click",function(){
		alert("hi");
		$("#userForm").submit();	
	}); */	
});


function checkLoginId(){
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
	
function save(){
	return ""==$("#result").text();	
	
}
</script>

</head>


<body>
	<h2 style="color:#1b85ae;padding-left:20px;">	
	<c:choose>
	<c:when test="${users.id > 0}"> Update User</c:when>
	<c:otherwise>
	Add User</c:otherwise>
	</c:choose>
	</h2>
	<form:form acceptCharset="UTF-8" method="POST" id="userForm" onsubmit="return save();"
		action="${pageContext.request.contextPath}/user/saveUser.do" enctype="multipart/form-data"
		commandName="users">
		<form:hidden path="id" />
		<div class = "table-responsive col-lg-6">
		<table class ="table table-bordered table-striped table-highlight" >
			<tr>
				<td>User Name<sup class="super">*</sup></td>
				<td><form:input path="userName" class="required" maxlength="30"/></td> 
			</tr>
			<tr>
				<td>Login ID<sup class="super">*</sup></td>
				<td><form:input path="loginId"  class="required"  maxlength="30"/></td>
				     <span style="color:red;" id="result"></span>
			</tr>
			<tr>
				<td>Role<sup class="super">*</sup></td>
				<td><form:select path="role"  class="required" id="selecter" >
						<option value="">--SELECT--</option>
						<form:options items="${roles}"  />
					</form:select>
				</td>
				   
			</tr>
			
			<tr>
				<td>Password<sup class="super">*</sup></td>
				<td><form:password path="password"  class="required" type="password" maxlength="15"/></td>
			</tr>
			
			<tr>
				<td>Confirm password<sup class="super">*</sup></td>
				<td><input type="password" name="confirmPassword" class="required" type="password" maxlength="15"/></td>
				   
			</tr>
			
			<%--    <tr>
			        <td>Customer</td>
			        <td><form:select path="customer.customerId">
			         <option value="0">--SELECT--</option>
			        <form:options items="${customerList}"  itemValue="customerId" itemLabel="customerName"/>
			        </form:select>
			    </tr> --%>
			<tr>
				<td>Site</td>
				<td><form:select path="site.siteId" class="required" id="selecter">
						<option value="">--SELECT--</option>
						
						<form:options  items="${siteList}" itemValue="siteId"
							itemLabel="siteName" />
					</form:select>
			</tr>
			
			
			<tr>
				<td>Gender<sup class="super">*</sup></td>
				<td>
					<form:radiobutton path="gender" class="gender" value="MALE"/>Male
					<form:radiobutton path="gender" class="gender" value="FEMALE"/>FeMale
					<!-- <span class="required"></span>  -->
				</td>
			</tr>		
			<%-- <tr>
			<td>Photo</td>
			<td>
			<input type="file" name="Img" style="width: 175px;" accept=".jpg,.png"/>
			</td></tr>
			<tr>
			<td>File</td>
			<td>
			<input type="file" name="file" style="width: 175px;"/>
			</td></tr>	
			
			<tr>
			<td>Text</td>
			<td>
			<input type="text" name="text" style="width: 175px;" value="${users.text}"/>
			</td></tr> --%>	
			<tr>
				<td>Comments</td>
				<td>
				<form:textarea rows="2" cols="21" path="comments" id="txtarea" maxlength="150"></form:textarea> </td>
			</tr>

			<tr>
				<td><a class="btn btn-primary" href="${pageContext.request.contextPath}/user/users.do">Back</a></td>
				<td colspan="2"><input type="reset" class="btn btn-primary" value="Reset" id="btnrest" />
				<input type="submit" style="margin-left: 36px;" id="subtn" class="button_style btn_validate btn btn-primary" value="Submit" /></td>
			</tr>
		</table>
		</div>

	</form:form>
	<script type="text/javascript">
	$(function() {
	    $( "#datepicker" ).datepicker({
	    	dateFormat:'yy-mm-dd'
	    });
	 });	
	</script>
		<script src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
</body>
</html>