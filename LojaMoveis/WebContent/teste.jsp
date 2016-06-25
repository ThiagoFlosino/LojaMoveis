<%@page import="Mapper.cozinhaMapper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	cozinhaMapper cm = new cozinhaMapper();
	cm.Connect();
	if(cm.estaConectado() == true){
		out.println("Connectado");
	}
%>
	<div class="crud">
		Descrição: <input type="text" name="descricao">
		<input type="submit" Value="Adicionar">
	</div>
</body>
</html>