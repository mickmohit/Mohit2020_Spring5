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
<a href="${path}/">Return to Welcome Page</a>

</body>

</html>