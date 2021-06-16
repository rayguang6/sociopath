<%@page import="java.util.*" %>
<%@page import="fusioncharts.FusionCharts" %> 

<%@page import="com.sociopath.service.StudentService" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/includes/header.jsp" %>

 <script type = "text/javascript"
src = "https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"> </script> <script type = "text/javascript"
src = "https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"> </script>

    <div id =  "chart" > </div>
    
<%
            // store chart config name-config value pair
            Map<String, String> chartConfig = new HashMap<String, String>();
            chartConfig.put("caption", "Result of Event 4");
            chartConfig.put("subCaption", "Rounds To Arrange Your Book");
            chartConfig.put("xAxisName", "Your Books");
            chartConfig.put("yAxisName", "Height Of Books");
            chartConfig.put("numberSuffix", "");
            chartConfig.put("theme", "fusion");
            //store label-value pair
            
            
            
            StudentService studentService = new StudentService();
           // Map<String, Integer> dataValuePair = new HashMap<String, StudentService studentService;
           
           String books = (String)request.getAttribute("books");
           
            Map<String, Integer> dataValuePair =  studentService.arrangeBook(books);
           // dataValuePair.put("Venezuela", 1);
           // dataValuePair.put("Saudi", 2);
           // dataValuePair.put("Canada", 3);
           // dataValuePair.put("Iran", 1);
            
            
            StringBuilder jsonData = new StringBuilder();
            StringBuilder data = new StringBuilder();
            // json data to use as chart data source
            jsonData.append("{'chart':{");
            for(Map.Entry conf:chartConfig.entrySet())
            {
                jsonData.append("'" + conf.getKey()+"':'"+conf.getValue() + "',");
            }
            jsonData.replace(jsonData.length() - 1, jsonData.length() ,"},");
            // build  data object from label-value pair
            data.append("'data':[");
            for(Map.Entry pair:dataValuePair.entrySet())
            {
                data.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");
            }
            data.replace(data.length() - 1, data.length(),"]");
            jsonData.append(data.toString());
            jsonData.append("}");
            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart = new FusionCharts(
                "column2d",
                "first_chart",
                "700",
                "400",
                "chart",
                "json",
                jsonData.toString()
            );
        %>
<%= firstChart.render() %> 





<br>

<c:set var="count" value="0" scope="page" />

<c:set var="count" value="${count + 1}" scope="page"/>
	
	<c:out value="Round 0) "></c:out>
	
	 <c:forEach var="bookResult" items="${bookResults}" varStatus="s">
	 
			<c:choose>
		    <c:when test="${bookResult.value=='0'}">
		    
	   			 	<br />
		    		<c:out value="Round ${count})"></c:out>
		  			  <c:set var="count" value="${count + 1}" scope="page"/>
		    
		    </c:when>    
		    <c:otherwise>
		        <c:out value=" ${bookResult.value}   "></c:out>
		    </c:otherwise>
			</c:choose>
			
					
	</c:forEach>
	
<%@include file="/WEB-INF/includes/footer.jsp" %>