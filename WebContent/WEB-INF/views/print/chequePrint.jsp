<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- 
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css"
	rel="stylesheet"> --%>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>

</head>
<body>

	<h3>Cheque Print</h3>
	<span
		style="color: green; text-align: center; display: inline-block; margin-left: 25%;">${message}</span>
	<form method="POST" id="reportForm"
		action="${pageContext.request.contextPath}/cheque/print.do">
   <div> <p style="color:red;" id="errormsg">${noCheque}</p></div> 
		<table class="table table-bordered table-striped table-highlight">
			<tr>

				<td style="width: 30%">Cheque Number:
	</td><td>
						<input type="text" name="chequeNumber" class="required" placeholder="Cheque No." />
			</td>
			
			</tr>

		</table>
		
		<input type="submit" style="margin-left: 36px;margin-top: 20px" id="submit" 
			class="button_style btn btn-primary btn_validate" value="Print" />

	</form>


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
	}	);
		
	</script>
</body>

</html>