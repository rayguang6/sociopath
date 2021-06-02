
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<title><tiles:insertAttribute name="title" /></title>
    
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


    <!-- Bootstrap CSS -->
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextRoot}/css/styles.css" rel="stylesheet">

  </head>
  <body>

    
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    
  <div class="container-fluid">
  
    <a class="navbar-brand" href="${contextRoot}/">SCP</a>
    
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${contextRoot}/about">About</a>
        </li>
   
        
        <li class="nav-item">
          <a class="nav-link disabled" href="/" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
      </ul>
      
      
      <form class="d-flex searchForm">
        <input class="form-control" type="search" placeholder="Search" aria-label="Search">
        <button id="searchButton" class="myButton" type="submit" >Search</button>
      </form>
      </div>
      
       <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	  	 
	  	 <sec:authorize access="isAuthenticated()">
	  	 
	  	 
	  	 <li class="nav-item"><a class="nav-link" href="${contextRoot}/profile">Profile</a></li>
        	 
          <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            ADMIN Action
          </a>
         	 <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="${contextRoot}/viewstatus">View status</a></li>
            <li><a class="dropdown-item" href="${contextRoot}/addstatus">Post Status</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Any Admin Action</a></li>
        	  </ul>
      	  </li>
		  	 
		  	   <c:url var="logoutLink" value="/logout" />
				<form id="logoutForm" method="post" action="${logoutLink}">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button class="myButton" type="submit" >Logout</button>
				</form>
	  	 
	  	 </sec:authorize>
	  	 
	  	  <sec:authorize access="!isAuthenticated()">
         
			<li class="nav-item"><a class="nav-link" href="${contextRoot}/login">Login</a></li>
			<li class="nav-item"><a class="nav-link" href="${contextRoot}/register">Register</a></li>
					
	  	 </sec:authorize>
         
       </ul>
      
    </div>
  

  
  
</nav>


    
    
    <div class="container">
		<tiles:insertAttribute name="content" />
	</div>

  
  
  	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

	<script src="${contextRoot}/js/bootstrap.min.js"></script>
   
  </body>
</html>