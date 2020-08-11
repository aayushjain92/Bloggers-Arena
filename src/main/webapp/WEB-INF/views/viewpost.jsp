<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="./fragments/header.jsp" />

<body>

	<!-- Loading post here -->


	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>${post.title}</h1>
		<br />


		<div class="row">
			<label class="col-sm-2">Date Published</label>
			<div class="col-sm-10">${post.date}</div>
		</div>

		<div class="row">
			<label class="col-sm-2">Content</label>
			<div class="col-sm-10">${post.body}</div>
		</div>

	</div>


	<!-- Loading comments in reverse chronological order here-->
	<!-- Creating an extra box for the comment -->
	<!-- Creating a button and refreshing the post -->


 	<c:if test="${comments ne null}">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Comments</th>
				<th>DatePosted</th>
			</tr>
		</thead>

		<c:forEach var="comment" items="${comments}">
			<tr>
				<td>${comment.commentText}</td>
				<td>${comment.date}</td>

			</tr>
		</c:forEach>
		
		<c:if test="${sessionScope.user eq null}">
		<tr>
			<td colspan="2">
				<form action="addcomment.htm">
					<textarea name="comment" > Enter comment here... </textarea>
					<!-- <input type="textarea" name="comment">  -->
					<input type="submit" value="add">
				</form>
			</td>
		</tr>
		</c:if>
		
	</table>
	</c:if>
	<c:if test="${post.comments eq null}">
	 <p>There are no comments</p>
	</c:if>
 
	<jsp:include page="./fragments/footer.jsp" />

</body>
</html>