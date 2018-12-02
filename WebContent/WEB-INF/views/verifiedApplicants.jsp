<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
<script>
$(document).ready(function(){
	$('.modal-body').hide();
	 $('.formSubmit').on('click',function(){
		 $('.modal-body').show();
		}); 
	}); 
</script>
</head>
<body>

				
				  <div class="modal-body" style=" margin-top: -50px;
    margin-left: 100px;" >
			<img alt="" src="${pageContext.request.contextPath}/img/searching.gif">
	</div>
		<%-- 		 <form:form method="POST" id="userForm" 
		action="${pageContext.request.contextPath}/upload/search.do" 
		commandName="fileSearch">	
		
		<div class = "table-responsive col-lg-10">
		<table class ="table table-bordered table-striped table-highlight" >
			<tr>
				<td>Employee Id</td>
				<td><form:input path="empId" /></td> 
		
				<td>Mobile Number</td>
				<td><form:input path="mobileNumber"  /></td>
				     <span style="color:red;" id="result"></span>
			</tr>
				<tr>
				<td>From Upload Date</td>
				<td><form:input path="fromDate"  type="date" id="fromDate"/></td>
				<td>To Upload Date</td>
				<td><form:input path="toDate"   id="toDate"/></td>
			</tr>
			<tr>
				<td>Month</td>
				<td><form:select path="month" >
						<option value="0">ALL</option>						
						<form:options items="${months}"  />						
					</form:select>
				</td>
				<td id="uploadingImg" style="display: none" rowspan=6 colspan=6 ><img alt="" src="${pageContext.request.contextPath}/img/file_uploading.gif"> </td>
			</tr>
			<tr>
				<td><a class="btn btn-primary" href="${pageContext.request.contextPath}/user/users.do">Back</a></td>
				<!-- <td colspan="2"><input type="reset" class="btn btn-primary" value="Reset" /> -->
				<td></td>
				<td><input type="submit" style="margin-left: 36px;" class="formSubmit button_style btn btn-primary" value="Search" /></td>		
			</tr>
		</table>
		
		</div>
	</form:form> --%>
 

<c:choose>
	<c:when test="${!empty appVerified}">
      <div class="row">
         <div class="col-lg-12">
         
<!-- 	<h3 style="text-align:center;"> Search Results</h3> -->
            <div class="panel panel-default">
              
               <span style="color: green;text-align: center;display:inline-block;margin-left:25%;">${message}</span>
               
               <div class="panel-body">
                  <div class="table-responsive" style="margin-bottom: 10%;">
                  
              <%--  <form:form method="POST" id="form"
		action="${pageContext.request.contextPath}/upload/submitFile.do"> --%>    	
                  	<table id="myDatatable" class ="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>Sr.No</th>
					<th>Applicant NIN</th>
					<th>Applicant Name</th>
					<th>Applicant Id</th>
					<th>Account No.</th>
					<th>IBAN</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${appVerified}" var="app" varStatus="idx">
					<tr>
						<%-- <td>
						<c:if test="${app.status=='Rejected'}" >
						<input type="checkbox" value="${file.fileId}" name="fileIds">
						</c:if>
						</td> --%>
						<td><c:out value="${idx.index +1}" /></td>
						<td><c:out value="${app.applicantNIN}" /></td>
						<td><c:out value="${app.applicantName}" /></td>
						<td><c:out value="${app.applicantID}" /></td>
						<td><c:out value="${app.accountNumber}" /></td>
						<td><c:out value="${app.iban}" /></td>
						
						
					<%-- 	<td>
						 <fmt:formatDate type = "both" 
         dateStyle = "medium" timeStyle = "long" value = "${file.uploadDate}" pattern="d MMM y hh:mm a" /></td> --%>
         <%-- 	<td>
						 <fmt:formatDate type = "both" 
         dateStyle = "medium" value = "${file.uploadDate}" pattern="d MMM y" /></td> 
						<td><c:out value="${file.recordCount}" /></td>
						<td><c:out value="${file.status}" /></td>
						<td><c:out value="${file.batchId}" /></td>	 --%>							
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- <div class="center">
		<input id="submit" type ="submit" value="Submit" class="btn btn-primary" disabled="disabled"/>
		</div> -->
		<%-- </form:form> --%>
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
                  <div class="table-responsive" style="text-align:center;">
                  No Data Found
                  </div>
                  </div>
                  </div>
                  </div>
                  </div>
                  	
	</c:otherwise>
	</c:choose>
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jquery-1.12.4.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jquery.dataTables.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/buttons.flash.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jszip.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/pdfmake.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/vfs_fonts.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/buttons.html5.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/dataTables.buttons.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/buttons.print.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
		//for enable and disable of reject and aprove
		 /* var fileIds = [];
           $.each($("input[name='fileIds']:checked"), function(){            
           	fileIds.push($(this).val());
           }); */
		/* $("input[name=fileIds]").click(function(){
			var checked = $("input[name='fileIds']:checked").length;
			
			if(checked!= 'undefined' && checked!=null && checked!=0) {
	        	   $("#submit").prop('disabled','');	        	   	
	        	   }
			else{
				 $("#submit").prop('disabled','disabled');
			}
		 }); */
		
		
		var oTable = $('#myDatatable').DataTable( {
			
		        dom: 'Blfrtip',
		      
		        buttons: [
		       {
		           extend: 'pdf',
		           footer: true,
		           title:'Uploaded_File',
		           filename: 'Uploaded_File',
		           exportOptions: {
		                columns: [0,1,2,3,4,5,6]
		            }
		       },
		       {
		           extend: 'excel',
		           footer: true,
		           title: 'Uploaded_File',
		           filename: 'Uploaded_File',
		           exportOptions: {
		        	   columns: [0,1,2,3,4,5,6]
		            }
		       }         
		    ]  
		    
		    } );
		/*  $( "#fromDate" ).datepicker({
		    	dateFormat:'yy-mm-dd'
		    });
		    $( "#toDate" ).datepicker({
		    	dateFormat:'yy-mm-dd'
		    }); */
		} );
	</script><!-- <script type="text/javascript">
	$(function() {
	    $( "#fromDate" ).datepicker();
	    $( "#toDate" ).datepicker();
	 });	
	</script> -->
</body>
</html>