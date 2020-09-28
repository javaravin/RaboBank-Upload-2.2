	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
	<html>
	<head>
	<script type="text/javascript">
  function issueCount()
  {
   // var val = document.getElementById("searchVal").value;

    document.search.action="/search/"+document.getElementById("searchVal").value;
    //alert(val);
    document.search.submit();
  }
  function load(){
    document.search.action='';
  }
  </script>
	<head>
	<body onload="load()">
	<form  name="search"  >
	    Search : <input type="text" id="searchVal"  value=""/>
  		<input type="submit" value="SearchIssueCount" onClick="load(); issueCount()" />
  	</form>
  	<c:if test="${response =='ok'}">
  	<table border="0">
  	<tr>
  	<th>First Name</th>
  	  	<th>SurName</th>
  	  	<th>Issue Count</th>
  	  	<th>Date of Birth</th>
  	</tr>
  	<tr>
  	<c:forEach items="${fileData}" var="message">
  	<tr>
  	<td>${message.firstName}</td>
  	  	<td>${message.surName}</td>
  	  	  	<td>${message.issueCount}</td>
  	  	  	  	<td>${message.dateOfBirth}</td>
  	  	  	  	</tr>
  	  	  	  	</c:forEach>
  	</table>
  	</c:if>
  	<c:if test="${response !='ok'}">
  	   <h6> No data found <h6>
      </c:if>
  	</body>

	<html>
