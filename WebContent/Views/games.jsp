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
								int nbGames = 0;
								String pseudo = (String)request.getAttribute("pseudo");
								while(games.next()){
									nbGames++;
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

	<script>
		var compteurTable = new Array() ; // tableau de compteur pour que l'utilisateur ne puisse pas appuyer sur Play ou Stop plus de une fois d'affiler 
		var j=0;
		var size = <%=nbGames%>;
		for(j=0;j<size;j++){
			compteurTable[j]=0;			// initialisation de tous les compteurs de jeu à 0.
		}
	</script>

<script src="<%=request.getContextPath()%>/assets/js/games.js"></script>
<%@include file="footer.jsp"%>
