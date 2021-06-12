

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
function RandomCrush() {
    var rnd = Math.floor(Math.random() * 9+1);
    document.getElementById('crush').value = rnd;
}

function RandomRumor() {
    var rnd = Math.floor(Math.random() * 9+1);
    document.getElementById('rumor').value = rnd;
}

function RandomChoice() {
    var rnd = Math.floor(Math.random() * 9+1);
    document.getElementById('choice').value = rnd;
}


</script>


<h1>Event 5 Here...  </h1>

<br>
<h3>Your Result is</h3>
<h3>⬇️⬇️⬇️⬇️</h3>


<form method="post" action="event5">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	<label for="crush"></label>
	<input type="text" name="crush" placeholder="who's the Crush?" id="crush" >
    <input type="button" value="Random Number!" onclick="RandomCrush();" />
    
    <input type="text" name="rumor" placeholder="where rumor's began?" id="rumor" >
    <input type="button" value="Random Number!" onclick="RandomRumor();" />
    
    
    <input type="text" name="choice" value="0" placeholder="how many extra line you want?" id="choice" >
    <input type="button" value="Generate Number!" onclick="RandomChoice();" />
    
    <input type="submit" class="btn btn-primary" value="Submit">
</form>

	