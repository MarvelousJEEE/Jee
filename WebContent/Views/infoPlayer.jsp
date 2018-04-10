<%@include file="header.jsp"%>
<%@ page import="bdd.GestionBDD" %>
<%@ page import="java.sql.ResultSet" %>


		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="/J2EE/games">Games</a></li>
				
					<li><a href="#" id="logout">LogOut</a></li>
				</ul>
			</nav>


		<!-- Password  -->
			<div class="wrapper style1 first">
				<article class="container" id="password">
					<div class="row">
						<form method="post" action="/J2EE/infoPlayer">
							<fieldset>
								<br> <br>
								<h1>Change your password</h1>
								<label for="Password">Old password</label>
								<input type="password" id="password" name="password" value="" size="20" maxlength="60"/> <br>
								<label for="NewPassword">New password</label>
								<input type="password" id="NewPassword" name="NewPassword" value="" size="20" maxlength="60"/> <br>
								<label for="RepeatPassword">Repeat new password</label>
								<input type="password" id="RepeatPassword" name="RepeatPassword" value="" size="20" maxlength="60"/> <br>
								<input type="hidden" value="password" name="option"/>
								<input type="hidden" value= <%=request.getAttribute("pseudo")%>  name="pseudo"/> 
								<input type="submit" value="Validate"/>
							</fieldset>
						</form>	
					</div>
				</article>
			</div>

		<!-- adresse mail  -->
			<div class="wrapper style2">
				<article class="container" id="email">
					<form method="post" action="/J2EE/infoPlayer">
						<div class="row" align="center">
							<fieldset> <br>
								<h1>Change your email</h1>
								<label for="newEmail">email</label>
								<input type="text" id="newEmail" name="newEmail" value=<%=request.getAttribute("email")%> size="20" maxlength="60"/><br>
								<input type="hidden" value= <%=request.getAttribute("pseudo")%>  name="pseudo"/>
								<input type="hidden" value="email" name="option" /> 
								<input type="submit" value="Validate"/>
							</fieldset>
						</div>
					</form>			
				</article>
			</div>
			
			<!-- Prefered games  -->
			<div class="wrapper style3">
				<article class="container" id="preferedGames">
					<form method="post" action="/J2EE/infoPlayer">
						<div class="row" align="center">
							<fieldset>
								<h1>Your prefered games</h1>
								<%
								GestionBDD bdd = GestionBDD.getInstance();
								ResultSet games = bdd.getGames();
								//ResultSet prefered = bdd.getGames();
								ResultSet prefered = bdd.getPreferedGames(request);
								while(games.next()) {
									boolean isPrefered = false;
									prefered.beforeFirst();
									while(!isPrefered && prefered.next()) {
										if(prefered.getString("gameName").equals(games.getString("name"))) {
											isPrefered = true;
										}
									}
									if(isPrefered) {%>
										<input type="checkbox" value="<%=games.getString("name")%>" name="games" checked/> <%=games.getString("name") %><br>
								  <%} else {%>
								  		<input type="checkbox" value="<%=games.getString("name")%>" name="games" /> <%=games.getString("name") %><br>
								 <% }
								}%>
								 
								
								
								<input type="hidden" value= <%=request.getAttribute("pseudo")%>  name="pseudo"/> 
								<input type="hidden" value="preferedGames" name="option" /> <br>
								<input type="submit" value="Validate"/>
							</fieldset>
						</div>
					</form>			
				</article>
			</div>


			

<%@include file="footer.jsp"%>	
	