<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div class="panel panel-default">
	<div class="panel-heading">
		<strong>
			<span class="glyphicon glyphicon-list"></span> User List
		</strong>
		<div class="btn-group pull-right">
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="addForm('user')" title="Add New User">
				<span class="glyphicon glyphicon-plus-sign"></span>
			</a>
			<a href="javascript:void(0);" class="btn btn-default btn-sm" onclick="refresh('user')" title="Refresh User">
				<span class="glyphicon glyphicon-refresh"></span>
			</a>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-bordered table-condensed table-hover table-striped">
			<thead>
				<tr>
					<th>Full Name</th>
					<th>User Id</th>
					<th>User Name</th>
					<th>Role</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>

		<tbody>
					<c:choose>
					<c:when test="${users.size() > 0}">
		
<c:forEach items="${users}" var="user"> 
<tr>
<td>"${user.userId}"</td>
<td>"${user.userName}"</td>
<td>"${user.password}"</td>
<td>"${user.role.name}"</td>
<%-- <td><a href="${path}/user/edit/${user.userId}">Edit</a></td>
<td><a href="${path}/user/delete/${user.userId}">Delete</a></td> --%>
<td><a href="javascript:void(0);" onclick="editForm('user', '${user.userId}')"><span class="glyphicon glyphicon-edit"></span></a></td>
<td><a href="javascript:void(0);" onclick="deleteData('user', '${user.userId}')"><span class="glyphicon glyphicon-trash"></span></a></td>
</tr>
 </c:forEach>
 
 					</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="8">No Users available</td>
						</tr>
					</c:otherwise>
				</c:choose>
	</tbody>

</table>

<a href="${path}/">Return to Welcome Page</a>
</div>