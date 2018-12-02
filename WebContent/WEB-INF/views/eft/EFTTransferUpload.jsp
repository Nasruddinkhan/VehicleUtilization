<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>
<html>
<head>
<script src="${pageContext.request.contextPath}/dist/js/formValidation.js"></script>
<style>

</style>
</head>
<body>
<script>
$(document).ready(function(){	
	$('#formSubmit').on('click',function(){
		$('#message').text('');
		$('#message').html('');
		$('#errorTable').html('');
	if(''!=$('#file').val()){		 
		 checkFileName();
	}
	else{
		$('#message').text("Please select file").css("color", "red");	 
	}
	});
});


function checkFileName(){
	var base_url = window.location.origin;	
	if(base_url==undefined){
		base_url=	window.location.protocol+'//'+window.location.hostname+':'+window.location.port;
	}
	
	base_url = base_url+"<%=request.getContextPath()%>";
	var filename = $("#file").val();
	filename = filename.substring(filename.lastIndexOf("\\")+1);
		//alert(filename);
		$.ajax({
			type : "post",
			url : base_url + "/eft/checkFileName.do",
			cache : false,
			data : 'fileName=' + filename,
			success : function(response) {
				
				if(''==response){
					//alert();
					 var div = document.getElementById('modal-content');
					 $(".modal-body").css("display", "block");
					 //<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
					 //$('.modal-content').
					 $('#formSubmit').attr('disabled','disabled');
					// $('.modal-body').fadeOut("slow",0.2);

					$('#form').submit();
					
				}else{
					$('#message').text(response).css("color", "red");
				}},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#message').text("Error in validation File Name");				
			}
		});

	}

</script>
<h3>
    External Transaction Upload
</h3>


<div id="container">
<%-- <form method="post" action="${pageContext.request.contextPath}/upload/form.do" enctype="multipart/form-data" >
<p style="color:red;">${Message}</p>
<p style="color: green;text-align: left;">${successMessage}</p>
<span>Please Choose file</span>
<div class="form-group col-lg-4">
<div class="form-control"> 
 <label for="email">Please Choose file :</label>
<input type="file" name="file" accept=".xls,.xlsx"/>
</div>
</div>
<input type="submit"  class="btn btn-primary"/>
<a class="btn btn-primary" href="${pageContext.request.contextPath}/upload/file.do">Back</a>
</form> --%>
</div>

<form id="form" class="form-inline" method="post" action="${pageContext.request.contextPath}/eft/form.do" enctype="multipart/form-data" >


<p style="color: green;text-align: left;" id="message">${successMessage}</p>

    <div class="form-group">
      <label for="email">Please Choose file:   </label>
      <input type="file" class="form-control" id="file" name="file" placeholder="File" accept=".xls,.xlsx"/>
    </div>
 
    <input id="formSubmit" type="button" class="btn btn_validate btn-primary " value="Submit"/>
  </form>


        <div class="modal-body" style="display: none; margin-top: -50px;
    margin-left: 100px;" >
         <img alt="" src="${pageContext.request.contextPath}/img/file_uploading.gif">
        </div>       


   <c:if test="${!empty Messages }">
   <div style="height:5%;width:5%;margin-top:3%;margin-bottom:3%;"></div>
     <table class="table" id="errorTable">
     <tr>
    <th>Sr No.</th>
    <th>Error Message</th>     
     </tr>
<c:forEach items="${Messages}" var="m" varStatus="idx">
<tr><td><c:out value="${idx.index +1 }"></c:out></td>
<td><p style="color:red;"><c:out value="${m}"></c:out></p></td>
</tr>
</c:forEach>
     </table>
</c:if>


</body>
</html>
