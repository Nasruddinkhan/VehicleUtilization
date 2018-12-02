<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add New Vehicle</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<link	href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
<style type="text/css">
form input{
height: 25px;
width: 250px;
border-radius: 5px;
border:none;
border:1px solid #adb0b5;
}
form #selecter,#selecter1{
height: 25px;
width: 250px;
border-radius: 5px;
}
form #selectminutes{
height: 25px;
width: 50px;
margin-left:20px;
border-radius: 5px;
}
form #selecthours{
height: 25px;
width: 50px;
border-radius: 5px;

}
form #btnrest{
height: 35px;
width: 90px;
}
#status{
height: 15px;
width: 20px;
}
.super{
color:red;
}
</style>
</head>
<body>
	<h2 style="color:#1b85ae; padding-left: 20px;">	
	<c:choose>
	<c:when test="${vehicle.vehicleId > 0}"> Update Vehicles</c:when>
	<c:otherwise>
	Add Vehicles</c:otherwise>
	</c:choose>
	</h2><span style="color: red;text-align: center;display:inline-block;margin-left:25%;">${message}</span>
	<form:form method="POST"  enctype="multipart/form-data" id="userForm" onsubmit="return save();"
	action="${pageContext.request.contextPath}/vehicle/saveVehicle.do"
	 commandName="vehicle">
		<input type="hidden" value="${sessionScope.User.loginId}" name="loginId" />
		<div class = "table-responsive col-lg-6">
		<table class ="table table-bordered table-striped table-highlight" id="mytable" >
			<tr>
				<td>Vendor<sup class="super">*</sup></td>
				<td>
				<form:select path="vendorType.VendorId" class="required" id="selecter1">
				        <option>--SELECT--</option>
						<form:options items="${vendor}" itemValue="VendorId" itemLabel="Vendor_Name"/>
					</form:select>
				</td>
			</tr>
			<tr>
			      
				<td>Vehicle Type<sup class="super">*</sup></td>
				<td><form:input path="vehicleId" name="vehicleId" id="vehicleId" type="hidden"  /> 
				<form:select path="vehiclesType.m_Id" class="required" id="selecter">
						<option value="">--SELECT--</option>
						<form:options items="${moter}" itemValue="m_Id" itemLabel="m_Name" />
					</form:select> </td>
			</tr>
			
			  <tr class="addtr"  >
			        <td>Type<sup class="super">*</sup></td>
			        <td><form:input path="Type" name="vehicleId" id="Type" class="" type="text" maxlength="40" /></td>
			  </tr>
			  
			<tr>
				<td>Vehicle Number<sup class="super">*</sup></td>
				<td><form:input path="vehiclesNumber"  class="required" maxlength="15"/><br>
				<span style="color:red;" id="result"></span></td>
			</tr>
			
			<tr>
				<td>Model Number<sup class="super">*</sup></td>
				<td><form:input path="modelNumber"  class="required" maxlength="15" /></td>
			</tr>
			<tr>
				<td>Register Date<sup class="super">*</sup></td>
				<td><form:input path="issueDate"  class="required" id="datepicker"/></td>
			</tr>
			<tr>
			<td>Status<sup class="super">*</sup></td>
			<td>
			<form:radiobutton path="status" id="status" value="ACTIVE" checked="checked"/>ACTIVE
			<form:radiobutton path="status" id="status" value="DEACTIVE"/>DEACTIVE 
			
			</td>
			</tr>
			<tr>
				<td><a class="btn btn-primary"
						href="${pageContext.request.contextPath}/vehicle/vehicleslist.do">Back</a></td>
				<td colspan="2"><input type="reset" class="btn btn-primary" value="Reset"  id="btnrest"/>
				
				<input type="submit" style="margin-left: 36px;" id="btnrest" class="button_style btn_validate btn btn-primary" value="Submit" />
				
				</td>
				</tr>
			
		</table>
		</div>

	</form:form>
	<script>
$(document).ready(function(){
	
	$("#vehiclesNumber").on("blur",function(){
		if( $("#vehiclesNumber").val() != null ){
			
			checkVehiclesNumber();
	}
		else{
			 $('#result').text("");			 
		}
	});
	
	if($('#selecter').val()==2 && $('#vehicleId').val()>0)
		{
		$('.addtr').show();
		$('#Type').attr('class','required');
		}else{
			$('.addtr').hide();
			$('#Type').attr('class',false);
		}
	 
	 $("#selecter").on("change",function(){
		 changeUI();
	});	
});

function changeUI(){
	var vehicleType = parseInt($('#selecter').val());
	
	if(vehicleType == 2){
		$('.addtr').show();
	}else{
		$('.addtr').remove();
	}
}

function checkVehiclesNumber(){
	var base_url = window.location.origin;	
	base_url = base_url+"<%=request.getContextPath()%>";
	var vehicleId=$("#vehicleId").val();
	vehicleId = ""==vehicleId ?0:vehicleId; 
		
		$.ajax({
			type : "post",
			url : base_url + "/vehicle/checkVehiclesNumber.do",
			cache : false,
			data : 'vehiclesNumber=' + $("#vehiclesNumber").val()+'&vehicleId='+vehicleId,
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
<script type="text/javascript">
$(function () {
	$("#datepicker").datepicker({
		dateFormate:'yy-mm-dd'
	});
});

</script>
<script src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
</body>
</html>