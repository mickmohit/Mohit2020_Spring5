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
			</c:choose> Address
		</strong>
	</div>
	<form:form method="post" class="form-horizontal" action="${path}/address/add" modelAttribute="addressForm" id="submitAddressForm">
		<form:hidden path="addressId"/>
		<div class="panel-body">
			
			<div class="form-group">
				<label class="col-md-2 control-label">Country : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="country" placeholder="Enter Country" required="true"/>
				</div>
				
				<label class="col-md-2 control-label">State : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="state" placeholder="Enter State" required="true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">City : </label>
				<div class="col-md-4">
					<form:input class="form-control" path="city" placeholder="Enter City" required="true"/>
				</div>
				
				
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">User :</label>
				<div class="col-md-4">
					<form:select class="form-control" path="userId">
						<c:forEach items="${users}" var="user">
							<form:option value="${user.userId}">${user.userName}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
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



<%-- <body>

<form:form method="post" action="${path}/address/add" modelAttribute="addressForm">
<form:hidden path="addressId" />
Country <form:input path="country" placeholder="Enter Country"/></p>
State <form:input path="state" placeholder="Enter State"/></p>
City <form:input path="city" placeholder="Enter City"/></p>
User

<form:select path="user.userId">

<c:forEach items="${users}" var="user">
<form:option value="${user.userId}">${user.userName}</form:option>
</c:forEach>
</form:select>

</p>
<form:button>Save</form:button>

<a href="${path}/address/list">List</a>
</form:form>

</body> --%>
