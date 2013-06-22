<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- 
	<h1>Book Found! Title is <s:property value="bookResultTitle"/> and the author is <s:property value="bookResultAuthor"/> </h1>
	<p><a href="<s:url action='index'/>">Go back...</a></p>
	-->
	<s:property value="result"/> <br>
	<s:property value="operationQuery"/>
	<table border="2">
		<thead>
			<tr>
				<td>Bank code</td>
				<td>RFC</td>
				<td>Name</td>
				<td>Address</td>
				<td>Loan</td>
				<td>Date</td>
				<td>Qualification</td>
				<td>Status</td>
			</tr>
		</thead>
		<tr>
			<td><s:property value="bank"/></td>
			<td><s:property value="rfc"/></td>
			<td><s:property value="name"/></td>
			<td><s:property value="address"/></td>
			<td><s:property value="loan"/></td>
			<td><s:property value="date"/></td>
			<td><s:property value="qualification"/></td>
			<td><s:property value="active"/></td>
		</tr>
		
	</table>
	
	
	
	
	
</body>
</html>