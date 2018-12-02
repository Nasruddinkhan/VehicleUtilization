<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
.spn{
font-size:15px;
font-weight: bold;
color: green;
}
@media screen {
  #printSection {
      display: none;
      
  }
}

@media print {
  body * {
    visibility:hidden;
  }
  #printSection, #printSection * {
    visibility:visible;
  }
  #printSection {
     position:absolute;
    width:18cm;
    margin-left:2cm;
    left:0;
    top:0;
   
  }
}
</style>
<script type="text/javascript">
document.getElementById("btnPrint").onclick = function () {
    printElement(document.getElementById("printThis"));
}

function printElement(elem) {
    var domClone = elem.cloneNode(true);
    
    var $printSection = document.getElementById("printSection");
    
    if (!$printSection) {
        var $printSection = document.createElement("div");
        $printSection.id = "printSection";
        document.body.appendChild($printSection);
    }
    
    $printSection.innerHTML = "";
    $printSection.appendChild(domClone);
    window.print();
}
      
      </script>
<body>
<div>
      <form:form commandName="dataEntry">
      <div class="modal-header">
          <a class="btn btn-danger close" href="${pageContext.request.contextPath}/report/reportList.do">&times;</a>
          
          <a  class="btn btn-default" id="btnPrint"><span class="glyphicon glyphicon-print"></span></a>
        </div>
        <div id="printThis">
        <img src="${pageContext.request.contextPath}/suhel/navdurga.jpg" alt="Mountain View" style="height: 40px;width: 140px;padding-left: 16px;padding-top: 10px;" >
        <h4 style="margin-left:250px;">${ dataEntry.vehicleType.m_Name}</h4>
        <div class = "table-responsive">
          <table class="table table-striped table-highlight">
          <tr>
          <td>Challan No.</td>
          <td> <fmt:formatNumber pattern = "0000"  type = "number" value = "${dataEntry.dataEntryId}"  />
          </tr>
          <tr>
          <td>Vendor:</td>
          <td>${dataEntry.vendorType.vendor_Name}</td>
          </tr>
          
          <tr>
          <td>Entry Date:</td>
          <td>${dataEntry.entryDate}</td>
          </tr>
          <c:choose>
          <c:when test="${dataEntry.vehicleType.m_Id==2}"><tr>
          <td>Time Consumed:</td>
          <td>${dataEntry.difftime} Minute(s)</td>
          </tr> </c:when>          
          <c:otherwise></c:otherwise>
          </c:choose>
          
          <tr>
          <td>Site Name:</td>
          <td>${dataEntry.site.siteName}</td>
          </tr>
          <tr>
          <td>VehicleType:</td>
          <td>${ dataEntry.vehicleType.m_Name}</td>
          </tr>
          <c:choose>
          <c:when test="${dataEntry.vehicleType.m_Id==2}"><tr>
          <td>Excavator Type</td>
          <td>${dataEntry.excavatorType.type}</td>
          </tr> </c:when>          
          <c:otherwise></c:otherwise>
          </c:choose>
           <tr>
            <c:choose>
          <c:when test="${dataEntry.vehicleType.m_Id==2}"><tr>
          <td>Work Type:</td>
          <td>${ dataEntry.workType}</td>
          </c:when>          
          <c:otherwise></c:otherwise>
          </c:choose>
          
          </tr>
          <tr>
          <td>Vehicle Number:</td>
          <td>${dataEntry.vehicle.vehiclesNumber}</td>
          </tr>       
          <c:choose>
          <c:when test="${dataEntry.vehicleType.m_Id==2}">
          <tr></tr>
          <tr></tr>
          </c:when>          
          <c:otherwise>
          <tr>
          <td>Loading/Unloading</td>
          <td>${dataEntry.loading_unloading}</td>
          </tr>
           <tr>
          <td>Loading From:</td>
          <td>${dataEntry.loading}</td>
          </tr>
          <tr>
          <td>UnLoading To:</td>
          <td>${dataEntry.unloading}</td>
          </tr>
           <tr>
          <td>Distance:</td>
          <td>${dataEntry.distance} k.m</td>
          </tr>
          </c:otherwise>
          </c:choose>
          <tr>
          <td>Rate:</td>
          <td>${dataEntry.rate} <i class="fa fa-inr"></i></td>
          </tr>
          <%-- <tr>
          <td>Diesel:</td>
          <td>${dataEntry.diesel} ltr</td>
          </tr> --%>
          <%-- <tr>
          <td>Average:</td>
          <td>${dataEntry.avg} <c:choose>
          <c:when test="${dataEntry.vehicleType.m_Id==2}">
          <c:if test="${dataEntry.avg!=null}">min/ltr</c:if>
         </c:when>          
          <c:otherwise><c:if test="${dataEntry.avg!=null}"> km/ltr</c:if>
          </c:otherwise>
          </c:choose>
           </td>
          </tr>
       --%>
          <%-- <tr>
          <td>Toll:</td>
          <td>${dataEntry.toll}</td>
          </tr> --%>
          <tr><td>Expense:</td>
          <td>
          <table>
          
          <c:forEach items="${expenseList}" var="exp">
          <tr>
          <td>${exp.expenses.expenseName }:
          </td><td>${exp.expenseAmount } <i class="fa fa-inr"></i>
          </td>
          </tr>
          </c:forEach>
          <tr>          
          <td>Total:</td>
          <td>${dataEntry.expense} <i class="fa fa-inr"></i></td>
          </tr>
          </table>
          <tr>
          <td>Remarks:</td>
          <td>${dataEntry.remarks}</td>
          </tr>
          <tr>
          <td>CreatedBy : ${dataEntry.getCreatedBy()}</td>
          <td>CreatedDate : <fmt:formatDate pattern = "dd-MMM-yyyy '_' hh:mm a" value ="${dataEntry.getCreatedDate()}"/></td>
          </tr>
          </table>
          </div>
        </div>
        </form:form>
        </div>   
      <div class="modal-footer">
          <a class="btn btn-default" href="${pageContext.request.contextPath}/report/reportList.do">Close</a>
          
        </div>
      
</body>