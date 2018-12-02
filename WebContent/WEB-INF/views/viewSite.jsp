<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>

	<form:form commandName="sites">
	<div class="modal-header">
          <a class="btn btn-danger close" href="${pageContext.request.contextPath}/site/sites.do">&times;</a>
          <h4 class="modal-title">${ sites.siteName}</h4>
        </div>
		<div class="table-responsive ">
			<table class="table table-striped table-highlight">
				
				<tr>
					<th>Site ID</th>
					<td>${ sites.siteId}</td>
				</tr>
				
				<tr>
					<th>Site Name</th>
					<td>${ sites.siteName}</td>
				</tr>
				
				<tr>
					<th>Site Location</th>
					<td>${ sites.siteLocation}</td>
				</tr>
				
				<tr>
					<th>Construction Type</th>
					<td>${ sites.construction_type}</td>
				</tr>
				<tr>
					<th>Start Project Date</th>
					<td>${ sites.pro_Start_Date}</td>
				</tr>
				 <tr>
			        <th>End Project Date</th>
			         <td>${ sites.pro_End_Date}</td>
			    </tr>
				
			<%-- 	<tr>
					<td>Image</td>
					<td><c:choose>
							<c:when test="${!empty users.imageFile}">
								<img style="width: 180px; height: 180px;" alt="img"
									src="data:image/jpeg;base64,${users.imageFile}" />
							</c:when>
							<c:otherwise>
	Image Not Available.
	</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>File</td>
					<td> <c:choose>
							<c:when test="${!empty users.fileName}">
								<a href="${pageContext.request.contextPath}/user/download/${users.id}.do">${users.fileName}</a>
							</c:when>
							<c:otherwise>
	File Not Available.
	</c:otherwise>
						</c:choose>

					</td>
				</tr>
 --%>
				
			</table>
		</div>
	</form:form>
	 <div class="modal-footer">
          <a class="btn btn-default" href="${pageContext.request.contextPath}/site/sites.do">Close</a>
        </div>
</body>
