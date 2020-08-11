<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<!-- <head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head> -->

<jsp:include page="./fragments/header.jsp" />


<body>

<h2>Login Form</h2>
	<form action="login.htm" method = "post">
  	
  	<div class="container user-reg-bg">
    <label for=email><b>Email ID</b></label>
    <input type="text" placeholder="Enter Email" name="email" required><br>

    <label for="psw"><b>Password </b></label>
    <input type="password" placeholder="Enter Password" name="password" required = "required"><br>
        
    <button type="submit">Login</button>
  	</div>
  	
</form>

<jsp:include page="./fragments/footer.jsp" />

</body>

</html>