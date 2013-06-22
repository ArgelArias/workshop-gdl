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
	<h1>Loans</h1>
	<s:form action="evaluate">
		<s:textfield name="loanQuery" label="RFC"/>
		
		<s:select label="Operation" headerKey="-1" headerValue="Select operation"
		 list="#{'1':'Display Loans', '2':'Payment', '3':'Close loan', '4':'Get a new loan'}"
		 name="operationQuery">
		</s:select>
		
		<s:submit value="Search"/>
	</s:form>
</body>
</html>