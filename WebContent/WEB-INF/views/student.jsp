<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<style>
#data {
	width: 100% !Important;
}
</style>
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
</head>
<body>
	<h3>Search Student</h3>
	<form:form method="POST"
		action="${pageContext.request.contextPath}/student/Searchstudents.do"
		commandName="studentSearch">

		<div class="table-responsive col-lg-12">
			<table class="table table-bordered table-striped table-highlight">
				<tr>
					<td>From Joining Date:<form:input path="fromDate"
							class="required" id="fromDate" /></td>
					<td>To Joining Date: <form:input path="toDate"
							class="required" id="toDate" /></td>
					<td><input type="submit" style="margin-left: 36px;"
						class="formSubmit button_style btn btn-primary" value="Search" /></td>
				</tr>
			</table>
		</div>
	</form:form>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 style="display: inline-block;">List of Student</h3>
					<span
						style="color: green; text-align: center; display: inline-block; margin-left: 25%;">${message}</span>

				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table id="myDatatable"
							class="display table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th>Student ID</th>
									<th>Student Name</th>
									<th>Student Class</th>
									<th>Student Age</th>
									<th>Joining Date</th>
									<th>Subject Name</th>
									<th>Marks</th>
									<th>Results</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${student}" var="user">
									<tr>
										<td><c:out value="${user[0]}" /></td>
										<td><c:out value="${user[1]}" /></td>
										<td><c:out value="${user[2]}" /></td>
										<td><c:out value="${user[3]}" /></td>
										<td><c:out value="${user[4]}" /></td>
										<td><c:out value="${user[5]}" /></td>
										<td><c:out value="${user[6]}" /></td>
										<td><c:out value="${user[7]}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jquery-1.12.4.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/buttons.flash.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jszip.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/pdfmake.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/vfs_fonts.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/buttons.html5.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/buttons.print.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>

	<script>
		$(document).ready(function() {
			var oTable = $('#myDatatable').DataTable({
				dom : 'Blfrtip',
				buttons : [ {
					extend : 'excel',
					footer : true,
					title : 'Student_List',
					filename : 'Student_List',
					exportOptions : {
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7 ]
					}
				} ]
			});
			$("#fromDate").datepicker({
				dateFormat : 'yy-mm-dd'
			});
			$("#toDate").datepicker({
				dateFormat : 'yy-mm-dd'
			});
		});
	</script>
</body>