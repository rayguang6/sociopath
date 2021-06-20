

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

			<form class="statusForm" action = "suddenlyPost" method="POST">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="form-group">
					<textarea class="form-control postInput" path="text" placeholder="How you feel today?	"
						name="text" value=""></textarea>
				</div>

				<button type="submit" class="w-100 btn btn-lg btn-primary"
					value="submit">POST</button>
			</form>

		</div>

	</div>
	
	
	
	
	
	
	<!-- For loop to get all posts -->
	<div class="postContainer">
		<div class="d-flex postHead ">
		
			<div class="postProfile">
				<img
					src="https://scontent.fmkz1-1.fna.fbcdn.net/v/t1.6435-9/84240445_2792862387500252_5848255734860480512_n.jpg?_nc_cat=106&ccb=1-3&_nc_sid=84a396&_nc_ohc=A_FJyKjSlqEAX8YEvwN&tn=lCZUPHlVXa-XqvCw&_nc_ht=scontent.fmkz1-1.fna&oh=023728caaa631105678e23bc048b773f&oe=60CDD9FD"
					class="postProfileImg" alt="Owner">
			</div>
			
			<div>
				<h6><a href="#"> Mr Suddenly </a></h6>
				<p> 16 June 2021</p>
			</div>
			
			<a class="postEditLink" href="#">edit</a>
		
		
		</div>
		<div class="postBody">
		
			<div class="homepage-status"> First Ever Post~  Happy Birthday! Let's Do It</div>

		</div>
		
		<div class="postBottom postingButtons">
		
				<button type="submit" class="btn btn-primary-outline outlineButton"
					value="submit">Like</button>
					
				<button type="submit" class="btn btn-primary-outline outlineButton"
					value="submit">Comment</button>
		</div>
	</div>
	
	
	
	<!-- Suddenly Post -->
	<c:forEach var="post" items="${posts}">
		
			<c:url var="editPost" value="/editstatus?id=${post.id}" />
			<c:url var="deleteLink" value="/deletestatus?id=${post.id}" />


			<div class="postContainer">
		<div class="d-flex postHead ">
		
			<div class="postProfile">
				
				<img id="" class="postProfileImg" src="img/avatar${post.ownerName}.png" onerror="this.onerror=null; this.src='img/avatar.png'" alt="Profile Image" >	
			</div>
			
			<div>
				<h6><a href="#"> ${post.ownerName} </a></h6>
			</div>
			
			<a class="postEditLink" href="editPost">edit</a>
		
		
		</div>
		<div class="postBody">
		
			<div class="homepage-status">${post.text}</div>

		</div>
		
		<div class="postBottom postingButtons">
		
				<button type="submit" class="btn btn-primary-outline outlineButton"
					value="submit">Like</button>
					
				<button type="submit" class="btn btn-primary-outline outlineButton"
					value="submit">Comment</button>
		</div>
		
			<div class="commentSection">
			
				<div class="comments">
					
					<img id="" class="postProfileImg" width="50" src="img/avatar${post.ownerName}.png" onerror="this.onerror=null; this.src='img/avatar.png'" alt="Profile Image" >	
					<div class="commentBody">
					
						<div class="commentName"><a href="#">${post.ownerName}</a></div>
						<div class="commentText">
							My Comments On This Post
						</div>
						
						<div class="commentBottom">
							<a href="#">Like</a> <a href="#">Dislike</a>
						</div>
						
					</div>				
				</div>
				
				<div class="comments">
					
					<img id="" class="postProfileImg" width="50" src="img/avatar.png" onerror="this.onerror=null; this.src='img/avatar.png'" alt="Profile Image" >	
					<div class="commentBody">
					
						<div class="commentName"><a href="#">Mr. Suddenly</a></div>
						<div class="commentText">
							My Comments On This Post
						</div>
						
						<div class="commentBottom">
							<a href="#">Like</a> <a href="#">Dislike</a>
						</div>
						
					</div>				
				</div>
				
			</div>
	</div>

		</c:forEach>
	
	
	
	
	
	

</div>


<%@include file="/WEB-INF/includes/footer.jsp"%>