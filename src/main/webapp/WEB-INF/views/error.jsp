<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Invalid Credentials !</title>
</head> -->

<jsp:include page="./fragments/header.jsp" />

<body>
<h2>Error Code: ${responseCode}</h2>
<h3>Reason: ${errorMessage}</h3>

<jsp:include page="./fragments/footer.jsp" />

</body>
</html>