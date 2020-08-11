<%@ page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->

<jsp:include page="./fragments/header.jsp" />
<body>

<form:form commandName="user">
First Name: <form:input path="fname"/> <form:errors path="fname"/><br/>
Last Name: <form:input path="lname"/> <form:errors path="lname"/><br/>
Email: <form:input path="email"/> <form:errors path="email"/><br/>
Password: <form:input path="password"/> <form:errors path="password"/><br/>

<input type="submit" value="Register"/>
</form:form>


<jsp:include page="./fragments/footer.jsp" />

</body>
</html>