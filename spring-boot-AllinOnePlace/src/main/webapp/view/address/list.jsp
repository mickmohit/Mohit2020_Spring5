<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default">
	<div class="panel-heading">
		<strong>
			<span class="glyphicon glyphicon-list"></span> Address List
		</strong>
		<div class="btn-group pull-right">
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="addForm('address')" title="Add New Address">
				<span class="glyphicon glyphicon-plus-sign"></span>
			</a>
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="refresh('address')" title="Refresh Address">
				<span class="glyphicon glyphicon-refresh"></span>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-bordered table-condensed table-hover table-striped">
			<thead>
				<tr>
					<th>Country</th>         
					<th>State</th>
					<th>City</th>
					<th>Line</th>
					<th>Pin Code</th>
					<th>User</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>

<tbody>
				<c:choose>
					<c:when test="${addresses.size() > 0}">
<c:forEach items="${addresses}" var="address">
<tr>
<td>${address.country}</td>
<td>${address.state}</td>
<td>${address.city}</td>
<td>${address.addressLine}</td>
<td>${address.pinCode}</td>
<td>${address.user.userName}</td>
<td><a href="javascript:void(0);" onclick="editForm('address', '${address.addressId}')"><span class="glyphicon glyphicon-edit"></span></a></td>
<td><a href="javascript:void(0);" onclick="deleteData('address', '${address.addressId}')"><span class="glyphicon glyphicon-trash"></span></a></td>
</tr>
</c:forEach>

			</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="8">No Addresses available</td>
						</tr>
					</c:otherwise>
				</c:choose>
</tbody>

</table>
</div>

<c:if test="${addresses.size() > 0}">
		<div class="panel-footer">
			Showing ${number+1} to ${size} of ${totalElements}
			<ul class="pagination pull-right" style="margin:-7px;">
			
			<!---------------Older way of Pagination ------------------->
				<%-- <c:forEach begin="0" end="${totalPages-1}" var="page">
					<li>
						<a href="javascript:void(0);" onclick="list('address', ${page}, ${size})">${page+1}</a> //Older method way of Pagination, comment this
						<a href="javascript:void(0);" onclick="list('address', ${page})">${page+1}</a> 
					</li>
				</c:forEach> --%>
				
				<!-- section-1 -->	
		<c:choose>
		<c:when test="${current == 1}">
			<li class="disabled"><a href="javascript:void(0);">First</a></li>
			<li class="disabled"><a href="javascript:void(0);">Prev</a></li>
		</c:when>
		<c:otherwise>
			<li ><a href="javascript:void(0);" onclick="list('address','1')">First</a></li>
			<li ><a href="javascript:void(0);" onclick="list('address','${current - 1}')">Prev</a></li>
		</c:otherwise>
		</c:choose>		
	
		<!-- section-2 -->
		<c:forEach begin="${begin}" end="${end}" var="i">
			<c:choose>
				<c:when test="${i==current}">
					<li class="active"><a href="javascript:void(0);" onclick="list('address','${i}')">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li ><a href="javascript:void(0);" onclick="list('address','${i}')">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
		
		<!-- section-3 -->
		<c:choose>
		<c:when test="${current == totalPages}">
		<li class="disabled"><a href="javascript:void(0);">Next</a></li>
		<li class="disabled"><a href="javascript:void(0);">Last</a></li>
		</c:when>
		<c:otherwise>
			<li ><a href="javascript:void(0);" onclick="list('address','${current + 1}')">Next</a></li>
			<li ><a href="javascript:void(0);" onclick="list('address','${totalPages}')">Last</a></li>
		</c:otherwise>
		</c:choose>		
				
				
			</ul>
		</div>
	</c:if>
	
<a href="${path}/">Return to Welcome Page</a>

</body>

</html>