<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
	<form:form commandName="users">
	<div class="modal-header">
          <a class="btn btn-danger close" href="${pageContext.request.contextPath}/user/users.do">&times;</a>
          <h4 class="modal-title">${ users.userName}</h4>
          </div>
		<div class="table-responsive ">
			<table class="table table-striped table-highlight">
				<tr>
					<th> ID</th>
					<td>${ users.id}</td>
				</tr>
				<tr>
					<th>Login ID</th>
					<td>${ users.loginId}</td>
				</tr>
				<tr>
					<th>User Name</th>
					<td>${ users.userName}</td>
				</tr>
				

			<%-- 	<tr>
					<th>Email</th>
					<td>${ users.email}</td>
				</tr>
				<tr>
					<th>Date Of Birth</th>
					<td>${ users.dateOfBirth}</td>
				</tr>
 --%>
				<%--    <tr>
			        <td>Customer</td>
			        <td><form:select path="customer.customerId">
			         <option value="0">--SELECT--</option>
			        <form:options items="${customerList}"  itemValue="customerId" itemLabel="customerName"/>
			        </form:select>
			    </tr> --%>
				
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
          <a class="btn btn-default" href="${pageContext.request.contextPath}/user/users.do">Close</a>
          </div>
</body>
