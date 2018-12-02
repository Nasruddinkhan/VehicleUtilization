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
						
						<div class="row">
										
						<button type="button" class="btn btn-primary" name="back" onclick="history.back()">Back</button>
						<!-- <div class="col-2">
							<a class="btn btn-primary" href="javascript:history.back(-1)">Back</a>
						</div> -->
						</div>
						<span
							style="color: green; text-align: center; display: inline-block; margin-left: 25%;">${param['message']}</span>
					</div>

					<div class="panel-body">
						<div class="table-responsive" style="margin-bottom: 20%;">
							<table id="myDatatable"
								class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th>Batch Id</th>
										<th>Branch </th>
										<th>Account Number</th>
										<th>Account Suffix</th>
										<th>Transfer Amount</th>
										<th>Beneficiary Account</th>
										<th>Name & Address</th>
										<th>Payment Purpose Code</th>
										<th>Salary Details 1</th>
										<th>Salary Details 2</th>									
										<th>Currency</th>
										<th>Charge Code</th>
										<th>Charge Amount</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${fileData}" var="file">
										<tr>
										    <td><c:out value="${file.PYM03BID}" /></td>
											<td><c:out value="${file.PYM03AB1}" /></td>
											<td><c:out value="${file.PYM03AN1}" /></td>
											<td><c:out value="${file.PYM03AS1}" /></td>
											<td><c:out value="${file.PYM03TAMT}" /></td>
											<td><c:out value="${file.PYM03OAN1}" /></td>
											<td><c:out value="${file.PYM03BAD1}" /></td>
											<td><c:out value="${file.PYM03PYP}" /></td>
											<td><c:out value="${file.PYM03PD1}" /></td>
											<td><c:out value="${file.PYM03PD2}" /></td>										
											<td><c:out value="${file.PYM03PCCY}" /></td>											
											<td><c:out value="${file.PYM03CCODE}" /></td>	
											<td><c:out value="${file.PYM03CAMT}" /></td>										
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