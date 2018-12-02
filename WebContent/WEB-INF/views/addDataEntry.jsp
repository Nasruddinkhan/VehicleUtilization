<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link	href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">

<style type="text/css">
form input{
height: 25px;
width: 354px;
border-radius: 5px;
border:none;
border:1px solid #1b85ae;

}
.add{
height: 25px;
width: 283px;
border-radius: 5px;
border:none;
border:1px solid #1b85ae;

}
#expenseCtr{
width: 283px;
}
.expenseType{
	height: 24px;
    border-radius: 3px;
}
form #selecter,#selecter1,#selecter2,#vendorselecter,#unloading,#loading,#selecter4,#loading_unloading{
height: 25px;
width: 354px;
border-radius: 5px;
border:1px solid #1b85ae;
}
form #selectminutes{
height: 25px;
width: 50px;
margin-left:20px;
border-radius: 5px;
border:1px solid #1b85ae;
}
form #selecthours{
height: 25px;
width: 50px;
border-radius: 5px;
border:1px solid #1b85ae;
}
form #txtarea{
width: 354px;
border-radius: 5px;
border:none;
border:1px solid #1b85ae;
}
form #btnrest{
height: 35px;
width: 90px;
}
form #datepicker,#datetimepicker,#datetimepicker1,#enddatetimepicker,#enddatetimepicker1,#setup_hours,#setup_hours1{
height: 25px;
width:100px;
}
.super{
color:red;
}
</style>
<script>


function save(){
	return ""==$("#result").text();	
	
}
</script>

</head>


<body>
	<h2 style="color:#1b85ae; padding-left: 20px;">	
	<c:choose>
	<c:when test="${dataEntry.dataEntryId > 0}"> 
	<input type="hidden" value="${expenseCount}" id="rowValue">
	<%-- <input type="hidden" value="${dataentryId}" name="dataEntryId"> --%>
	Update Entry</c:when>
	<c:otherwise>
	Add Entry</c:otherwise>
	</c:choose>
	</h2>
	
	<form:form acceptCharset="UTF-8" method="POST" id="userForm" onsubmit="return save(); return timeCalc();"
		action="${pageContext.request.contextPath}/dataEntry/saveDataEntry.do" 
		commandName="dataEntry">
		<input type="hidden" id="exphide" name="exphide" value="${expenseCount}">
		
		<%-- <form:hidden path="dataEntryId" /> --%>
		<select id="hiddenExp" style="display: none">
							<c:forEach items="${expenses}" var="e">							
							<option value='${e.expenseId}'>${e.expenseName}</option>
							</c:forEach>
		</select>
							
							
		<div class = "table-responsive col-lg-9">
		<table class ="table table-bordered table-striped table-highlight" >
		<tr>
		<td>Challan No.</td>
		<td>
		<c:choose>
		<c:when test="${flag == null}">
		<input  type="hidden" name="flagName" value="0">
		</c:when>
		<c:otherwise>
		<input  type="hidden" name="flagName" value="${flag}">
		</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${dataEntry.dataEntryId > 0}">
		 <fmt:formatNumber pattern = "0000"  type = "number" value = "${dataEntry.dataEntryId}"  />
	    <form:input path="dataEntryId" value="${dataEntry.dataEntryId}" type="hidden"/>
		</c:when>
		<c:otherwise>
	    
	    <fmt:formatNumber pattern = "0000"  type = "number" value = "${dataentryId}"  />
	    <input name="dataEntryId" value="${dataentryId}" type="hidden"/>
	    </c:otherwise>
	    </c:choose>
		</td>
		</tr>
		
		<tr>	
			        <td>Vendor<sup class="super">*</sup></td>
			      <td><form:select path="VendorType.VendorId" class="required" id="vendorselecter" >
						<option value="">--SELECT--</option>
						<form:options items="${vendor}" itemValue="VendorId" 
						itemLabel="Vendor_Name" />
					</form:select>
					</td>
			    </tr>
		
		<tr>	
			        <td>Vehicle Type<sup class="super">*</sup></td>
			      <td><form:select path="vehicleType.m_Id" class="required vehicleType" id="selecter">
						<option value="">--SELECT--</option>
						<form:options items="${moter}" itemValue="m_Id" 
						itemLabel="m_Name" />
					</form:select>
					</td>
			    </tr>
			    <%-- <c:choose>
			    <c:when test="${dataEntry.vehicleType.m_Id ==2}"> --%>
			    <tr class="Excavatortype">
				<td>Excavator Type<sup class="super">*</sup></td>
				  <td><form:select path="excavatorType.type" class="required" id="selecter4">
			         <option value="">--SELECT--</option>
			         <form:options items="${vehicle}" />
			        </form:select></td>
			</tr><%-- </c:when>
			<c:otherwise></c:otherwise>
			</c:choose>  --%>
			<tr>
				<td>Vehicle Number<sup class="super">*</sup></td>
				  <td><form:select path="vehicle.vehicleId" class="required vehicleNum" id="selecter1">
			         <option value="">--SELECT--</option>
			         <form:options items="${vehicleno}"  itemValue="vehicleId" itemLabel="vehiclesNumber"/>
			        </form:select></td>
			</tr>
			
			<tr class="worktr">
				<td>Work Type</td>
				  <td><form:select path="workType" id="selecter1" class="workType">
			           <form:option value="" label="- Select -"/>			         
			           <form:option value="BUCKET" label="BUCKET"/>
			         <form:option value="BREAKER" label="BREAKER"/>
			        </form:select></td>
			</tr>
		
			<tr>
				<td>Site<sup class="super">*</sup></td>
				<td><form:select path="site.siteId" class="required" id="selecter2">
						<option value="">--SELECT--</option>
						<form:options items="${sites}" itemValue="siteId" 
						itemLabel="siteName" />
					</form:select></td>
			</tr>
			<tr class="dateentry">
				<td>Entry Date<sup class="super">*</sup></td>
				
				<td id="datetd"><form:input path="entryDate" id ="datepicker"/>
				<c:choose>
				<c:when test="${dataEntry.dataEntryId > 0}">
				<label class="lblstarttime" style="font-size:14px;font-weight: lighter;">
				Start Time:</label>
				&nbsp;<form:input type="time" path="Time" id ="datetimepicker1"/>
				<label class="lblendtime" style="font-size:14px;font-weight: lighter;">End Time:</label>
				<form:input type="time" path="endTime" id ="enddatetimepicker1" class="endtime"/>&nbsp;
				<%-- <form:input  path="difftime" name="difftime" readonly="true" style="font-size:14px;font-weight: lighter;" id="setup_hours1"/>&nbsp;
				<label style="font-size:14px;font-weight: lighter;" id="setup_hourslbl">Minute(s)</label> --%>
				</c:when>
				<c:otherwise>
				<label class="lblstarttime" style="font-size:14px;font-weight: lighter;">
				Start Time:</label>&nbsp;<form:input type="time" path="Time" id ="datetimepicker"/>
				<label class="lblendtime" style="font-size:14px;font-weight: lighter;">End Time:</label>
				<form:input type="time" path="endTime" id ="enddatetimepicker" class="endtime"/>&nbsp;
				<%-- <form:input  path="difftime" name="difftime" readonly="true" style="font-size:14px;font-weight: lighter;" id="setup_hours"/>&nbsp;
				<label style="font-size:14px;font-weight: lighter;" id="setup_hourslbl">Minute(s)</label> --%>
				</c:otherwise>
				</c:choose>
				<form:input  path="difftime" name="difftime" readonly="true" class="required" style="font-size:14px;font-weight: lighter;" id="setup_hours"/>&nbsp;
				<label style="font-size:14px;font-weight: lighter;" id="setup_hourslbl">Minute(s)</label>
				<br>
				<span style="font-size:14px;color:red;" id="spnmsg"></span>
				</td>
			</tr>
			    <tr class="loading_unloading">
				<td>Loading/Unloading</td>
				<td><form:select path="loading_unloading" id="loading_unloading">
							<form:option value="" label="- Select -"/>			         
			           <form:option value="loading" label="loading"/>
			         <form:option value="unloading" label="unloading"/>
				</form:select>
				 <%--Time In Minutes
				<form:select path="minutes" id="selectminutes">
							<form:options items="${minutes}"  />
				</form:select> --%>
				</td>  
			</tr>
			
			  <tr class="Excavator">
				<td>Loading From</td>
				<td><form:input path="loading"  class="required" id="loading" maxlength="20"/>
				</td>
			</tr>
			<tr class="Excavator">
				<td>UnLoading To</td>
				<td>
				<form:input path="unloading" class="required" id="unloading" maxlength="20"/>
				</td>
			</tr>
			  <tr>
				<td>Rate<sup class="super">*</sup></td>
				<td><form:input path="rate"  class="required" id="rate" maxlength="10" />-<i class="fa fa-inr"></i></td>
			</tr>
			 <tr class="Excavator">
				<td >Distance</td>
				<td><form:input path="distance" id="distance" maxlength="10"/>-K.M</td>
			</tr>
			<%--  <tr>
				<td>Diesel</td>
				<td><form:input path="diesel"  id="diesel" />-ltr</td>
			</tr> --%>
			<tr class="expense">
				<td>Expense</td>
				<td colspan="3">		
				 <a class="add-row" title="Click to add expense"><i class="fa fa-plus-square" style="font-size:20px"></i></a> <br><br>   
				 
				<table id="expense">				
							<c:forEach items="${expenseList}" varStatus="i" var="exp">
							
							<tr><td>
							<select name="expenseType_${i.index+1}" class="expenseType" >
							<c:forEach items="${expenses}" var="expense">							
							<option value='${expense.expenseId}'  ${expense.expenseId == exp.expenses.expenseId ? 'selected' : ''}>${expense.expenseName}</option>
							</c:forEach>
							</select>
							<input type="text" id="expenseAmount${i.index+1}" name="expenseAmount_${i.index+1}" maxlength="10" class="add" onchange="calculateExpense()" value="${exp.expenseAmount}" >
							&nbsp;<a class="delete-row" title="Delete"><i class="fa fa-trash-o" style="font-size:20px;color:red"></i></a><br><br>
			           		</td></tr>					
							</c:forEach>
							
								
				<%-- <tr><td>
				<select name="expenseType_1" >
				<c:forEach items="${expenses}" var="expense">
				<option value='${expense.expenseId}'>${expense.expenseName}</option>
				</c:forEach>
				</select>
				<input type="text" name="expenseAmount_1" class="add">
            <a class="add-row">Add Expense</a>    
				<form:input path="expense" id="expenc" />-<i class="fa fa-inr"></i>
				</td></tr> --%>
				</table>
				<label style="font-size:14px; width:63px; text-align:center; font-weight: lighter" >Total</label>
				<form:input path="expense" id="expenseCtr" readonly="true" class="required" />-<i class="fa fa-inr"></i></td>
			</tr>
			
			<tr>
				<td>Remarks</td>
				<td>
				<form:textarea rows="2" cols="21" path="remarks" id="txtarea"></form:textarea> </td>
			</tr>
			<tr>
				<td><a class="btn btn-primary" href="${pageContext.request.contextPath}/dataEntry/dataEntryList.do">Back</a></td>
				<td colspan="2"><input type="reset" class="btn btn-primary" value="Reset"  id="btnrest"/>
				<input type="submit" style="margin-left: 36px;" id="btnrest" class="button_style btn_validate btn btn-primary" value="Submit" /></td>
			</tr>
			
		</table>
		<br/>
		
		</div>

	</form:form>
	<script type="text/javascript">
	
	var i = 0;
	$(document).ready(function () {
		  $(".delete-row").on('click',function(){
		
              $(this).parent().parent().remove();
              i = i - 1;
              $('#exphide').val(i);
          
              calculateExpense();
          });
		var rowVal  = parseInt($('#rowValue').val());
		console.log("rowVal ["+rowVal+"]");
		if(isNaN(rowVal)){
			console.log("Add mode ==>>");
			i = 1;
		}else{
			i = rowVal;
		}
		changeUI();
		var months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
        var d = new Date(),
        date = ( d.getFullYear() + '-' +(d.getMonth()+1)  + '-' +(d.getDate()));
        
        $('#datepicker').val(date);
        var d = new Date(),
        time=(d.getHours()+':'+d.getMinutes());
        $('#datetimepicker').val(time);
        $('#enddatetimepicker').val(time);
        
      
        $("#rate,#distance,#diesel,#expenc").keydown(function (e) {
        	onlynumber(e);
        });
       
        $(".add-row").click(function(){
        	  i = i + 1;
        	var markup = "<tr><td><select  id='expenseType_"+i+"' class='expenseType' name='expenseType_"+i+"'></select>&nbsp<input type='text' id='expenseAmount"+i+"' maxlength='10' onchange='calculateExpense()'  class='add' name='expenseAmount_"+i+"'>&nbsp<a class='delete-row' title='Delete'><i class='fa fa-trash-o' style='font-size:20px;color:red'></i></a><br><br></td></tr>";
            $("#expense").append(markup);
            var $options = $("#hiddenExp > option").clone();
    		$("#expenseType_"+i).append($options);
    		$('#exphide').val(i);
         
         
           $(".delete-row").on('click',function(){
               $(this).parent().parent().remove();
               i = i - 1;
               $('#exphide').val(i);
       
               calculateExpense();
           });
        });
               


        $('#enddatetimepicker1, #datetimepicker1').blur(function () {
        	 var valuestart = $('#datetimepicker1').val();
             var valuestop = $('#enddatetimepicker1').val();
             timeCalcedit(valuestart,valuestop);
	      }); 
        function timeCalcedit(valuestart, valuestop) {
            var hourStart = new Date("01/01/2007 " + valuestart).getHours();
            var hourEnd = new Date("01/01/2007 " + valuestop).getHours();
            var minuteStart = new Date("01/01/2007 " + valuestart).getMinutes();
            var minuteEnd = new Date("01/01/2007 " + valuestop).getMinutes();

            var hourDiff = hourEnd - hourStart;
            var minuteDiff = minuteEnd - minuteStart;
            var difminuts = (60*hourDiff)+(1*minuteDiff);
            
            if (minuteDiff < 0) {
                hourDiff = hourDiff - 1;
            }
           
            if(difminuts <= 0){
          	  $('#spnmsg').html("End time should be greater (>) than start time ");
          	  return false;
            }else{
          	  $('#setup_hours').val(difminuts);
          	  $('#spnmsg').html("");
          	  return true;
            }
        };	
        
        $('#enddatetimepicker, #datetimepicker').blur(function(){
        	 var valuestart = $('#datetimepicker').val();
             var valuestop = $('#enddatetimepicker').val();
      	  timeCalc(valuestart,valuestop); 
        });
        
             function timeCalc(valuestart, valuestop) {
              var hourStart = new Date("01/01/2007 " + valuestart).getHours();
              var hourEnd = new Date("01/01/2007 " + valuestop).getHours();
              var minuteStart = new Date("01/01/2007 " + valuestart).getMinutes();
              var minuteEnd = new Date("01/01/2007 " + valuestop).getMinutes();

              var hourDiff = hourEnd - hourStart;
              var minuteDiff = minuteEnd - minuteStart;
              var difminuts = (60*hourDiff)+(1*minuteDiff);
              if (minuteDiff < 0) {
                  hourDiff = hourDiff - 1;
              }
             /*  alert(difminuts); */
              if(difminuts <= 0){
            	  $('#spnmsg').html("End Time should be greater (>) than start Time ");
            	  return false;
              }else{
            	  $('#setup_hours').val(difminuts);
            	  $('#spnmsg').html("");
            	  return true;
              }
              
          };
          
    });/*end Ready function*/
    $("#selecter2").change(function () {
        var selectedValue = $(this).val();
         $("#loading").val($(this).find("option:selected").html()) 
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
    
    }
    
    $(document).on("keydown",".add", function(e){
    	onlynumber(e);
      });

    function save(){
    	return ""==$("#result").text();	
    	
    } 
	 $(function() {
	    $( "#datepicker" ).datepicker({
	    	dateFormat:'yy-mm-dd'
	    });
	 });	
	 $(function() {
	   $( "#datetimepicker" ).datetimepicker({
	    	timeFormat:"HH:mm a"
	    });
	   $( "#enddatetimepicker" ).datetimepicker({
	    	timeFormat:"HH:mm a"
	    });
	 });	
	    
	 
	 if($('.vehicleType').val()==0){
		 $('.lblstarttime').hide();
		 $('.Excavatortype').hide();
		 $('.lblendtime').hide();
		 $('.endtime').hide();
		 $('.worktr').hide();
		 $('#loading_unloading').attr('class',false);
		 $('.loading_unloading').hide();
 		$('#setup_hours').hide();
 		$('#setup_hourslbl').hide();
 	   $( "#datetimepicker" ).hide();
	 }
    $('.vehicleType').change(function() {
    		getVehicles();
    		getExcavatorType();
    	  	changeUI();
	});
    
    $('#vendorselecter').change(function() {
    	getVehicleTypes();	  	
});
    function tryParseInt(str, defaultValue) {
        return parseInt(str) == str ? parseInt(str) : defaultValue;
    }

    function calculateExpense(){
    	
    	var expenseCounter =parseInt( $('#exphide').val());
  
    	console.log(expenseCounter);
    	$('#expenseCtr').val();
    	var sum = 0;
    	//for(var j = 1; j<=expenseCounter; j++){
    		//console.log($("#expenseAmount"+j).val());
    		
    		var expAmountArr = $( "input[name^='expenseAmount']" );
    	
    		for(var i=0;i<expAmountArr.length;i++){
    		
    		sum =  parseInt( sum ) + tryParseInt(expAmountArr[i].value, 0);
  
    		}
    	//}
    
    	$('#expenseCtr').val(sum);
    	
    }
    function changeUI(){
    	var vehicleType = parseInt($('.vehicleType').val());
        	
    	if(vehicleType == 2){
    		$('.loading_unloading').hide();
    		$('#loading_unloading').attr('class',false);
    		$('.Excavatortype').show();
    		$('.Excavator').hide();
    		$('.lblstarttime').show();
    		$('.lblendtime').show();
    		$('.worktr').show();
    		$('.endtime').show();
    		$('.lblstarttime').html("Start Time:");
    		$('.lblendtime').html("End Time:");
    		$('#setup_hours').show();
    		$('#setup_hourslbl').show();
    		$( "#datetimepicker" ).show();
    		$('#loading,#unloading').attr('class',false);
    		$('#selecter4').attr('class','required');
    		$('.workType').addClass('required');
    		/* $('#datetd').append("<label id='lbltime' style='font-size:14px;font-weight: lighter;'>EndTime:</label><input path='endTime' id ='endtimepicker' style='height:25px;width:55px;'>"); */
    	}else if(vehicleType == 1){
    		$('.Excavatortype').hide();
    		$('.loading_unloading').show();
    		$('#loading_unloading').attr('class','required');
    		$('.Excavator').show();
    		$('.lblstarttime').show();
    		$('.lblendtime').show();
    		$( "#datetimepicker" ).show();
    		$('.lblstarttime').html("In Time:");
    		$('.lblendtime').html("Out Time:");
    		$('#loading,#unloading').attr('class','required');
    		$('#selecter4').attr('class',false);
    		$('.workType').removeClass('required');
    		$('.lblendtime').show();
    		$('.worktr').hide();
    		$('.endtime').show();
    		$('#setup_hours').show();
    		$('#setup_hourslbl').show();
    		}
    }
    function getVehicles(){
    	
    	var vehicleType = $('.vehicleType').val();
    	var vendorId = $('#vendorselecter').val();    	
    	vehicleType = ""==vehicleType ?0:vehicleType; 
    	vendorId = ""==vendorId ?0:vendorId;
    	//alert("411"+vehicleType);
    	//alert('${pageContext.request.contextPath}/vehicle/getVehicleByVehicleType/'+vehicleType+'.do');
    		$.ajax({
    			type : "get",
    			url : '${pageContext.request.contextPath}/vehicle/getVehicleByVehicleType/'+vehicleType+'/'+vendorId+'.do',
    			cache : false,
    			success : function(response) {
    				response = JSON.parse(response);
    			//	alert(response);
    				var s="<option value=''> --SELECT--</option>";
    				for(var i=0;i<response.length;i++){
    					s+="<option value='"+response[i].vehicleId+"'> "+response[i].vehiclesNumber+"</option>";
    				}				
    				
    				$(".vehicleNum").html(s);
    			},
    			error : function(xhr, ajaxOptions, thrownError) {
    				alert(thrownError);		
    			}
    		});
    	}
  function getExcavatorType(){
    	
    	var vehicleType = $('.vehicleType').val();
    	
    	var vendorId = $('#vendorselecter').val();    	
    	vehicleType = ""==vehicleType ?0:vehicleType; 
    	vendorId = ""==vendorId ?0:vendorId;
    	
    	if(2==vehicleType){
    	//alert("411"+vehicleType);
    	//alert('${pageContext.request.contextPath}/vehicle/getVehicleByVehicleType/'+vehicleType+'.do');
    		$.ajax({
    			type : "get",
    			url : '${pageContext.request.contextPath}/vehicle/getExcavatorTypeByVendorAndVehicleType/'+vehicleType+'/'+vendorId+'.do',
    			cache : false,
    			success : function(response) {
    				response = JSON.parse(response);
    				//alert(response.length);
    			//	alert(response);
    				var s="<option value=''> --SELECT--</option>";
    				for(var i=0;i<response.length;i++){
    					s+="<option value='"+response[i]+"'> "+response[i]+"</option>";
    				}				
    				
    				$("#selecter4").html(s);
    			},
    			error : function(xhr, ajaxOptions, thrownError) {
    				alert(thrownError);		
    			}
    		});
    	}
    	}
function getVehicleTypes(){
    	//alert();
    	var vendorId = $('#vendorselecter').val();    	
    	vendorId = ""==vendorId ?0:vendorId; 
    	//alert("411"+vehicleType);
    	//alert('${pageContext.request.contextPath}/vehicle/getVehicleByVehicleType/'+vehicleType+'.do');
    		$.ajax({
    			type : "get",
    			url : '${pageContext.request.contextPath}/vehicle/getVehicleTypeByVendor/'+vendorId+'.do',
    			cache : false,
    			success : function(response) {
    				response = JSON.parse(response);
    				//alert(response);
    				var s="<option value=''> --SELECT--</option>";
    				for(var i=0;i<response.length;i++){
    					s+="<option value='"+response[i].m_Id+"'> "+response[i].m_Name+"</option>";
    				}				
    				
    				$(".vehicleType").html(s);
    			},
    			error : function(xhr, ajaxOptions, thrownError) {
    				alert(thrownError);		
    			}
    		});
    	}
    

    /*EnD*/
   
	</script>
		<script src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.js"></script>

</body>
</html>