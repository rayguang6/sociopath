
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h1>Visualize Result Of 4  </h1>

<br>
<h3>Your Result is</h3>


		

	Result is : <%= request.getAttribute("books") %>
	<br>
	<br>
	<br>
	
	<%= request.getAttribute("bookResults") %>