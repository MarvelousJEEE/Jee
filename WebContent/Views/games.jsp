<%@include file="header.jsp"%>
<%@ page import="servlets.SessionTools" %>
<%@ page import="bdd.GestionBDD" %>
<%@ page import="java.sql.ResultSet" %>

		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="#games">Games</a></li>
					<li><a href="/J2EE/infoPlayer"> Profil </a></li>
					<li><a href="#" id="logout">LogOut</a></li>
				</ul>
				
			</nav>



		<!-- Game list  -->
			<div class="wrapper style1 first">
				<article id="games">
				<h1>
				Bonjour <%=request.getAttribute("pseudo")%>, amusez vous bien !
			</h1>
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
									if(games.getBoolean("isShowed")){
										name = games.getString("name");

							%>
										<br>
										<h2><%= name %></h2>
										<p>
											 <%= games.getString("infos") + "   " + games.getString("release")%> 
											</p> 
									
									<div style = "margin-top : -1%">
									
										<input type="submit" class="btn " value="Play" onclick="play('<%=pseudo%>','<%=name%>','<%=nbGames%>')"/>
										<input type="submit"  class="btn "  value="Stop" onclick="stop('<%=pseudo%>','<%=name%>','<%=nbGames%>')"/>
										<br>
									</div>
									<% } %>
							<%} %>
					</fieldset>
					</div>
					
				</article>
			</div>

	<script>
		var compteurTable = new Array() ; // tableau de compteur pour que l'utilisateur ne puisse pas appuyer sur Play ou Stop plus de une fois d'affiler 
		var j=0;
		var size = <%=nbGames%>;
		for(j=1;j<=size;j++){
			compteurTable[j]=0;			// initialisation de tous les compteurs de jeu à 0.
		}
	</script>

<script src="<%=request.getContextPath()%>/assets/js/games.js"></script>
<%@include file="footer.jsp"%>
