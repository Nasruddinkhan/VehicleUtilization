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
<script
	src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
<script>
	$(document).ready(function() {

	});
</script>

</head>


<body>
	<h2>Update Record</h2>
	<form:form method="POST" id="form"
		action="${pageContext.request.contextPath}/upload/updateFileRecord.do"
		commandName="fileData">
		<form:hidden path="id" />
		<form:hidden path="fileHeader.fileId" />
		<div class="table-responsive col-lg-6">
			<table class="table table-bordered table-striped table-highlight">
				<tr>
					<td>Employee Id</td>
					<td><form:input path="empId" class="required" /></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td><form:input path="mobileNo" class="required" /></td>
				</tr>
				<tr>
					<td>Company Code</td>
					<td><form:input path="companyCode" class="required" /></td>
				</tr>
				<tr>
					<td>Salary</td>
					<td><form:input path="salary" class="required" /></td>
				</tr>
				<tr>
					<td>Bank Account</td>
					<td><form:input path="bankAccount" class="required" /></td>
				</tr>
				<tr>
					<td>EmpName & address</td>
					<td><form:input path="nameAddress" class="required" /></td>
				</tr>

				<tr>
					<td>Month</td>
					<td><form:select path="month" >
							<option value="">--SELECT--</option>
							<form:options items="${months}" />
						</form:select></td>

				</tr>
				<tr>
					<td>Salary Details 1</td>
					<td><form:input path="salaryDetail1"  /></td>
				</tr>
				<tr>
					<td>Salary Details 1</td>
					<td><form:input path="salaryDetail2"  /></td>
				</tr>
				<tr>
					<td>IFSC Code</td>
					<td><form:input path="ifscCode"  /></td>
				</tr>
				<tr><td>Currency</td>
					<td><form:select path="currency" >
							<option value="">--SELECT--</option>
							<form:options items="${currencies}" />
						</form:select></td>
				</tr>
				<tr>
					<td>Bonus Amount</td>
					<td><form:input path="bonusAmount"  /></td>
				</tr>
				<tr>
					<td>Deduction Amount</td>
					<td><form:input path="deductionAmount"  /></td>
				</tr>



				<tr>
					<td><a class="btn btn-primary"
						href="javascript:history.go(-1)">Back</a></td>
					<td colspan="2"> <input type="submit" style="margin-left: 36px;"
						class="button_style btn_validate btn btn-primary" value="Submit" /></td>
				</tr>
			</table>
		</div>

	</form:form>

</body>
</html>