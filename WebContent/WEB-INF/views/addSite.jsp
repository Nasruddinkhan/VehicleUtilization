<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add new site</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link	href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">  
<style>
.super{
color:red;
border-radius: 4px;
width: 152px;
    height: 24px;
 
}
#div{
border-radius: 4px;
width: 283px;
    height: 24px;

}
form input{
height: 25px;
width: 150px;
border-radius: 4px;
width: 283px;
 height: 24px;
border:none;
border:1px solid #1b85ae;
}
form #btnrest{
height: 35px;
width: 90px;
}
#status{
height: 15px;
width: 20px;

}
</style>  
</head>
<body>
	<h2 style="color:#1b85ae; padding-left: 20px;">	
	 <c:choose>
	<c:when test="${sites.siteId > 0}"> Update Site</c:when>
	<c:otherwise>
	Add Site 
	</c:otherwise>
	</c:choose> 
	
	</h2><span style="color: red;text-align: center;display:inline-block;margin-left:25%;">${message}</span>
	<form:form method="POST"  enctype="multipart/form-data" id="userForm" onsubmit="return save();"
	action="${pageContext.request.contextPath}/site/saveSite.do"
	 commandName="sites">
		<input type="hidden" value="${sessionScope.User.loginId}" name="loginId" />
		<div class = "table-responsive col-lg-7">
		<table class ="table table-bordered table-striped table-highlight" >	
			<tr>
				<td>Site Name<sup class="super">*</sup></td>
				<td><form:input path="siteId" type="hidden" name="siteId"  />
				<form:input path="siteName" name="siteName" class="required" maxlength="40"/></td></tr>
				<tr>	<td>Site Location<sup class="super">*</sup></td>
				<td><form:input path="siteLocation" name="siteLocation" class="required" maxlength="40"/></td> 
			</tr>
			<%-- <tr>
				<td>Site Location<sup class="super">*</sup></td>
				<td><form:input path="siteLocation" name="siteLocation" class="required" /></td> 
			</tr> --%>
			<tr>
				<td>Construction type<sup class="super">*</sup></td>
				<td><form:select path="construction_type" class="required" id="div">
				<form:option value="" label="--Select--"></form:option>
				<form:option value="Residential" label="Residential"></form:option>
				<form:option value="Commercial" label="Commercial"></form:option>			
				</form:select></td></tr>
				<tr>
				<td>Project Start Date:</td>
				<td><form:input path="pro_Start_Date" id="datepicker1" class="required" /></td>
				<tr> <td> End Date:</td><td><form:input path="pro_End_Date" id="datepicker2" class="required" />
				</td> 
			</tr>
			
		<%-- 	<tr>
				<td>Stage<sup class="super">*</sup></td>
				<td><form:select path="stage" class="required" id="div">
				<option value="">----select-----</option>
				<option value="Foundation">Foundation</option>
				<option value="Desining">Desining</option>
				<option value="Integrity">Integrity</option>
				<option value="Electronic">Electronic</option>
				<option value="Mechnical">Mechnical</option>
				<option value="Door/Window">Door/Window</option>
				</form:select>
				</td>
				<td>StarDate:
				<form:input path="start_Date" id="datepicker3" class="required" />
				 EndDate:<form:input path="end_Date" id="datepicker4" class="required" />
				</td>
				</tr> --%>
			    <tr>
				<td>Site Status<sup class="super">*</sup></td>
				<td><form:radiobutton path="status" id="status" value="ACTIVE" checked="checked"/>ACTIVE
			        <form:radiobutton path="status" id="status" value="DEACTIVE"/>DEACTIVE</td> 
			</tr>
					
			<tr>
				<td><a class="btn btn-primary"  href="${pageContext.request.contextPath}/site/sites.do">Back</a></td>
				<td colspan="2"><input type="reset" class="btn btn-primary" id="btnrest" value="Reset" />
				<input type="submit" style="margin-left: 36px;" id="btnrest" class="button_style btn_validate btn btn-primary" value="Submit" /></td>
			</tr>
		</table>
		</div>

	</form:form>
	<script type="text/javascript">
	$( function() {
	    $( "#datepicker1" ).datepicker();
	    $( "#datepicker2" ).datepicker();
	    $( "#datepicker3" ).datepicker();
	    $( "#datepicker4" ).datepicker();
	    
	  } );
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