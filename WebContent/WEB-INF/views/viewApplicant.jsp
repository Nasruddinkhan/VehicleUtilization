<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
</head>
<body>
	<h3>View Detail</h3>
	
	<span
							style="color: green; text-align: center; display: inline-block; margin-left: 25%;">${message}</span>
	
	<form:form method="POST" id="form" 
		action="${pageContext.request.contextPath}/print/approveRejectApplication.do">
		<div class="table-responsive col-lg-6">
		<input type="hidden" name="action" id="action" />
		<input type="hidden" name="id" value="${detailInsert.applicantID}"/>
			<table class="table table-bordered table-striped table-highlight">
				<tr>
					<th>Share Holder Name</th>
					<th>${detailInsert.applicantName}</th>

					
					<th>Share Holder Number</th>
					<th>${detailInsert.applicantNIN}</th>
				</tr>
				<tr>
					
					<th>Name</th>
					<th>${detailInsert.applicantName}</th>


					
					<th>ID /CR No</th>
					<th>${detailInsert.applicantID}</th>

				</tr>
				<tr>
					
					<th>Share Holder Type</th>
					<th>${detailInsert.applicantType}</th>


					
					<th>Guardian ID</th>
					<th>${detailInsert.guardianID}</th>

				</tr>
				<tr>
					
					<th>Passport No.</th>
					<th>${detailInsert.passportNo}</th>


					
					<th>DOB / Nationality</th>
					<th>${detailInsert.dob}/${detailInsert.nationality}
				</tr>
				<tr>
					
					<th>Post Code</th>
					<th>${detailInsert.pastCode}</th>

					
					<th>P.O.Box</th>
					<th>${detailInsert.poBox}</th>
				</tr>
				<tr>
					
					<th>City / Country</th>
					<th>${detailInsert.city}/${detailInsert.country}</th>


					
					<th>Telephone</th>
					<th>${detailInsert.telephone}</th>

				</tr>
				<tr>
					
					<th>Fax</th>
					<th>${detailInsert.fax}</th>


					
					<th>E-Mail</th>
					<th>${detailInsert.email}</th>

				</tr>
				<tr>
					
					<th>Merge NINs</th>
					<th colspan="3">${detailInsert.nin1}&emsp;
						${detailInsert.nin2}&emsp; ${detailInsert.nin3}&emsp;
						${detailInsert.nin4}</th>

				</tr>


				<tr>
					<th>Account No.</th>
					
					<th>${ detailInsert.accountNumber}</th>
					<th>Account Name.</th>
					
					<th>${ detailInsert.accountName}</th>
				</tr>
				<tr>
					<th>Bank.</th>
					
					<th>${ detailInsert.bank}</th>
					<th>Branch.</th>
					
					<th>${ detailInsert.branch}</th>
				</tr>

				<tr>
					<th>SWIFT.</th>
					
					<th>${ detailInsert.swift}</th>
					<th>IBAN.</th>
					
					<th>${ detailInsert.iban}</th>
					</tr>
				<tr>
					<th>Currency</th>
					
					<th>${ detailInsert.currency}</th>
				
					<th>Signature</th>
					<th>
					<img
						style="width: 180px; height: 120px;" alt="img"
						src="data:image/jpeg;base64,${detailInsert.signatureFile}" /></th>
				</tr>
			</table>

			
			<div class="center">
				<a class="btn btn-primary" href="javascript:history.go(-1)">Back</a>
				
					
						<input id="reject" type="button" value="Reject"
							class="btn btn-primary"  />
					
						<input id="approve" type="button" value="Approve"
							class="btn btn-primary" />

					
				
			</div>
		</div>
	</form:form>

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
			if(base_url==undefined){
				base_url=	window.location.protocol+'//'+window.location.hostname+':'+window.location.port;
			}
			
			
			base_url = base_url+"<%=request.getContextPath()%>";
					$('#reject').click(
							function() {
								$("#action").val("Rejected");
								$('#form').submit();
								//alert('Submit');
							});

					$('#approve').click(
							function() {
								$("#action").val("Approved");
								$('#form').submit();
							});

				});
	</script>
</body>
</html>