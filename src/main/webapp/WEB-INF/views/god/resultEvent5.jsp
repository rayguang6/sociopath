
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>Result of EVENT 5 </h1>

<br>
<h3>Your Result is</h3>

<h5>Rumors starts from : <c:out value="${rumor}"></c:out></h5>
<h5>Crush is : <c:out value="${crush}"></c:out></h5>

<h5>You chosed to add : <c:out value="${choice}  lines"></c:out></h5>

	<c:forEach var="result" items="${results}" varStatus="s">
			<c:out value="  ${result}    "></c:out>
			
			<br>
	</c:forEach>
	
	
	
	<br><br>
	
	
			  
	