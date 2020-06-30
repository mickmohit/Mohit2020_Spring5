<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<form:form method="post" class="form-horizontal" action="${path}/user/savePassword" modelAttribute="passwordForm" id="submituserPasswordForm">
<form:hidden path="userId" value='${userId}'/>
<div class="container col-lg-5">
	<div class="card">
		<div class="card-header">
			<strong>
				<i class="fa fa-lock"></i> Change Password
			</strong>
		</div>
		<div class="card-body">
			<div class="row">
				<label class="col-md-3 control-label">Current: </label>
				<div class="col-md-5 mb-3">
					<input class="form-control" name="oldPassword" placeholder="Enter Current Password" required="true"/>
				</div>
			</div>
				
			<div class="row">	
				<label class="col-md-3 control-label">New : </label>
				<div class="col-md-5 mb-3">	
					<input class="form-control" name="newPassword" placeholder="Enter New Password" required="true"/>
				</div>
			</div>
				
			<div class="row">
				<label class="col-md-3 control-label">Enter Retype New Password : </label>
				<div class="col-md-5 mb-3">
					<input class="form-control" name="c_newPassword" placeholder="Enter Retype New Password" required="true"/>
				</div>
			</div>
		</div>			
		<div class="card-footer text-right">
			<button class="btn btn-sm btn-primary">
				<i class="fa fa-save"></i> Save Changes
			</button>
		</div>
	</div>
</div>
</form:form>