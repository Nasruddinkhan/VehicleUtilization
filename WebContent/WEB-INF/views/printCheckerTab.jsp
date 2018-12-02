<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <div class="wrapper">
	<div class="container">
		<div class="bs-example bs-example-tabs" role="tabpanel"
			data-example-id="togglable-tabs">
			<ul id="myTab" class="nav nav-tabs nav-tabs-responsive"
				role="tablist">
				<li role="presentation" class="active"><a href="#home"
					id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="true"> <span class="text">File Upload</span>
				</a></li>
				<li role="presentation" class="next"><a href="#profile"
					role="tab" id="profile-tab" data-toggle="tab"
					aria-controls="profile"> <span class="text">View
							Uploaded History</span>
				</a></li>
			</ul>
		</div>
	</div>
</div>
 -->
 
 <!DOCTYPE html>
<html>
<head>
<style>
body {font-family: "Lato", sans-serif;}

/* Style the tab */
div.tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
div.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of buttons on hover */
div.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
div.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
</style>
</head>
<body>
<input type="hidden" id="mode" value="${mode=='SEARCH'}" />
<div class="tab">
  <button class="tablinks" onclick="openTab(event, 'pending')">Pending Application</button>
  <button class="tablinks" onclick="openTab(event, 'verified');">Verified Application</button>  
</div>

<div id="pending" class="tabcontent" style="display: block">


 <%@ include file="pendingApplicants.jsp"%>
</div>


<div id="verified" class="tabcontent">
  <%@ include file="verifiedApplicants.jsp"%>
  </div>

<script>
$(document).ready(function(){
		if($('#mode').val()=="true"){			
			openTab(event, 'fileUploadHist');
		}
		else{			
			openTab(event, 'fileUpload');
		}
		});

function openTab(evt, cityName) {
	if('fileUploadHist'==cityName){
		//location.href='${pageContext.request.contextPath}/upload/fileUploadHistory.do';
	}
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}
</script>
     
</body>
</html> 
 