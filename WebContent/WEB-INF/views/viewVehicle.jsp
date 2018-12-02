<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<body>
	<form:form commandName="vehicle">
		<div class="modal-header">
          <a class="btn btn-danger close" href="${pageContext.request.contextPath}/vehicle/vehicleslist.do">&times;</a>
          <h4 class="modal-title">${ vehicle.vehiclesType.m_Name}</h4>
        </div>
			<div class = "table-responsive">
          <table class="table table-striped table-highlight">
				<tr>
					<th>Vehicle Type</th>
					<td>${ vehicle.vehiclesType.m_Name}</td>
				</tr>
				<tr>
					<th>Vendor Name</th>
					<td>${ vehicle.vendorType.vendor_Name}</td>
				</tr>
				<tr>
					<th>Vehicle Number</th>
					<td>${ vehicle.vehiclesNumber}</td>
				</tr>
				<tr>
					<th>Model Number</th>
					<td>${ vehicle.modelNumber}</td>
				</tr>
				<tr>
					<th>Issue Date</th>
					<td>${ vehicle.issueDate}</td>
				</tr>
			</table>
			</div>
	</form:form>
	<div class="modal-footer">
          <a class="btn btn-default" href="${pageContext.request.contextPath}/vehicle/vehicleslist.do">Close</a>
        </div>
</body>
