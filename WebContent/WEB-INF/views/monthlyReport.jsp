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
<script>
$(document).ready(function(){	
	$('.modal-body').hide();
	 $('.formSubmit').on('click',function(){
		 $('.modal-body').show();
		}); 
	}); 
</script>
</head>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
window.onload = function() { 
	var dps = [[], [], [], []];
	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		animationDuration: 2000,
		//backgroundColor: "#F5DEB3",
		exportEnabled: true,
		exportFileName: "Monthly Utilization",
		theme: "light1",
		title:{
			text: "Vehicles Utilization"
		},
		toolTip: {
			shared: true,
			reversed: true
		},
		legend: {
			reversed: true,
			cursor: "pointer",
			itemclick: toggleDataSeries
		},
		axisX:{
			  tickLength: 10,
			  margin: 5
		},
		axisY: {
			title: "Hours"
		},
		data: [{
			type: "stackedColumn",
			name: "Excavator",
			showInLegend: true,
			yValueFormatString: "#,##0 hours",
			dataPoints: dps[0]
		},{
			type: "stackedColumn",
			name: "Cement Mixer",
			showInLegend: true,
			yValueFormatString: "#,##0 hours",
			dataPoints: dps[1]
		},{
			type: "stackedColumn",
			name: "Bulldozer",
			showInLegend: true,
			yValueFormatString: "#,##0 hours",
			dataPoints: dps[2]
		},{
			type: "stackedColumn",
			name: "Dump Truck",
			showInLegend: true,
			yValueFormatString: "#,##0 hours",
			dataPoints: dps[3]
		}]
	});
	 
	function toggleDataSeries(e){
		if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
			e.dataSeries.visible =  false;
		}
		else{
			e.dataSeries.visible = true;
		}
		chart.render();
	}
	 
	var yValue;
	var label;
	 
	<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">	
		<c:forEach items="${dataPoints}" var="dataPoint">
			yValue = parseFloat("${dataPoint.y}");
			label = "${dataPoint.label}";
			dps[parseInt("${loop.index}")].push({
				label : label,
				y : yValue
			});		
		</c:forEach>	
	</c:forEach> 
	 
	chart.render();
	 
	}
	</script>

</head>
<body>

	<%-- <h3>Report</h3>
	<span
		style="color: green; text-align: center; display: inline-block; margin-left: 25%;">${message}</span> --%>
	<%-- <form method="POST" id="reportForm"
		action="${pageContext.request.contextPath}/report/dealReport.do">
	
		<input type="submit" style="margin-left: 36px;margin-top: 20px" id="submit" 
			class="button_style btn btn-primary btn_validate" value="Process" />

	</form> --%>

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
				});
		

	<%-- 	var base_url = window.location.origin;	
		if(base_url==undefined){
			base_url=	window.location.protocol+'//'+window.location.hostname+':'+window.location.port;
		}
		
		base_url = base_url+"<%=request.getContextPath()%>";
		$('#submit').click(
				function() {
					
				if('portfolio'==$('#type').val()){							
					$('#reportForm').attr('action',
							base_url + "/report/portfolioReport.do");
					//alert($('#reportForm').attr('action'));
					$('#reportForm').submit();
					//alert('Submit');
				}
				else if('dormancy'==$('#type').val()){							
					$('#reportForm').attr('action',
							base_url + "/report/dormancyReport.do");
					$('#reportForm').submit();
					//alert('Submit');
				}
				}); --%>
	
			
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
		 });
		
		
		var oTable = $('#myDatatable').DataTable( {
			
		        dom: 'Blfrtip',
		      
		        buttons: [
		       {
		           extend: 'pdf',
		           footer: true,
		           title:'Uploaded_File',
		           filename: 'Uploaded_File',
		           exportOptions: {
		                columns: [1,2,3,4,5,6]
		            }
		       },
		       {
		           extend: 'excel',
		           footer: true,
		           title: 'Uploaded_File',
		           filename: 'Uploaded_File',
		           exportOptions: {
		        	   columns: [1,2,3,4,5,6]
		            }
		       }         
		    ]  
		    
		    } );

		 } );*/
	

	
	</script>
	
	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/canvasjs.min.js"></script>
	
</body>

<%-- <body>
<div class="panel panel-primary" style="margin:20px;">
	<div class="panel-heading">
        	<h3 class="panel-title">Registration Form</h3>
	</div>
<div class="panel-body">
    <form>
<div class="col-md-12 col-sm-12">
	<div class="form-group col-md-6 col-sm-6">
            <label for="name">Name*	</label>
            <input type="text" class="form-control input-sm" id="name" placeholder="">
        </div>
        <div class="form-group col-md-6 col-sm-6">
            <label for="email">Email*</label>
            <input type="email" class="form-control input-sm" id="email" placeholder="">
        </div>

        <div class="form-group col-md-6 col-sm-6">
            <label for="mobile">Mobile*</label>
            <input type="text" class="form-control input-sm" id="mobile" placeholder="">
        </div>

	<div class="form-group col-md-6 col-sm-6">
	      <label for="address">Address*</label>
	      <textarea class="form-control input-sm" id="address" rows="3"></textarea>
	   </div>
	
	<div class="form-group col-md-6 col-sm-6">
            <label for="city">City*</label>
            <input type="text" class="form-control input-sm" id="city" placeholder="">
        </div>
	
	<div class="form-group col-md-6 col-sm-6">
            <label for="state">State*</label>
            <input type="text" class="form-control input-sm" id="state" placeholder="">
        </div>

	<div class="form-group col-md-6 col-sm-6">
            <label for="country">Country*</label>
            <input type="text" class="form-control input-sm" id="country" placeholder="">
        </div>

	<div class="form-group col-md-6 col-sm-6">
            <label for="pincode">Pincode</label>
            <input type="text" class="form-control input-sm" id="pincode" placeholder="">
        </div>

	<div class = "form-group col-md-6 col-sm-6">
	      <label for="years">You could register for up to 10 years for Kalabhairava Shanthi - M.A *</label>	 
		<span class="help-block">Please choose the no. of months for which you would like to register</span>
     
	      <select class="form-control input-sm" id="years">
		<option>-- Select No of Year --</option>
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
		<option>6</option>
		<option>7</option>
		<option>8</option>
		<option>9</option>
		<option>10</option>
	      </select>
	</div>

	<div class = "form-group col-md-6 col-sm-6">
	      <label for="months">You could register for up to 10 months for Kalabhairava Shanthi. *</label>
	      <span class="help-block">Please choose the no. of months for which you would like to register</span>	      
	      <select class="form-control input-sm" id="months">
		<option>-- Select No of Month --</option>
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
		<option>6</option>
		<option>7</option>
		<option>8</option>
		<option>9</option>
		<option>10</option>
	      </select>
	</div>

	<div class="form-group col-md-6 col-sm-6" >
            <label for="pincode">Would you be visiting ashram to participate in Kalabhairava Shanthi - M.A ? * 
	    <input type="checkbox" checked data-toggle="toggle"></label>
	    <span class="help-block">if you need a cottage to stay, you could book in advance through email to ishastay@ishafoundation.org</span>
	</div>

	<div class="form-group col-md-6 col-sm-6">
            <label for="arrival">Arrival Date</label>
            <input type="text" class="form-control input-sm datepicker" id="arrival" placeholder="">
        </div>
</div>
<div class="col-md-12 col-sm-12" id="deceased">
	<div class="form-group col-md-3 col-sm-3">
            <label for="name">Name*</label>
            <input type="text" class="form-control input-sm" id="name" placeholder="">
        </div>
	<div class="form-group col-md-3 col-sm-3">
            <label for="gender">Gender*</label>
            <input type="text" class="form-control input-sm" id="gender" placeholder="">
        </div>
	<div class="form-group col-md-3 col-sm-3">
            <label for="age">Age*</label>
            <input type="text" class="form-control input-sm" id="age" placeholder="">
        </div>
	<div class="form-group col-md-3 col-sm-3">
            <label for="DOB">Date of Birth or Exact Birth Year*</label>
            <input type="text" class="form-control input-sm datepicker" id="DOB" placeholder="">
        </div>
	<div class="form-group col-md-3 col-sm-3">
            <label for="DOD">Date of Death or Exact Death Year*</label>
            <input type="text" class="form-control input-sm datepicker" id=" DOD" placeholder="">
        </div>
	<div class="form-group col-md-3 col-sm-3">
            <label for="mother">Deceased Person's Mother Name*</label>
            <input type="text" class="form-control input-sm" id="mother" placeholder="">
        </div>
	<div class="form-group col-md-3 col-sm-3">
            <label for="father">Deceased Person's Father Name*</label>
            <input type="text" class="form-control input-sm" id="father" placeholder="">
        </div>
	<div class="form-group col-md-3 col-sm-3">
	    <label for="photo">Photo*</label>
	    <input type="file" id="photo">
	    <p class="help-block">Please upload individual photo. Group photo is not acceptable.</p>
	</div>
</div>

<div class="col-md-12 col-sm-12" id="addblock">
	<div class="form-group col-md-3 col-sm-3">
		<input type='button' class="btn btn-primary" value="Add" id="add"/>
	</div>
</div>
<div class="col-md-12 col-sm-12" >
	<div class="form-group col-md-3 col-sm-3 pull-right">
		<input type='text' class="form-control input-sm" id="amount" readonly placeholder="Total Amount"/>
	</div>
</div>
<div class="col-md-12 col-sm-12">
	<div class="form-group col-md-3 col-sm-3 pull-right" >
			<input type="submit" class="btn btn-primary" value="Submit"/>
	</div>
</div>
</form>
</div>
</body> --%>
</html>