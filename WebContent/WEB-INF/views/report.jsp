<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Report</title>
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- <link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.9/css/all.css"
	integrity="sha384-5SOiIsAziJl6AWe0HWRKTXlfcSHKmYV4RBF18PPJ173Kzn7jzMyFuTtk8JA7QQG1"
	crossorigin="anonymous"> -->
<link href="${pageContext.request.contextPath}/css/jquery-ui.css"
	rel="stylesheet">
<style type="text/css">
#data {
	width: 100% !Important;
}
.ui-widget-header {
    border: 1px solid darkgray !Important;
    background: #bbbfc3 url(images/ui-bg_gloss-wave_35_f6a828_500x100.png) 50% 50% repeat-x !Important;
}
select {
color: black !important; 
}
.spn {
	color: #1b85ae;
}

#datepicker1 {
	display: block;
}

 .datepicker,.required {
	height: 25px;
	width: 150px;
	border: 1px solid #000;
	border-radius: 3px;
}
table.dataTable thead .sorting:after {
  content: "";
  float: right;
  font-family: fontawesome;
  color: rgba(50, 50, 50, 0.5);
}
</style>
</head>
<body>
	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 style="display: block; text-align:center; color: #1b85ae;">Reports</h3>
					<form:form acceptCharset="UTF-8" method="POST" id="userForm"
						action="${pageContext.request.contextPath}/report/searchReport.do"
						commandName="searchBean">					

						<table class="table table-bordered table-striped table-highlight">
							<tr>
								<td colspan="2">From Entry Date
								<form:input path="entryFromDate" class="datepicker txtFromDate" id="txtFromDate"  readonly="true"/> To Entry Date:&nbsp;<form:input
										path="entryToDate" class="datepicker"  id="txtToDate" readonly="true"/><span id="toDate"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										
										Vendors:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<form:select path="vendorType" class="required vendorcls"
										id="vendorselecter">
										<option value="0">--ALL--</option>
										<form:options items="${vendor}" itemValue="VendorId"
											itemLabel="Vendor_Name" />
									</form:select></td>
										
							</tr>
							<tr>
								<td>Site Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<form:select path="siteId" class="required site"
										id="siteselecter" name="filter">
										<option value="0">--ALL--</option>
										<form:options items="${site}" itemValue="siteId"
											itemLabel="siteName" />
									</form:select>&nbsp;&nbsp;
							
							
								Vehicle Type
							<form:select path="vehicleType" class="required vehicleType"
										id="selectervtype">										
										<form:options items="${motor}" itemValue="m_Id"
											itemLabel="m_Name" />
									</form:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Vehicle Number
					<form:select path="vehicleNumber" class="required vehicleNum" id="VehicleNumberselecter">
						<option value="0">--ALL--</option>
					 <form:options items="${vehicle}" itemValue="vehicleId" itemLabel="vehiclesNumber" />
						</form:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <input type="submit" style="margin-left: 36px;" id="submit" class="button_style btn_validate btn btn-primary" value="Search" onclick="return validate();" />	
									</td>
									 
							</tr>
							<tr><td style="text-align: center;"><span style="color:red;">${message}</span></td></tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
	<c:if test="${!empty dataEntryList}">
	<c:set var="vehType" value="${searchBean.vehicleType}" />	
		<div class="panel-body">
			<div class="table-responsive">
			<c:choose>
			<c:when test="${searchBean.vehicleType==2}"> 			
				<%@include file="excavator.jsp" %>
				</c:when>
				<c:otherwise>
				<%@include file="tripper.jsp" %>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:if>
	</div>
	</div>
	</div>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Header</h4>
        </div>
        <div class="modal-body">
        
        </div>
        </div>
        </div>
        </div>

	<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jquery-1.12.4.js"></script>--%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/vendor/datatableJsCss/js/jquery.dataTables.min.js"></script>
	
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
	function validate(){
		var isError = true;
		var fromDate = $("#txtFromDate").val();
		var toDate = $("#txtToDate").val();
		console.log(fromDate);
		console.log(toDate);
		console.log(( fromDate.length <= 0 ) && ( toDate.length != 0 ));
		if( ( fromDate.length <= 0 ) && ( toDate.length != 0 ) ){
			isError = false;
		}else if(( toDate.length <= 0 ) && ( fromDate.length != 0 )){
			isError = false;
		}
		return isError;
	}
	
		$(document).ready(function() {
			 $("#txtFromDate").datepicker({
				          changeYear: true,
				         changeMonth:true, 
				         dateFormat:'yy-mm-dd',
				         onSelect: function(selected) {
				        	 var toDate = $("#txtToDate").val();
				        	 console.log("toDate ["+toDate+"]");
				           $("#txtToDate").datepicker("option","minDate", selected)
				           $("#txtFromDate").css("border","1px solid #000"); 
					        $("#toDate").html('');
				           if(toDate === undefined || toDate == null || toDate.length <= 0 ){
				           $("#txtToDate").css("border","1px solid red"); 
				           $("#toDate").html("<font color='red'>To Entry Date should not be empty</font>"); 
				           }
				         }
				     });
				     $("#txtToDate").datepicker({
				         changeYear: true,
				         changeMonth:true, 
				         dateFormat:'yy-mm-dd',
				         onSelect: function(selected) {
				            $("#txtFromDate").datepicker("option","maxDate", selected)
				            $("#txtToDate").css("border","1px solid #000"); 
					        $("#toDate").html('');
					        var fromDate = $("#txtFromDate").val();
				        	 console.log("fromDate ["+fromDate+"]");
				        	 if(  fromDate === undefined || fromDate == null || fromDate.length <= 0 ){
						           $("#txtFromDate").css("border","1px solid red"); 
						           $("#toDate").html("<font color='red'>From Entry Date should not be empty</font>"); 
						      }
				         }
				     });
				     
				    
	var vehType="${vehType}";	
	if('2'==vehType){
			var oTable = $('#myDatatable').DataTable( { 
				dom: 'Blfrtip',			
				"bFilter": false,
			        buttons: [
			       {
			           extend: 'pdf',
			           footer: true,
			           title: 'Data Entry Report',
			           filename: 'Excavators',
			           orientation:'landscape',
			           //Customize
			               
			           customize: function ( doc ) {
		                    doc.content.splice( 0,1);
		                    
		                    var logo = 'data:image/png;base64,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';
							doc.pageMargins = [30,100,20,30];
							// Set the font size fot the entire document
							doc.defaultStyle.fontSize = 10;
							// Set the fontsize for the table header
							doc.styles.tableHeader.fontSize = 10;
		                    doc['header']=(function() {
								return {
									columns: [
										{
											image: logo,
											width: 150,
											margin: [10,0]	
																					
										},
										{
											alignment: 'left',
											italics: false,
											text: 'Excavators',
											fontSize: 18,
											margin: [140,0]
										},
										{																					
											text:'Start Entry Date : '+ $('#txtFromDate').val() +'\n'+
												'To Entry Date : '+ $('#txtToDate').val()+'\n'+ 
													'Vendor : '+ $('#vendorselecter :selected').text()+'\n'+ 
													'Site : '+ $('#siteselecter :selected').text()+'\n'+
													'Vehicle Type : '+ $('#selectervtype :selected').text()+'\n'+
													'Vehicle Number : '+ $('#VehicleNumberselecter :selected').text(),
											fontSize: 10,
											margin: [10,0]
										}
										
									],
									margin: 20 
									 
									    
									 
									  
								}
							
			           });
			           },
			           
			           exportOptions: {
			                columns: [0,1,2,3,4,5,6,7,8,9,10,12,13]
			            }
			       },
			       {
			           extend: 'excel',
			           footer: true,
			           title: 'Data Entry Report',
			           filename: 'Excavators',
			           orientation:'landscape',
			           
			           exportOptions: {
			                columns: [0,1,2,3,4,5,6,7,8,9,10,12,13]
			            }
			       }
			    ],
			    "order": [[ 0, "desc" ]],
			    "footerCallback": function ( row, data, start, end, display ) {
			                var api = this.api(), data;
			    
			                // Remove the formatting to get integer data for summation
			                var intVal = function ( i ) {
			                    return typeof i === 'string' ?
			                        i.replace(/[\$,]/g, '')*1 :
			                        typeof i === 'number' ?
			                            i : 0;
			                };
			     
			                var list=[7,10,11,12,13];
			                for(var i=0;i<list.length;i++){
			                	  total = api
				                    .column( list[i] )
				                    .data()
				                    .reduce( function (a, b) {
				                    	//alert("1"+a);
				                    //	alert("b"+b);
				                    	if(b.includes("&nbsp;")){
				                    	b=b.substring(0,b.indexOf("&nbsp;"));
				                    	}
				                    	//alert("3"+a);
				                    	//alert("4"+b);
				                    	
				                        return intVal(a) + intVal(b);
				                    }, 0 );
				                
				                // Distance over this page
				                pageTotal = api
				                    .column( list[i], { page: 'current'} )
				                    .data()
				                    .reduce( function (a, b) {
				                    	if(b.includes("&nbsp;")){
				                    	b=b.substring(0,b.indexOf("&nbsp;"));
				                    	}
				                        return intVal(a) + intVal(b);
				                    }, 0 );
				                // Update footer
				                //alert(total);
				              // alert(pageTotal);
				                $( api.column( list[i] ).footer() ).html(
				                    ''+pageTotal);
				            }
			              }
			        } );
	}
	else if('1'==vehType){
		var oTable = $('#myDatatable').DataTable( { 
			dom: 'Blfrtip',			
			"bFilter": false,
		        buttons: [
		       {
		           extend: 'pdf',
		           footer: true,
		           title: 'Data Entry Report',
		           filename: 'Trippers',
		           orientation:'landscape',
		           
		           customize: function ( doc ) {
	                    doc.content.splice( 0,1);
	                    
	                    var logo = 'data:image/png;base64,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';
						doc.pageMargins = [30,100,20,30];
						// Set the font size fot the entire document
						doc.defaultStyle.fontSize = 10;
						// Set the fontsize for the table header
						doc.styles.tableHeader.fontSize = 10;
	                    doc['header']=(function() {
							return {
								columns: [
									{
										image: logo,
										width: 150,
										margin: [10,0]																					
									},
									{
										alignment: 'left',
										italics: false,
										text: 'Trippers',
										fontSize: 18,
										margin: [140,0]
									},
									{																					
										text:'Start Entry Date  : '+ $('#txtFromDate').val() +'\n'+
											 'To Entry Date     : '+ $('#txtToDate').val()+'\n'+ 
											 'Vendor            : '+ $('#vendorselecter :selected').text()+'\n'+ 
											 'Site              : '+ $('#siteselecter :selected').text()+'\n'+
											 'Vehicle Type      : '+ $('#selectervtype :selected').text()+'\n'+
											 'Vehicle Number    : '+ $('#VehicleNumberselecter :selected').text(),
										fontSize: 10,
										margin: [10,0]
									}
									
								],
								margin: 20 
								  
							}
						
		           });
		           },
		           
		           
		           exportOptions: {
		                columns: [0,1,2,3,4,5,6,7,8,9,10,11,13]
		            }
		       },
		       {
		           extend: 'excel',
		           footer: true,
		           title: 'Data Entry Report',
		           filename: 'Tripper',
		           orientation:'landscape',
		           
		           exportOptions: {
		                columns: [0,1,2,3,4,5,6,7,8,9,10,11]
		            }
		       }
		    ],
		    "order": [[ 0, "desc" ]],
		    "footerCallback": function ( row, data, start, end, display ) {
		                var api = this.api(), data;
		    
		                // Remove the formatting to get integer data for summation
		                var intVal = function ( i ) {
		                    return typeof i === 'string' ?
		                        i.replace(/[\$,]/g, '')*1 :
		                        typeof i === 'number' ?
		                            i : 0;
		                };
		     
		                var list=[7,10,11,12,13];
		                for(var i=0;i<list.length;i++){
		                	  total = api
			                    .column( list[i] )
			                    .data()
			                    .reduce( function (a, b) {
			                    	//alert("1"+a);
			                   //	alert("b"+b);
			                    	if(b.includes("&nbsp;")){
			                    	b=b.substring(0,b.indexOf("&nbsp;"));
			                    	}
			                    	//alert("3"+a);
			                    	//alert("4"+b);
			                    	
			                        return intVal(a) + intVal(b);
			                    }, 0 );
			                
			                // Distance over this page
			                pageTotal = api
			                    .column( list[i], { page: 'current'} )
			                    .data()
			                    .reduce( function (a, b) {
			                    	if(b.includes("&nbsp;")){
			                    	b=b.substring(0,b.indexOf("&nbsp;"));
			                    	}
			                        return intVal(a) + intVal(b);
			                    }, 0 );
			                // Update footer
			                //alert(total);
			              // alert(pageTotal);
			                $( api.column( list[i] ).footer() ).html(
			                    ''+pageTotal 
			                );
			            }
		              }
		        } );
}
			    
			
			// Simply get the sum of a column
			  //var table = $('#example').DataTable();
			  

			$('#expencelist').hover(function() {
				getExpense();
		});
		
			$('#selectervtype').change(function() {
				getVehicles();	
			}); 
			$('#vendorselecter').change(function() {
				getVehicles();	
			});
		});/*END Ready Function*/
		function getExpenseDtl(id){
			getExpense(id)
		}
		function getExpense(id){
			console.log(id);
	    	var expences = $("#inphidden_"+id).val();    
	    	console.log(expences);
	    	expences = ""==expences ?0:expences; 
	    		$.ajax({
	    			type : "get",
	    			url : '${pageContext.request.contextPath}/dataEntry/GetExpenseList/'+expences+'.do',
	    			cache : false,
	    			success : function(response) {
	    				response = JSON.parse(response);
	    				//alert(response);
	    				var s;
	    				for(var i=0;i<response.length;i++){
	    					s+="<option> "+response[i].expenses.expenseName+"=>"+response[i].expenseAmount+"</option>";
	    				}				
	    				$("#expencelist_"+id).html(s);
	    			},
	    			error : function(xhr, ajaxOptions, thrownError) {
	    				//alert(thrownError);		
	    			}
	    		});
	    	};
		/*  $(".remarover").mouseover(function(){
	    	    $(".remarover").css("background-color", "yellow");
	    	}).mouseout(function(){
	    		 $("#remarover").css("background-color", "red");
	        });; */
	        function mouseOverWidth(id) {
	        	   var cellText = $("#"+id).html();   
				console.log(cellText);
			};
			
	        function getVehicles(){
	        	var vehicleType = $('.vehicleType').val();
	        	var vendorId = $('.vendorcls').val();    	
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
	        				var s="<option value='0'>--ALL--</option>";
	        				for(var i=0;i<response.length;i++){
	        					s+="<option value='"+response[i].vehicleId+"'> "+response[i].vehiclesNumber+"</option>";
	        				}				
	        				
	        				$(".vehicleNum").html(s);
	        			},
	        			error : function(xhr, ajaxOptions, thrownError) {
	        				alert(thrownError);		
	        			}
	        		});
	        	};
	</script>
</body>
</html>
