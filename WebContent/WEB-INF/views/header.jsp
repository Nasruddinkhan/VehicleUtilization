<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Navigation -->
<style>
 #side-menu{
height:617px;
}
.navbar-default>ul>li {
	color: #9d9d9d;
}

.navbar-default>ul>li>a {
	color: #9d9d9d;
}
/* .sidebar .sidebar-nav.navbar-collapse {
    border: solid;
}
.sidebar .sidebar-nav.navbar-collapse >ul>li {
    border: solid;
} */
a:hover {
	background: #41919e !important;
	color: white !important;
}
.dropdown-menu1{
    top: 0px;
    left: 251px;
    background-color: #136572;
    position: absolute;
    z-index: 1000;
    display: none;
    float: left;
    min-width: 160px;
    padding: 5px 0;
    margin: 2px 0 0;
    font-size: 14px;
    text-align: left;
    list-style: none;
   /*  -webkit-background-clip: padding-box;
    background-clip: padding-box; */
    border: 1px solid #ccc;
    border: 1px solid rgba(0,0,0,.15);
    border-radius: 4px;
    -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
    box-shadow: 0 6px 12px rgba(0,0,0,.175);
}
.dropdown-submenu1:hover>.dropdown-menu1{
    display:block;
 
    text-decoration: none;
    }

.dropdown-submenu1>a:after{
padding: 2px 25px;
display:block;
float:right;
}

/*gradient for side bar  */
/* .gradient {
 background: rgba(41,184,229,1);
background: -moz-linear-gradient(left, rgba(41,184,229,1) 4%, rgba(83,195,231,1) 11%, rgba(179,220,237,0.87) 27%, rgba(188,224,238,0.3) 100%);
background: -webkit-gradient(left top, right top, color-stop(4%, rgba(41,184,229,1)), color-stop(11%, rgba(83,195,231,1)), color-stop(27%, rgba(179,220,237,0.87)), color-stop(100%, rgba(188,224,238,0.3)));
background: -webkit-linear-gradient(left, rgba(41,184,229,1) 4%, rgba(83,195,231,1) 11%, rgba(179,220,237,0.87) 27%, rgba(188,224,238,0.3) 100%);
background: -o-linear-gradient(left, rgba(41,184,229,1) 4%, rgba(83,195,231,1) 11%, rgba(179,220,237,0.87) 27%, rgba(188,224,238,0.3) 100%);
background: -ms-linear-gradient(left, rgba(41,184,229,1) 4%, rgba(83,195,231,1) 11%, rgba(179,220,237,0.87) 27%, rgba(188,224,238,0.3) 100%);
	background: rgba(193, 158, 103, 1);
	background: -moz-linear-gradient(left, rgba(193, 158, 103, 1) 0%,
		rgba(182, 141, 76, 0.82) 12%, rgba(243, 226, 199, 0.67) 22%,
		rgba(242, 224, 196, 0.64) 24%, rgba(233, 212, 179, 0.64) 37%);
	background: -webkit-gradient(left top, right top, color-stop(0%, rgba(193, 158, 103,
		1)), color-stop(12%, rgba(182, 141, 76, 0.82)),
		color-stop(22%, rgba(243, 226, 199, 0.67)),
		color-stop(24%, rgba(242, 224, 196, 0.64)),
		color-stop(37%, rgba(233, 212, 179, 0.64)));
	background: -webkit-linear-gradient(left, rgba(193, 158, 103, 1) 0%,
		rgba(182, 141, 76, 0.82) 12%, rgba(243, 226, 199, 0.67) 22%,
		rgba(242, 224, 196, 0.64) 24%, rgba(233, 212, 179, 0.64) 37%);
	background: -o-linear-gradient(left, rgba(193, 158, 103, 1) 0%,
		rgba(182, 141, 76, 0.82) 12%, rgba(243, 226, 199, 0.67) 22%,
		rgba(242, 224, 196, 0.64) 24%, rgba(233, 212, 179, 0.64) 37%);
	background: -ms-linear-gradient(left, rgba(193, 158, 103, 1) 0%,
		rgba(182, 141, 76, 0.82) 12%, rgba(243, 226, 199, 0.67) 22%,
		rgba(242, 224, 196, 0.64) 24%, rgba(233, 212, 179, 0.64) 37%);
	background: linear-gradient(to right, rgba(193, 158, 103, 1) 0%,
		rgba(182, 141, 76, 0.82) 12%, rgba(243, 226, 199, 0.67) 22%,
		rgba(242, 224, 196, 0.64) 24%, rgba(233, 212, 179, 0.64) 37%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#c19e67',
		endColorstr='#e9d4b3', GradientType=1);
} */
</style>
<nav class="navbar navbar-default navbar-static-top " role="navigation"
	style="background-color: #136572; height:50px">
	<div class="navbar-header ">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<!-- <a class="navbar-brand" href="/SMS/login/home.do"
			style="color: #080808; font-size: 22px; font-style: oblique; font-weight: bold; margin-right: 0px; margin-left: 0px"> -->
	<span style="color:white"></span><a class="navbar-brand" href="/SMS/login/home.do" style="padding:0px 0px;"><img src="${pageContext.request.contextPath}/img/logo.jpg" style="width: 250px;height: 49px;">
		</a>
		<a class="navbar-brand" href="#"
			style="color: #fff; font-size: 14px; font-style: oblique;"> Login
			timestamp :<fmt:formatDate type="both" dateStyle="medium"
				timeStyle="long" value="${sessionScope.loginTime}"
				pattern="d MMM hh:mm a" />
		</a>


	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">


		<%-- <li>Login Timestamp :
		<fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "long" value = "${sessionScope.loginTime}" pattern="d MMM hh:mm a" /></li> --%>
		<li style="color: #fff; font-size: 14px; font-style: oblique;">Welcome
			Mr.${sessionScope.User.userName}</li>
		<!-- /.dropdown -->

		<!-- /.dropdown-alerts -->
		<!-- </li> -->

		<!-- /.dropdown -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<!--    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>-->
				<li><a
					href="${pageContext.request.contextPath}/docs/userManual.pdf"
					target="_blank"><i class="fa fa-info" aria-hidden="true"></i>
						Help</a></li>
				<li class="divider"></li>
				<li><a
					href="${pageContext.request.contextPath}/login/logout.do"><i
						class="fa fa-sign-out fa-fw"></i> Logout</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse gradient" style="background-color: #136572;">
			<ul class="nav" id="side-menu">
				<li class="sidebar-search">
					<!--<div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
							
                            <!-- /input-group --> <img
					src="${pageContext.request.contextPath}/suhel/off-road-diesel-maryland.jpg"
					style="width: 250px; height: 150px;">
				</li>
				<li><a href="${pageContext.request.contextPath}/login/home.do"><i
						class="fa fa-home fa-fw fa-2x" style="color: white;"></i> <span
						style="color: white; font-weight: bold;">Home</span></a></li>
				<!-- <li>
                    
                             <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Charts<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="flot.html">Flot Charts</a>
                                </li>
                                <li>
                                    <a href="morris.html">Morris.js Charts</a>
                                </li>
                            </ul>
				</li>-->

				<!--   <li>
                            <a href="tables.html"><i class="fa fa-table fa-fw"></i> Tables</a>
                        </li> -->

				
				<%-- <c:if test="${sessionScope.User.userLevel=='1'}">
					<li><a
						href="${pageContext.request.contextPath}/upload/file.do"><i
							class="fa fa-upload fa-fw fa-2x" style="color: lightblue;"></i><span
							style="color: Black; font-weight: bold;"> File Upload </span></a></li>

				</c:if>
 --%>				<%-- <c:if test="${sessionScope.User.userLevel=='1'}">
					<li><a
						href="${pageContext.request.contextPath}/print/newApp.do"><i
							class="fa fa-print fa-fw fa-2x" style="color: lightblue;"></i> <span
							style="color: Black; font-weight: bold;">Print </span></a></li>
				</c:if> --%>

				<%-- <c:if test="${sessionScope.User.userLevel=='2'}">
					<li><a
						href="${pageContext.request.contextPath}/print/getAllApplicantion.do"><i
							class="fa fa-print fa-fw fa-2x" style="color: lightblue;"></i> <span
							style="color: Black; font-weight: bold;">Print </span></a></li>
				</c:if> --%>


               <li class="dropdown-submenu1">
                <a href="#" id="btn-1" data-toggle="collapse" data-target="#submenu1" aria-expanded="false">
                          <i class="fa fa-cc-mastercard fa-fw fa-2x" style="color: white;"></i><span
						   style="color: white; font-weight: bold;" >&nbsp;&nbsp;Master <b class="caret"></b></span></a>
          <ul class="dropdown-menu1" role="menu" aria-labelledby="btn-1">
                         <c:if test="${sessionScope.User.role=='1'}">
                         <li><a href="${pageContext.request.contextPath}/user/users.do" style="text-align: left;text-decoration: none;padding:3px 40px;">
						     <i class="fa fa-users fa-fw fa-2x" style="color: white;"></i> <span
						     style="color: white; font-weight: bold;">Users </span>
				         </a></li>
				         </c:if>
				         <li><a
					          href="${pageContext.request.contextPath}/vehicle/vehicleslist.do" style="text-align: left;text-decoration: none;padding:3px 32px;">
					          <i class="fa fa-truck fa-fw fa-2x" style="color: white;"></i> <span
						      style="color: white; font-weight: bold;">Vehicles</span>
				         </a></li>
				    <li><a 
					       href="${pageContext.request.contextPath}/vendor/vendors.do" style="text-align: left;text-decoration: none;padding:3px 32px;">
					       <i class="fa fa-vine fa-fw fa-2x" style="color: white;"></i> <span
					       style="color: white; font-weight: bold;">Vendors</span>
				    </a></li>
				    <li><a 
					      href="${pageContext.request.contextPath}/site/sites.do" style="text-align: left;text-decoration: none;padding:3px 43px;">
						  <i class="fa fa-globe fa-fw fa-2x" style="color: white;"></i> <span
						  style="color: white; font-weight: bold;">Sites</span>
				    </a></li>
          </ul>
    </li>

				
				<li><a
					href="${pageContext.request.contextPath}/dataEntry/dataEntryList.do">
						<i class="fa fa-edit fa-fw fa-2x" style="color: white;"></i> <span
						style="color: white; font-weight: bold;">Entry</span>
				</a></li>
				
				<li><a href="${pageContext.request.contextPath}/report/reportList.do">
						<i class="fa fa-file fa-fw fa-2x" style="color: white;"></i>
						<span style="color: white; font-weight: bold;">Report </span>
				</a></li>
				<%-- <c:if test="${sessionScope.User.role=='1'}">
					<li><a href="${pageContext.request.contextPath}/eft.do"><i
							class="fa fa-upload fa-fw fa-2x" style="color: white;"></i><span
							style="color: white; font-weight: bold;"> External Fund
								Transfer</span></a></li>
				</c:if> --%>
               
				<%-- <li><a
					href="${pageContext.request.contextPath}/cheque/showChequePrint.do"><i
						class="fa fa-envelope fa-fw fa-2x" style="color: lightblue;"></i> <span
						style="color: Black; font-weight: bold;">Print </span></a></li> --%>
				<li><a
					href="${pageContext.request.contextPath}/user/changePasswordPage.do"><i
						class="fa fas fa-key fa-fw fa-2x" style="color: white;"></i> <span
						style="color: white; font-weight: bold;">Change Password </span></a></li>
						<li><a
					href="${pageContext.request.contextPath}/importExport/exportPage.do"><i
						class="fa fa-cloud-upload fa-fw fa-2x" style="color: white;"></i> <span
						style="color: white; font-weight: bold;">Export</span></a></li>
						<li><a
					href="${pageContext.request.contextPath}/importExport/importPage.do"><i
						class="fa fa-cloud-download  fa-fw fa-2x" style="color: white;"></i> <span
						style="color: white; font-weight: bold;">Import</span></a></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>
