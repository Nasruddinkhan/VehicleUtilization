<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="style.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
    <form:form acceptCharset="UTF-8" method="POST" id="userForm" onsubmit="return save();"
		action="${pageContext.request.contextPath}/site/sites" enctype="multipart/form-data"
		commandName="sites">
		<div class="tablll">
       <table id="customers">
       <tr><th colspan="2"><h1>Site list</h1></th></tr>
  
  </table></div>
  </form:form>
  
  <h2>View Sites</h2>

	<form:form commandName="sites">
		<div class="table-responsive col-lg-6">
			<table class="table table-bordered table-striped table-highlight">
				
				<tr>
					<th>Site ID</th>
					<td>${ users.siteId}</td>
				</tr>
				
				<tr>
					<th>Site Name</th>
					<td>${ sites.siteName}</td>
				</tr>
				
				<tr>
					<th>Site Location</th>
					<td>${ users.userLevel}</td>
				</tr>
				<tr>
					<th>Site Manager</th>
					<td>${ users.password}</td>
				</tr>
				<tr>
					<th>Site Supervisor</th>
					<td>${ site.password}</td>
				</tr>
				</table>
</div>
</form:form>
</body>
</html>