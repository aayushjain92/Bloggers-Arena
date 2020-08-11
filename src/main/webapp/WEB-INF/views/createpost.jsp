<%@ page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<!-- <head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head> -->

<jsp:include page="./fragments/header.jsp" />

<body>

<form:form action= "createpost.htm" method = "post" commandName="post">
Title: <form:input path="title"/> <form:errors path="title"/><br/>
Body: <form:input path="body" /> <form:errors path="body"/><br/>

<!-- <form:textarea path="body"  rows = "20" cols = "40" /> -->

<input type="submit" value = "Add"/>
</form:form>
<jsp:include page="./fragments/footer.jsp" />

</body>
</html>