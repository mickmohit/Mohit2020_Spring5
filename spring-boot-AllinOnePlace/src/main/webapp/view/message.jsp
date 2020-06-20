<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
<title>Message Page</title>

</head>
<body>

${message}</br>
<a href="${path}/user/list">User List</a></br>
<a href="${path}/user/form">User Form</a></br>
<a href="${path}/address/list">Address List</a></br>
<a href="${path}/address/form">Address Form</a>

</body>
</html>