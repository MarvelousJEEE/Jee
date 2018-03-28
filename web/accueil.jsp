<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List,org.tutorial.Book"%>
<%
List<Book> listBooks = (List<Book>)request.getAttribute("listBooks");
String searchText = request.getParameter("searchText");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil de la bibliothèque</title>
</head>
<body>
<h1>Bienvenue dans la bibliothèque</h1>
<p>
Liste des livres
<% if (searchText!=null && !searchText.equals("")) { %>
dont le titre contient '<%=searchText%>'
<% } %>
</p>
<% if (listBooks== null || listBooks.size() == 0) { %>
Aucun résultat
<% } else { %>

<table border="1" cellpadding="5" cellspacing="0">
	<tr>
		<th>Titre</th>
		<th>Auteur</th>
	</tr>
<%
for (Book book:listBooks) {
	String title = book.getTitle();
	String author = book.getAuthor();
%>
<tr>
	<td><%=title %></td>
	<td><%=author %></td>
</tr>
<%
}
%>
</table>
<% } %>
<br/>
<form action="MainServlet" method="post">
Rechercher un livre dont le titre contient : <input type="text" name="searchText">
<input type="submit" value="recherche">
</form>
</body>
</html>