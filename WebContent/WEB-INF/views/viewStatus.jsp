l<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.request.contextPath}/dist/js/validation.js"></script>

<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css"
	rel="stylesheet">
</head>
<body>
	<c:choose>
		<c:when test="${!empty fileListApprovNReject}">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">


						<div class="panel-body">
							<div class="table-responsive" style="margin-bottom: 10%;">

								<form method="POST" id="form">
									<input type='hidden' id='action' name='action'>

									<table id="myDatatable1"
										class="table table-bordered table-striped table-hover">
										<thead>
											<tr>
												<th>Maker ID</th>
												<th>File Name</th>
												<th>File Uploaded On</th>
												<th>Number of Transaction</th>
												<th>Status</th>
												<th>Checker ID</th>
												<th>Checker Date</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${fileListApprovNReject}" var="file">
												<tr>
													<td><c:out value="${file.createdBy}" /></td>
													<td><c:out value="${file.fileName}" /></td>
													<td><fmt:formatDate type="both" dateStyle="medium"
															timeStyle="long" value="${file.uploadDate}"
															pattern="d MMM y" /></td>
													<td align="center"><c:out value="${file.recordCount}" /></td>
													<td><c:out value="${file.status}" /></td>
													<td><c:out value="${file.checkerId}" /></td>
													<td>
													<fmt:formatDate type = "both" 
         dateStyle = "medium" timeStyle = "long" value = "${file.checkerDate}" pattern="d MMM y" /></td>
													
													<td><a class="btn btn-primary"
														href="${pageContext.request.contextPath}/upload/fileUploadData/${file.fileId}.do">View
															Details</a>&nbsp;&nbsp;
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="table-responsive" style="text-align: center;">
								No Data Found</div>
						</div>
					</div>
				</div>
			</div>

		</c:otherwise>
	</c:choose>
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
			var base_url = window.location.origin;	
			base_url = base_url+"<%=request.getContextPath()%>";
							$("#approve")
									.click(
											function() {
												$('#action').val('Approved');
												$('#form')
														.attr(
																'action',
																base_url
																		+ '/upload/approveRejectFile.do');
												$("#form").submit();
											});
							$("#reject")
									.click(
											function() {
												$('#action').val('Rejected');
												$('#form')
														.attr(
																'action',
																base_url
																		+ '/upload/approveRejectFile.do');
												$("#form").submit();
											});

							var oTable = $('#myDatatable1').DataTable({

								dom : 'Blfrtip',
								buttons : [ {
									extend : 'pdf',
									footer : true,
									title : 'Uploaded_File',
									filename : 'Uploaded_File',
									exportOptions : {
										columns : [ 0, 1, 2, 3, 4 ,5,6]
									}
								}, {
									extend : 'excel',
									footer : true,
									title : 'Uploaded_File',
									filename : 'Uploaded_File',
									exportOptions : {
										columns : [ 0, 1, 2, 3, 4,5,6 ]
									}
								} ],
								"ordering" : false

							});

						});
	</script>
</body>
</html>