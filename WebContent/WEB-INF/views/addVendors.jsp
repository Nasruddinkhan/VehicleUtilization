<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add new site</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link	href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet"> 

<style>
.super{
color:red;
border-radius: 5px;
width: 250px;
    height: 24px;
 
}
#div{
border-radius: 5px;
width: 250px;
    height: 24px;

}
#required{
margin-left:5px;
}
form input{
border-radius: 5px;
width: 250px;
 height: 30px;
border:none;
border:1px solid #1b85ae;
}
form #btnrest{
height: 35px;
width: 90px;
}
#status{
height: 15px;
width: 20px;

}
#address {
    overflow: auto;
    width: 250px;
    border-radius: 5px;
}
</style>  




</head>
<body>
<script>
$(document).ready(function(){
	
	$("#vendor_Name").on("blur",function(){
		if( $("#vendor_Name").val() != null ){
	
			checkVendorName();
			
	}
		else{
			 $('#result').text("");	
			
		}
	});
	/* $("#submit").on("click",function(){
		alert("hi");
		$("#userForm").submit();	
	}); */
	 $("#Vendor_Contact_No").keydown(function (e) {
     	onlynumber(e);
     });
     function onlynumber(e){
         if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         		(e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
         		(e.keyCode >= 35 && e.keyCode <= 40)) {
                  return;
         }
         if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        } 
     
     };
});


function checkVendorName(){
	var base_url = window.location.origin;	
	base_url = base_url+"<%=request.getContextPath()%>";
	var vendorId=$("#vendorId").val();
	vendorId = ""==vendorId ?0:vendorId; 
		
		$.ajax({
			type : "post",
			url : base_url + "/vendor/checkVendor.do",
			cache : false,
			data : 'vendor_Name=' + $("#vendor_Name").val()+'&vendorId='+vendorId,
			success : function(response) {												
					$('#result').text(response);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#result').text("Error is validation vehicleId");				
			}
		});

	}
	
function save(){
	return ""==$("#result").text();	
	
}
</script>
	<h2 style="color:#1b85ae; padding-left: 20px;">	
	 <c:choose>
			<c:when test="${vendors.vendorId > 0}"> Update Vendors</c:when>
			<c:otherwise>
	Add Vendors 
	</c:otherwise>
	</c:choose> 
	
	</h2><span style="color: red;text-align: center;display:inline-block;margin-left:25%;">${message}</span>
	<form:form method="POST"  enctype="multipart/form-data" id="userForm" onsubmit="return save();"
	action="${pageContext.request.contextPath}/vendor/saveVendors.do"
	 commandName="vendors">
		<input type="hidden" value="${sessionScope.User.loginId}" name="loginId" />
		<div class = "table-responsive col-lg-8">
		<table class ="table table-bordered table-striped table-highlight" >
			
			<tr>
			<td>Vendors Name<sup class="super">*</sup></td>
				 <form:input path="vendorId" type="hidden" name="vendors_Id" id="vendorId"   />
				<td><form:input path="vendor_Name" name="vendorseName" id="vendor_Name" class="required" maxlength="40"/>
				<span style="color:red;" id="result"></span>
				</td>
				</tr>
				<tr>
					<td>Vendors Contact No<sup class="super">*</sup></td>
				<td><form:input path="vendor_Contact_no" name="Vendor_Contact_No" maxlength="10" id="Vendor_Contact_No" class="required" /></td> 
			</tr>
                   <tr>
					<td>Vendors Location<sup class="super">*</sup></td>
				<td><form:input path="vlocation" name="Vlocation" class="required" maxlength="30" /></td> 
			   
            </tr>
            
            <tr>
			 <td>Vendors Address<sup class="super">*</sup></td>
				<td><form:textarea path="address" name="Address"  class="required"  maxlength="150"/></td>
				</tr>
			<tr>
			
			    
				<td>Vendors Status<sup class="super">*</sup></td>
				<td> <form:radiobutton path="status" id="status" value="ACTIVE" checked="checked"/>ACTIVE
			        <form:radiobutton path="status" id="status" value="DEACTIVE"/>DEACTIVE</td>  
			</tr>
					
			<tr>
				<td><a class="btn btn-primary"  href="${pageContext.request.contextPath}/vendor/vendors.do">Back</a></td>
				<td><input type="reset" class="btn btn-primary" id="btnrest" value="Reset" />
				<input type="submit" style="margin-left: 36px;" id="btnrest" class="button_style btn_validate btn btn-primary" value="Submit" /></td>
			</tr>
		</table>
		</div>

	</form:form>
	<!-- <script type="text/javascript">
	$(function() {
	    $( "#datepicker" ).datepicker({
	    	dateFormat:'yy-mm-dd'
	    });
	 });	
	</script> -->
		<script src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
</body>
</html>