<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get a new loan</title>
</head>
<body>
	<h1>APPROVED</h1>
	<s:form action="new">
		<s:textfield label="Loan amount" name="loanAmount" />
		<input type="hidden" name="loanQuery" value="<s:property value="loanQuery"/>">
		<s:submit value="SUBMIT" />
	</s:form>
</body>
</html>