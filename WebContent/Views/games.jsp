<%@include file="header.jsp"%>
<%@ page import="servlets.SessionTools" %>
<%@ page import="bdd.GestionBDD" %>
<%@ page import="java.sql.ResultSet" %>

		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="#games">Games</a></li>
					<li><a href="#copyright"> Contactez-nous </a></li>
					<li><a href="#" id="logout">LogOut</a></li>
				</ul>
			</nav>
			<h1>
				Bonjour<%=request.getAttribute("pseudo")%>, amusez vous bien !
			</h1>


		<!-- Game list  -->
			<div class="wrapper style1 first">
				<article id="games">
					<div class="row">
						<fieldset>
							<% 
								GestionBDD bdd = GestionBDD.getInstance();
								ResultSet games = bdd.getGames();
								String name;
								String pseudo = (String)request.getAttribute("pseudo");
								while(games.next()){
									name = games.getString("name");
							%>
								<label><%= name %></label>
								<input type="submit" value="Play" onclick="play('<%=pseudo%>',name)"/>
								<input type="submit" value="Stop" onclick="stop('<%=pseudo%>',name)"/>
								</br>
							<%} %>
					</fieldset>
					</div>
					
				</article>
			</div>



<script src="<%=request.getContextPath()%>/assets/js/games.js"></script>
		
<%@include file="footer.jsp"%>




