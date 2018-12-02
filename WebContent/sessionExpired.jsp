<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<body style="background-color: #efe2cc">
 <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Session Expired</h3>
                    </div>
                      <div class="panel-body">
                            <div >
                                 <strong>Session Expired!! </strong><a href="${pageContext.request.contextPath}/login.do">Login Again</a>.
                            </div>
                      </div>
                </div>
            </div>
        </div>
    </div>
<%-- 
<h3>Logout Successfully! </h3>
<table>
	<tr>
		<td><a href="${pageContext.request.contextPath}/login.do">Login Again</a></td>
	</tr>
</table> --%>
</body>
</html>