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
	<!-- <s:property value="rs"/> <br> -->
	
	<table border="2">
		<thead>
			<tr>
				<td>RFC</td>
				<td>Name</td>
				<td>Address</td>
				<td>Loan</td>
				<td>Date</td>
				<td>Qualification</td>
				<td>Status</td>
			</tr>
		</thead>
			<s:iterator value="loans">
				<tr>
				<td><s:property value="RFC"/></td>
				<td><s:property value="NAME"/></td>
				<td><s:property value="ADDRESS"/></td>
				<td><s:property value="LOAN_AMOUNT"/></td>
				<td><s:property value="EXPIRATION_DATE"/></td>
				<td><s:property value="QUALIFICATION"/></td>
				<td><s:property value="STATUS"/></td>
				</tr>
			</s:iterator>
		
	</table>
	
	<a href="<s:url action='index'/>">Go back</a>

</body>
</html>