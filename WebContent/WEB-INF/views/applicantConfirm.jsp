<%@page import="com.project.millatinventory.common.CommonUtil"%>
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
	<h3>Confirm detail</h3>
	<div class="table-responsive col-lg-12">
		<form:form method="POST" id="form" commandName="detailInsert"
			action="${pageContext.request.contextPath}/print/confirmDetails.do">
			<div class="table-responsive">
				<table class="table table-bordered " style="width: 60%">
					<tr>
						<th width=15%>Share Holder Name</th>
						<td width=10%>${detailInsert.applicantName}</td>
					</tr>
					<tr>
						<form:hidden path="applicantNIN" />
						<th>Share Holder Number</th>
						<td>${detailInsert.applicantNIN}</td>
					</tr>
				</table>

				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Information</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive col-lg-12">
							<table class="table table-bordered table-striped ">
								<tr>
									<th width=35%>Name</th>
									<form:hidden path="applicantName" />
									<td>${detailInsert.applicantName}</td>
								</tr>
								<tr>
									<form:hidden path="applicantID" />
									<th>ID /CR No</th>
									<td>${detailInsert.applicantID}</td>
								</tr>
								<tr>
									<form:hidden path="applicantType" />
									<th>Share Holder Type</th>
									<td>${detailInsert.applicantType}</td>
								</tr>
								<tr>
									<form:hidden path="guardianID" />
									<th>Guardian ID</th>
									<td>${detailInsert.guardianID}</td>
								</tr>
								<tr>
									<form:hidden path="passportNo" />
									<th>Passport No.</th>
									<td>${detailInsert.passportNo}</td>
								</tr>
								<tr>
									<form:hidden path="dob" />
									<form:hidden path="nationality" />
									<th>DOB / Nationality</th>
									<td>${detailInsert.dob}/${detailInsert.nationality}</td>
								</tr>
								<tr>
									<form:hidden path="pastCode" />
									<th>Post Code</th>
									<td>${detailInsert.pastCode}</td>
								</tr>
								<tr>
									<form:hidden path="poBox" />
									<th>P.O.Box</th>
									<td>${detailInsert.poBox}</td>
								</tr>
								<tr>

									<form:hidden path="city" />
									<form:hidden path="country" />
									<th>City / Country</th>
									<td>${detailInsert.city}/${detailInsert.country}</td>
								</tr>
								<tr>
									<form:hidden path="telephone" />
									<th>Telephone</th>
									<td>${detailInsert.telephone}</td>

								</tr>
								<tr>
									<form:hidden path="fax" />
									<th>Fax</th>
									<td>${detailInsert.fax}</td>
								</tr>
								<tr>

									<form:hidden path="email" />
									<th>E-Mail</th>
									<td>${detailInsert.email}</td>

								</tr>
								<tr>
									<form:hidden path="nin1" />
									<form:hidden path="nin2" />
									<form:hidden path="nin3" />
									<form:hidden path="nin4" />
									<th>Merge NINs</th>
									<td>${detailInsert.nin1}&emsp;${detailInsert.nin2}&emsp;
										${detailInsert.nin3}&emsp; ${detailInsert.nin4}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Divident Payment Details</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive col-lg-12">
							<table class="table table-bordered table-striped ">
								<tr>
									<th width=15%>Account No.</th>
									<form:hidden path="accountNumber" />
									<td>${ detailInsert.accountNumber}</td>
									<th width=15%>Account Name.</th>
									<form:hidden path="accountName" />
									<td>${ detailInsert.accountName}</td>
								</tr>
								<tr>
									<th>Bank.</th>
									<form:hidden path="bank" />
									<td>${ detailInsert.bank}</td>
									<th>Branch.</th>
									<form:hidden path="branch" />
									<td>${ detailInsert.branch}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>



				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">For Foreign Account Only</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive col-lg-12">
							<table class="table table-bordered table-striped ">
								<tr>
									<th>SWIFT.</th>
									<form:hidden path="swift" />
									<td>${detailInsert.swift}</td>

									<th>IBAN.</th>
									<form:hidden path="iban" />
									<td>${detailInsert.iban}</td>
									<th>Currency</th>
									<form:hidden path="currency" />
									<td>${detailInsert.currency}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Signature</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-6 col-sm-6">
							<div class="form-group-sm col-md-12 col-sm-12">
								<form:hidden path="signatureFile" />
								<img style="width: 120px; height: 80px; margin-left: 80%;"
									alt="img"
									src="data:image/jpeg;base64,${detailInsert.signatureFile}" />
							</div>
						</div>
					</div>
				</div>

				<input type="hidden" name="confirm" value="${confirm}" />
				<div class="center">
					<a class="btn btn-primary" href="javascript:history.go(-1)">Back</a>
					<c:choose>
						<c:when test="${confirm=='true'}">
							<input id="submit1" type="submit" value="Confirm"
								class="btn btn-primary" disabled="disabled" />
						</c:when>
						<c:otherwise>
							<input id="submit" type="submit" value="Confirm"
								class="btn btn-primary" />

						</c:otherwise>
					</c:choose>
					<c:if test="${confirm=='true'}">
						<input id="print" type="submit" value="Print"
							class="btn btn-primary" />
					</c:if>
				</div>
			</div>
			
		</form:form>
	</div>
<div id="printDiv">
				<span class="applicantName">${detailInsert.applicantName}</span>
				<span class="applicantNIN">${detailInsert.applicantNIN}</span>
				<span class="applicantName1">${detailInsert.applicantName}</span>
				<span class="applicantID">${detailInsert.applicantID}</span>
				
				 <c:if test="${detailInsert.applicantType=='Company'}">
				<span class="company"><img style="width: 20px; height: 20px;" src="${pageContext.request.contextPath}/img/Check-mark-clip.jpeg" /></span>
				</c:if>
				<c:if test="${detailInsert.applicantType=='Minor'}">
				<span class="minor"><img style="width: 20px; height: 20px;" src="${pageContext.request.contextPath}/img/Check-mark-clip.jpeg" /></span>
				</c:if>
				<c:if test="${detailInsert.applicantType=='Individual'}">
				<span class="individual"><img style="width: 20px; height: 20px;" src="${pageContext.request.contextPath}/img/Check-mark-clip.jpeg" /></span>
				</c:if>		 	
								
				
				<span class="guardianID">${detailInsert.guardianID}</span>
				<span class="passportNo">${detailInsert.passportNo}</span>
				<span class="dob">${detailInsert.dob}/${detailInsert.nationality}</span>
				<span class="postCode">${detailInsert.pastCode}</span>
				<span class="poBox">${detailInsert.poBox}</span>
				<span class="city">${detailInsert.city}/${detailInsert.country}</span>
				<span class="telephone">${detailInsert.telephone}</span>
				<span class="fax">${detailInsert.fax}</span>
				<span class="email">${detailInsert.email}</span>
				<span class="nin1">${detailInsert.nin1}</span>
				<span class="nin2">${detailInsert.nin2}</span>
				<span class="nin3">${detailInsert.nin3}</span>
				<span class="nin4">${detailInsert.nin4}</span>
				<span class="accountNumber">${ detailInsert.accountNumber}</span>
				<span class="accountName">${ detailInsert.accountName}</span>
				<span class="bank">${ detailInsert.bank}</span>
				<span class="branch">${ detailInsert.branch}</span>
				<span class="swift">${detailInsert.swift}</span>
				<span class="iban">${detailInsert.iban}</span>
				<span class="currency">${detailInsert.currency}</span>
				<span class="date">
				<%=CommonUtil.getToday("dd-MMM-yyyy")%></span>
				<span class="signature"><img style="width: 120px; height: 40px; margin-left: 80%;" alt="img"
					src="data:image/jpeg;base64,${detailInsert.signatureFile}" /></span>
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

	<script>
		$(document).ready(function() {
			$('#printDiv').hide();
			var base_url = window.location.origin;	
			if(base_url==undefined){
				base_url=	window.location.protocol+'//'+window.location.hostname+':'+window.location.port;
			}
			
			
			base_url = base_url+"<%=request.getContextPath()%>";
					$('#submit1').click(
							function() {
								$('#form').attr('action',
										base_url + "/print/confirmDetails.do");
								$('#form').submit();
								//alert('Submit');
							});

					$('#print').click(function() {
						//alert();
					/*	$('#form').attr('action',
							base_url + "/print/printForm.do");
						$('#form').submit();
						*/
						$('#printDiv').show();			
						
						$('.table-responsive').hide();					
						$('h3').hide();
						window.print();
						$('#printDiv').hide();							
						$('.table-responsive').show();						
						$('h3').show();					
					});
				});
	</script>
	
	<style>
	.applicantName{
	position: absolute;
	top:240px;
	left:300px;
	}
	.applicantNIN{
	position: absolute;
	top:280px;
	left:400px;
	}
	.applicantName1{
	position: absolute;
	top:430px;
	left:250px;
	}
	
	.applicantID{
	position: absolute;
	top:462px;
	left:250px;
	}
	
	.company{
	position: absolute;
	top:490px;
	left:380px;
	}
	.minor{
	position: absolute;
	top:490px;
	left:532px;
	}
	.individual{
	position: absolute;
	top:490px;
	left:720px;
	}
	
	.guardianID{
	position: absolute;
	top:527px;
	left:250px;
	}
	
	.passportNo{
	position: absolute;
	top:560px;
	left:250px;
	}
	.dob{
	position: absolute;
	top:592px;
	left:250px;
	}
	.postCode{
	position: absolute;
	top:625px;
	left:220px;
	}
	.poBox{
	position: absolute;
	top:625px;
	left:560px;
	}
	.city{
	position: absolute;
	top:657px;
	left:250px;
	}
	.telephone{
	position: absolute;
	top:690px;
	left:250px;
	}
	.fax{
	position: absolute;
	top:722px;
	left:250px;
	}
	.email{
	position: absolute;
	top:755px;
	left:250px;
	}
	.nin1{
	position: absolute;
	top:790px;
	left:220px;
	}
	.nin2{
	position: absolute;
	top:790px;
	left:360px;
	}
	.nin3{
	position: absolute;
	top:790px;
	left:500px;
	}
	.nin4{
	position: absolute;
	top:790px;
	left:640px;
	}
	
	.accountNumber{
	position: absolute;
	top:870px;
	left:200px;
	}
	.accountName{
	position: absolute;
	top:870px;
	left:780px;
	}
	.bank{
	position: absolute;
	top:920px;
	left:150px;
	}
	.branch{
	position: absolute;
	top:920px;
	left:700px;
	}
	.swift{
	position: absolute;
	top:1020px;
	left:150px;
	}
	.iban{
	position: absolute;
	top:1020px;
	left:470px;
	}
	.currency{
	position: absolute;
	top:1020px;
	left:800px;
	}
	.date{
	position: absolute;
	top:1385px;
	left:150px;
	}
	.signature{
	position: absolute;
	top:1375px;
	left:600px;
	}
</style>
</body>
</html>