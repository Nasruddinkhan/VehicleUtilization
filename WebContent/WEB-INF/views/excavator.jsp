<h4 style="display: block; text-align:center; color: #1b85ae;">Excavators</h4>
<table id="myDatatable"
	class="display table table-bordered table-striped table-hover">
	<thead>
		<tr>
			<th><span class="spn">Challan No.</span></th>
			<th><span class="spn">Entry Date</span></th>
			<th><span class="spn">Vendor</span></th>
			<th><span class="spn">Pocklane</span></th>
			<th><span class="spn">Site</span></th>
			<th><span class="spn">Start Time</span></th>
			<th><span class="spn">Close Time</span></th>
			<th><span class="spn">Total Time</span></th>
			<th><span class="spn">Work Type</span></th>
			<th><span class="spn">Vehicle No.</span></th>
			<!-- <th><span class="spn">VendorType</span></th>  -->
			<th><span class="spn">&nbsp;&nbsp;<i class="fa fa-inr"></i><br>Rate&nbsp;</span></th>
		
			<!-- <th><span class="spn">Diesel<br>&nbsp;(Ltr)
			</span></th>
			 --><!-- <th><span class="spn">Avg</span></th> -->
			<th><span class="spn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-inr"></i>Expense<br>&nbsp;</span></th>
			<th style="display:none"><span class="spn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-inr"></i>Expense<br>&nbsp;</span></th>
			<th><span class="spn">&nbsp;&nbsp;&nbsp;<i class="fa fa-inr"></i>Total&nbsp;</span></th>
			<th><span class="spn">Remarks</span></th>
		</tr>
	</thead>
	<tbody>
		<%int i=1; %>
		<c:forEach items="${dataEntryList}" var="dataEntry" varStatus="i">
			<tr>
				<td><a class="btn1" data-toggle="modal" data-target="#myModal"
												href="${pageContext.request.contextPath}/dataEntry/viewDataEntry/${dataEntry.dataEntryId}.do">
												
												
				<fmt:formatNumber pattern = "0000"  type = "number" value = "${dataEntry.dataEntryId}"  /></a></td>
				<td><fmt:formatDate pattern="dd-MMM-yyyy"
						value="${dataEntry.entryDate}" /></td>
		
				<td ><c:out value="${dataEntry.vendorType.vendor_Name}" /></td>
				<td ><c:out value="${dataEntry.excavatorType.type}" /></td>
				<td><c:out value="${dataEntry.site.siteName}" /></td>
				<td><c:out value="${dataEntry.time}" /></td>
				<td><c:out value="${dataEntry.endTime}" /></td>
				<td style="text-align:right;"><c:out value="${dataEntry.difftime}" /></td>
				<td><c:out value="${dataEntry.workType}" /></td>
				<td><c:out value="${dataEntry.vehicle.vehiclesNumber}" /></td>
				<%-- <td><c:out value="${dataEntry.vendor.Vendor_Name}" /></td> --%>
				<td style="text-align:right;"><c:out value="${dataEntry.rate}" /></td>
			
				<%-- <td><c:if test="${dataEntry.diesel!=''}">
						<c:out value="${dataEntry.diesel}" />&nbsp;
										 </c:if></td>
				 --%><%--  <td>
          								 <c:if test="${dataEntry.avg!=''}">										 
											<c:choose>
											<c:when test="${dataEntry.vehicleType.m_Id==2}">  
											 <c:out value="${dataEntry.avg}" />&nbsp;<c:if test="${dataEntry.avg!=null}">min/ltr</c:if>
											 </c:when>
										 <c:otherwise><c:out value="${dataEntry.avg}" />&nbsp;<c:if test="${dataEntry.avg!=null}">K.m</c:if></c:otherwise>
										 </c:choose>
										 </c:if>		
										  </td>
									 --%>
				              <td style="text-align:right;">
				                <c:if test="${dataEntry.expense!=''}">
					         	<c:out value="${dataEntry.expense}" />
						      </c:if> &nbsp;<input type="hidden" id="inphidden_${i.index+1}" value="${dataEntry.dataEntryId}" /> 
						      <select id="expencelist_${i.index+1}" onmouseover="getExpenseDtl(${i.index+1})">
						        <option></option>
				              </select>
				              </td>
				             <td style="display:none"><c:out value="${dataEntry.expense}" /></td>
				      <td style="text-align:right;">
				      <c:choose>
						<c:when test="${dataEntry.vehicleType.m_Id==2}">
							<fmt:formatNumber type="number" value="${dataEntry.rate * (dataEntry.difftime/60)}" maxFractionDigits="2" />
						</c:when>
						<c:otherwise>
						<c:out value="${dataEntry.rate}" />&nbsp;
						</c:otherwise>
					</c:choose>
					</td>


				<td id="remarover<%=i %>"
					onmouseover="mouseOverWidth('remarover<%=i %>');"
					style="padding: 4px;">
					<div style="overflow: auto; max-height: 100px; max-width: 100px;">
						<c:out value="${dataEntry.remarks}" />
					</div>
				</td>
				<%i++; %>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
		<th colspan="1" style="text-align: right">Total:</th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th style="text-align:right;"></th>
			<th style="text-align:right;"></th>
			<th style="text-align:right;"></th>
			<th style="text-align:right;"></th>
			<th style="text-align:right;"></th>
			<th style="display:none;text-align:right;"></th>
			<th style="text-align:right;"></th>
			<th style="text-align:right;"></th>
			
		</tr>
	</tfoot>
</table>