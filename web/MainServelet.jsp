<%@ page language="java"%>
<%@page import="java.util.Date"%>
<%
    String myParameter=request.getParameter("param");
    Date now = new Date();
%>

<html>
<head>
    <title>Bonjour la Bretagne</title>
</head>
<body>
<h1>Bonjour la Bretagne</h1>
<p>Valeur du param�tre : <%=myParameter %></p>
<p>Date de la requ�te : <%=now.toString() %></p>


</body>
</html>
