<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css"
	rel="stylesheet">
</head>
<body>
	<c:if test="${!empty fileData}">
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>Details about the file Name :
							${fileData[0].fileHeader.fileName}</h3>
						<a class="btn btn-primary" href="javascript:history.go(-1)">Back</a>
						<span
							style="color: green; text-align: center; display: inline-block; margin-left: 25%;">${param['message']}</span>
					</div>

					<div class="panel-body">
						<div class="table-responsive" style="margin-bottom: 20%;">
							<table id="myDatatable"
								class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th>Employee Id</th>
										<th>Mobile No.</th>
										<th>Company Code</th>
										<th>Bank Account</th>
										<th>Name & Address</th>
										<th>Month</th>
										<th>Salary Details 1</th>
										<th>Salary Details 2</th>
										<th>IFSC Code</th>
										<th>Currency</th>
										<th>Bonus Amount</th>
										<th>Deduction Amount</th>
										<c:if test="${fileData[0].fileHeader.status=='Rejected'}">
											<th>Action</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${fileData}" var="file">
										<tr>
											<td><c:out value="${file.empId}" /></td>
											<td><c:out value="${file.mobileNo}" /></td>
											<td><c:out value="${file.companyCode}" /></td>
											<td><c:out value="${file.bankAccount}" /></td>
											<td><c:out value="${file.nameAddress}" /></td>
											<td><c:out value="${file.month}" /></td>
											<td><c:out value="${file.salaryDetail1}" /></td>
											<td><c:out value="${file.salaryDetail2}" /></td>
											<td><c:out value="${file.ifscCode}" /></td>
											<td><c:out value="${file.currency}" /></td>
											<td><c:out value="${file.bonusAmount}" /></td>
											<td><c:out value="${file.deductionAmount}" /></td>
											<c:if test="${fileData[0].fileHeader.status=='Rejected'}">
												<td><a class="btn btn-primary"
													href="${pageContext.request.contextPath}/upload/fileDataUpdate/${file.id}.do">Modify</a></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
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

	<script>
		$(document).ready(function() {
			var oTable = $('#myDatatable').DataTable({
				dom : 'Blfrtip',
				buttons : [ {
					extend : 'pdf',
					footer : true,
					orientation : 'landscape',
					title : 'File_Data',
					filename : 'File_Data',
					exportOptions : {
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ]
					}
				}, {
					extend : 'excel',
					footer : true,
					title : 'File_Data',
					filename : 'File_Data',
					exportOptions : {
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ]
					}
				} ]
			});

		});
	</script>
</body>
</html>