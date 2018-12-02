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
		action="${pageContext.request.contextPath}/vendor/vendors" enctype="multipart/form-data"
		commandName="vendors">
		<div class="tablll">
       <table id="customers">
       <tr><th colspan="2"><h1>Vendors list</h1></th></tr>
  
  </table></div>
  </form:form>
  
  <h2>View Vendor</h2>

	<form:form commandName="vendors">
		<div class="table-responsive col-lg-6">
			<table class="table table-bordered table-striped table-highlight">
				
				<tr>
					<th>Vendor ID</th>
					<td>${vendors.vendorId}</td>
				</tr>
				
				<tr>
					<th>Vendor Name</th>
					<td>${vendors.vendor_Name}</td>
				</tr>
				
				<tr>
					<th>Vendor Contact_No</th>
					<td>${vendors.vendor_Contact_no}</td>
				</tr>
				
					<tr>
					<th>Vendor Address</th>
					<td>${vendors.address}</td>
				</tr>
				<tr>
					<th>Vendor Location</th>
					<td>${vendors.vlocation}</td>
				</tr>
					<tr>
					<th>Vendor Status</th>
					<td>${vendors.status}</td>
				
				
				</tr>
				</table>
   </div>
</form:form>
</body>
</html>