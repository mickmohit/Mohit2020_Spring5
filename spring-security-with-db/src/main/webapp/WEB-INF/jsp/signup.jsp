<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/app.css"rel="stylesheet"
	type="text/css"></link>
 <title>Registration Form</title>
</head>
<body class="security-app">
	<div class="details">
		<h2>Spring Security - JDBC Authentication-Registration Form</h2>
		
	</div>
	<div class="lc-block">
            <form:form method="post" modelAttribute="users" action="/signup">
                <table >
                    <tr>
                        	<td><label>First Name</label></td>
                        <td> <spring:bind path="name">
                            <form:input type="text" path="name" placeholder="name"
                                autofocus="true"/>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label >Password</label>
                        </td>
                        <td> <spring:bind path="password">
                            <form:input type="text" path="password" placeholder="password"
                                autofocus="true"/>
                            </spring:bind>
                        </td>
                    </tr>
                   
                    <tr>
                        <td>
                            <label >LastName</label>
                        </td>
                       <td> <spring:bind path="lastName">
                            <form:input type="text" path="lastName" placeholder="lastName"
                                autofocus="true"/>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label >Email</label>
                        </td>
                         <td> <spring:bind path="email">
                            <form:input type="text" path="email" placeholder="email"
                                autofocus="true"/>
                            </spring:bind>
                        </td>
                    </tr>
                   
                    <tr>
                        <td></td>
                        <td>
                            <form:button class="button red small" name="register">Register</form:button>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td></td>
                        <td><a href="<spring:url value='login'/>">Back to Login Page</a>
                        </td>
                    </tr>
                </table>
            </form:form>
  
     
    </div>
   
        </body>
</html>
