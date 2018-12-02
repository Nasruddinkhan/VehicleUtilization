<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/fevicon.gif" type="image/x-icon" />
<title>SMS</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	
	
	<!--  from User -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/db_site_ui.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo_table_jui.css">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css">
   <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script> --%>
      <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
   <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.jqueryui.min.js"></script>   <!-- MetisMenu CSS -->
 --%>   <link href="${pageContext.request.contextPath}/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
   <!-- DataTables CSS -->	
<%--    <link href="${pageContext.request.contextPath}/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
   <!-- DataTables Responsive CSS -->
   <link href="${pageContext.request.contextPath}/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet"> --%>
   <!-- Custom CSS -->
   <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">
   <!-- Custom Fonts -->
   <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
 
</head>

<body>

	<div id="wrapper">

		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="menu" />
	</div>
	

	<!-- /.row -->

	<div id = "page-wrapper"  style="margin-top:-20px;"	>
		<div class= "row" style="margin-left:-45px;margin-bottom: 10px;">
			<div class="col-lg-12" >
			<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	
	
	<!-- /#page-wrapper -->


	<!-- /#wrapper -->

	<!-- My footer -->
	<tiles:insertAttribute name="footer" />
	
	
</body>

</html>
