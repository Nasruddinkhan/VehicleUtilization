<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendor/datatableJsCss/css/buttons.dataTables.min.css"
	rel="stylesheet">
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
<body>
	<c:choose>
		<c:when test="${empty modify || modify=='N'}">
			<h3>Fetch Details</h3>
			<form method="GET" id="userForm"
				action="${pageContext.request.contextPath}/print/search.do">

				<table class="table table-bordered table-striped table-highlight">
					<tr>
						<td style="width:30%">Applicant Id: <input name="id" type="text"
							class="required" value="${id}" />
						</td>
						<td style="width:30%">Type: <select name="type" class="required">

								<option value="">--select--</option>
								<c:choose>
									<c:when test="${detailInsert.applicantType=='Minor'}">
										<option value="Minor" selected="selected">Minor</option>
									</c:when>
									<c:otherwise>
									<option value="Minor">Minor</option>										
									</c:otherwise>
								</c:choose>
									<c:choose>
									<c:when test="${detailInsert.applicantType=='Individual'}">
										<option value="Individual" selected="selected">Individual</option>
									</c:when>
									<c:otherwise>
									<option value="Individual">Individual</option>										
									</c:otherwise>
								</c:choose>
									<c:choose>
									<c:when test="${detailInsert.applicantType=='Company'}">
										<option value="Company" selected="selected">Company</option>
									</c:when>
									<c:otherwise>
									<option value="Company">Company</option>										
									</c:otherwise>
								</c:choose>
						</select>
						</td>
						<td><input type="submit" style="margin-left: 36px;"
							class="button_style btn btn-primary btn_validate" value="Search" /></td>
					</tr>
				</table>

			</form>
		</c:when>
		<c:otherwise>
			<h3>View Applicant</h3>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${!empty detailInsert }">



			<form:form method="POST" id="form"
				action="${pageContext.request.contextPath}/print/submitDetails.do"
				commandName="detailInsert" enctype="multipart/form-data">

			<table class ="table table-bordered " style="width:60%">
					<tr>
						<th width=15%>Share Holder Name:</th>
						<td width=10%>${detailInsert.applicantName}</td>
					</tr>
					<tr>
						<form:hidden path="applicantNIN" />
						<th>Share Holder Number:</th>
						<td>${detailInsert.applicantNIN}</td>
					</tr>
				</table>

				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Please update below mentioned
							information,if Required</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-6 col-sm-6">
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Name</label>
								<form:input path="applicantName"
									class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>ID /CR No</label>
								<form:input path="applicantID"
									class="form-control input-sm required" />

							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Share Holder Type</label>
								<form:radiobutton path="applicantType" class="required"
									value="Minor" />
								Minor
								<form:radiobutton path="applicantType" class="required"
									value="Individual" />
								Individual
								<form:radiobutton path="applicantType" class="required"
									value="Company" />
								Company

							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Guardian ID</label>

								<form:input path="guardianID"
									class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Passport No.</label>
								<form:input path="passportNo"
									class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>DOB /Nationality</label>
								${detailInsert.dob}/${detailInsert.nationality}
								<form:hidden path="dob" />
								<form:hidden path="nationality" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Post Code</label>
								<form:input path="pastCode"
									class="form-control input-sm required textbox" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>P.O.Box</label>
								<form:input path="poBox" class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>City / Country</label>
								<form:input path="city" class="form-control input-sm required" />
								<form:input path="country"
									class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Telephone</label>
								<form:input path="telephone"
									class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Fax</label>
								<form:input path="fax" class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>E-Mail</label>
								<form:input path="email" class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-12 col-sm-12">
								<label>Merge NINs</label> <br />
							</div>
							<div class="form-group-sm col-md-3 col-sm-3">
								NIN1
								<form:input path="nin1" class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-3 col-sm-3">
								NIN2
								<form:input path="nin2" class="form-control input-sm required" />
							</div>
							<div class="form-group-sm col-md-3 col-sm-3">
								NIN3
								<form:input path="nin3" class="form-control input-sm required " />
							</div>
							<div class="form-group-sm col-md-3 col-sm-3">
								NIN4
								<form:input path="nin4" class="form-control input-sm required " />
							</div>
						</div>
					</div>
				</div>


				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Divident Payment Details</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-12 col-sm-12">
							<div class="form-group col-md-6 col-sm-6">
								<label> Account No.</label>
								<form:input path="accountNumber" class="required"/>
							</div>
							<div class="form-group col-md-6 col-sm-6">
								<label>Account Name.</label>
								<form:select path="accountName" id="accountName" class="required">
									<option value="">--SELECT--</option>
									<form:options items="${accountTypeList}"
										itemLabel="accountTypes.accounType"
										itemValue="accountTypes.accounType" />
								</form:select>
							</div>
						</div>
						<div class="col-md-12 col-sm-12">
							<div class="form-group col-md-6 col-sm-6">
								<label>Bank</label>
								<form:input path="bank" class="required" />
							</div>
							<div class="form-group col-md-6 col-sm-6">
								<label> Branch.</label>
								<form:input path="branch" class="required" />
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">* For Foreign Account Only</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-12 col-sm-12">
							<div class="form-group col-md-4 col-sm-4">
								<label>SWIFT</label>
								<form:input path="swift" class="required" />
							</div>
							<div class="form-group col-md-4 col-sm-4">
								<label>IBAN.</label>
								<form:input path="iban" id="iban" class="required"/>
							</div>
							<div class="form-group col-md-4 col-sm-4">
								<label>Currency</label>
								<form:input path="currency" class="required" />
							</div>
						</div>
					</div>
				</div>



				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Signature</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-12 col-sm-12">
							<div class="form-group col-md-6 col-sm-6">
								<form:hidden path="signatureFile" />
								<img style="width: 120px; height: 80px;" alt="img"
									src="data:image/jpeg;base64,${detailInsert.signatureFile}" />
							</div>
						</div>
					</div>
				</div>





				<c:choose>
					<c:when test="${detailInsert.status=='Initiated'}">
						<div class="center">
							<input id="submit" type="submit" value="Submit"
								class="btn btn-primary btn_validate" disabled="disabled" />
						</div>
					</c:when>
					<c:otherwise>
						<div class="center">
							<input id="submit" type="submit" value="Submit"
								class="btn btn-primary btn_validate" />
						</div>
					</c:otherwise>
				</c:choose>
			</form:form>
			</div>
			</div>
			</div>
			</div>
			</div>
		</c:when>
		<c:otherwise>
			<c:if test="${!empty id}">
         No data Found
      </c:if>
		</c:otherwise>
	</c:choose>

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
		
		$("#accountName").on("change",function(){
			var accountName= $("#accountName").val()
			if( accountName != 0 ){
				//alert(accountName);		
				getIban(accountName);
		}
			else{
				 $('#iban').val("");			 
			}
		});
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

		 */} );
	

	function getIban(accountName){
		var base_url = window.location.origin;	
		if(base_url==undefined){
			base_url=	window.location.protocol+'//'+window.location.hostname+':'+window.location.port;
		}
		
		base_url = base_url+"<%=request.getContextPath()%>";
			//alert(base_url);
			var id = $("#id").val();
			id = "" == id ? 0 : id;

			$.ajax({
				type : "post",
				url : base_url + "/print/getIBAN.do",
				cache : false,
				data : 'accountType=' + accountName,
				success : function(response) {
					//alert(response);
					$('#iban').val(response);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#iban').val("");
				}
			});

		}
	</script>
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