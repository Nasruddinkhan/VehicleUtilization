<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>

<body>

<h3>
    Transaction File Upload  
</h3>
<div id="container">
<%-- <form id="entities" action="${pageContext.request.contextPath}/upload/action.do" > --%>
<br/>
<h4>Please select action</h4>
<div class="form-group col-lg-4">
<select name="action" class ="required form-control">
<option value="1">Upload New Transaction File</option>
<option value="2">View Uploaded history</option>
</select>
</div>
<input type="submit"  class="btn btn-primary"/>

</form>
</div>
</body>
</html> 