

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.spn{
font-size:15px;
font-weight: bold;
color: green;
}
</style>
<body>
<div>
          <form:form commandName="vendors">
      <div class="modal-header">
          <a class="btn btn-danger close" href="${pageContext.request.contextPath}/vendor/vendors.do">&times;</a>
          <h4 class="modal-title">${vendors.vendor_Name}</h4>
         </div>
      <div class = "table-responsive">
          <table class="table table-striped table-highlight">
                 <%--  <tr>
					<th>Vendor ID</th>
					<td>${vendors.vendorId}</td>
				</tr> --%>
				
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
					<th>Vendor location</th>
					<td>${vendors.vlocation}</td>
				</tr>
					<tr>
					<th>Vendor Status</th>
					<td>${vendors.status}</td>
				</tr>
          </table>
        </div>
        
      </form:form>
      <div class="modal-footer">
          <a class="btn btn-default" href="${pageContext.request.contextPath}/vendor/vendors.do">Close</a>
       </div>
      </div>
</body> 