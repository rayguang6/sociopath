

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="/WEB-INF/includes/header.jsp"%>

<c:url var="search" value="/search" />

<!-- Post Form -->



<div class="container">

	<div class="postContainer">
		<h2>Post Something Here</h2>
		<div class="myForm">

			<form:form class="statusForm" modelAttribute="statusUpdate">
				<div class="form-group">
					<form:textarea class="form-control postInput" path="text"
						name="text" value=""></form:textarea>
				</div>

				<button type="submit" class="w-100 btn btn-lg btn-primary"
					value="submit">POST</button>
			</form:form>

		</div>

	</div>
	
	
	
	
	
	
	<!-- For loop to get all posts -->
	
	
	<div class="postContainer">
		<div class="d-flex postHead ">
		
			<div class="postProfile">
				<img
					src="https://scontent.fmkz1-1.fna.fbcdn.net/v/t1.6435-9/92952811_2914026528717170_5238843194598227968_n.jpg?_nc_cat=100&ccb=1-3&_nc_sid=174925&_nc_ohc=gWp41UAvy20AX94P4Sp&_nc_ht=scontent.fmkz1-1.fna&oh=f6a30697e2294822330c68366fb8a37f&oe=60C92E3B"
					class="postProfileImg" alt="Owner">
			</div>
			
			<div>
				<h6><a href="#"> Lei Zhi Guang </a></h6>
				<p> 16 June 2021</p>
			</div>
			
			<a class="postEditLink" href="#">edit</a>
		
		
		</div>
		<div class="postBody">
		
			<div class="homepage-status">${statusUpdate.text}</div>

		</div>
		
		<div class="postBottom postingButtons">
		
				<button type="submit" class="btn btn-primary-outline outlineButton"
					value="submit">Like</button>
					
				<button type="submit" class="btn btn-primary-outline outlineButton"
					value="submit">Comment</button>
			
		</div>
	</div>
	
	
	

</div>


<%@include file="/WEB-INF/includes/footer.jsp"%>