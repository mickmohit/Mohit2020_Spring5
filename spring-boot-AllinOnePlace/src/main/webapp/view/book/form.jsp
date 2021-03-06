<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript" src="${path}/js/jquery.save.js"></script>
<div class="panel panel-default">
	<div class="panel-heading">
		<strong>
			<c:choose>
				<c:when test="${isNew}"><span class="glyphicon glyphicon-plus-sign"></span></c:when>
				<c:otherwise><span class="glyphicon glyphicon-edit"></span></c:otherwise>
			</c:choose> Book
		</strong>
	</div>

<form:form method="post" class="form-horizontal" action="${path}/book/add" modelAttribute="bookForm" id="submitBookForm">
		<form:hidden path="id" />
		<div class="panel-body">
			
			<div class="form-group">
				<label class="col-md-2 control-label">Title : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="title" placeholder="Enter Title" required="true"/>
				</div>
			</div>	
				
			<div class="form-group">
				<label class="col-md-2 control-label">Author : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="author" placeholder="Enter Author" required="true"/>
				</div>
				
				<label class="col-md-2 control-label">IsbnNumber : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="isbnNumber" placeholder="Enter IsbnNumber" required="true"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-md-2 control-label">Price : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="price" placeholder="Enter Price" required="true"/>
				</div>
				
				<label class="col-md-2 control-label">Language : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="language" placeholder="Enter Language" required="true"/>
				</div>
			</div>
			
							
				<%-- <c:if test="${userForm.userId ne null}"> --%>
				
				<!-- <label class="col-md-2 control-label">Profile Picture :</label>	
				<div class="col-md-4">
				<button type="button" class="btn btn-default" data-toggle="modal" data-target="#uploadModal" >
					<span class="glyphicon glyphicon-upload">Upload</span>
				</button>
				</div> -->
				<%-- </c:if> --%>
								
			
		</div>
		<div class="panel-footer">
			<form:button value="Save" class="btn btn-xs btn-default">
				<span class="glyphicon glyphicon-floppy-disk"></span>
				<c:choose>
					<c:when test="${isNew}"> Save</c:when>
					<c:otherwise> Update</c:otherwise>
				</c:choose>
			</form:button>
		</div>
	</form:form>
</div>	
	
<%-- 
<c:if test="${userForm.userId ne null}"> --%>
<%-- <div id="uploadModal" class="modal fade" role="dialog">
<div class="modal-dialog">
	<div class="modal-content">
		<form id="uploadImage" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Upload User Profile for ${userForm.fullName} </h4>
			</div>												  
			<div class="modal-body">
				<div class="form-group">
					<input type="hidden" name="editUserId" value="${userForm.userId}">
					<label class="col-md-3 control-label">Upload File :</label>	
					<div class="col-md-6">
					 	<input type="file" name="file" class="form-control">
					</div>
			</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-default">Upload</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</form>
	</div>
</div>
</div> --%>
<%-- </c:if> --%>

<%-- <body>

<form:form method="post" action="${path}/user/add" modelAttribute="userForm">
<form:hidden path="userId" />
User Id: <form:input path="userId" placeholder="Enter user ID"/> </p>
User Name <form:input path="userName" placeholder="Enter User Name"/></p>
Password <form:input path="password" placeholder="Enter Password"/></p>
Role
<form:select path="role.roleId">
<c:forEach items="${roles}" var="role">
	<form:option value="${role.roleId}">${role.name}</form:option>
</c:forEach>
</form:select>
</p>
<form:button>Save</form:button>
</p>
<a href="${path}/user/list">List</a>
</form:form>

</body> --%>
