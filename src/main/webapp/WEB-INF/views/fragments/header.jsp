<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title></title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/users/add" var="urlAddUser" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Bloggers Arena</a>
		</div>
		<div id="navbar">
			<c:if test="${sessionScope.user eq null}">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="registrationform.htm">Sign Up</a></li>
					<li class="active"><a href="login.htm">Login</a></li>
				</ul>
			</c:if>
			<c:if test="${sessionScope.user ne null}">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="createpost.htm">Create Post</a></li>
					<li class="active"><a href="editpost.htm">Edit Post</a></li>
					<li class="active"><a href="logout.htm">Logout</a></li>
				</ul>
			</c:if>
		</div>
	</div>
</nav>

