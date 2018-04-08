<%@ page import="servlets.Admin"%>
<%@include file="header.jsp"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="bdd.GestionBDD" %>
<%
	GestionBDD bdd = GestionBDD.getInstance();
	ResultSet games = bdd.getGames();
	ResultSet players = bdd.getPlayers();
	ResultSet matchs = bdd.getCurrentMatchs();
	ResultSet matchsEnd = bdd.getFinishedMatchs();
%>


		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="#gameList">Game List</a></li>
					<li><a href="#addGame">Add Game </a></li>
					<li><a href="#players"> Players </a></li>
					<li><a href="#plays"> Plays </a></li>
					<li><a href="#history">History</a>
					
					<li><a href="#" id="logout">LogOut</a></li>
				</ul>
			</nav>

		<!-- Game List  -->
			<div class="wrapper style1 first">
				<article class="container" id="gameList">
					<div class="row">
						
						<div class="8u 12u(mobile)" style="
    padding-left: 35%;" >
				
						<h1> List of Games !  </h1>		
						
								
						<ol>
								<% String show;
							      String hide; %>	
								<%	while (games.next()) { %>
								      <%
								      if(games.getBoolean("isShowed")){
								    	show = "checked";
								    	 hide ="";								    	  
								      }else {
								    	hide = "checked";
								    	show="";
								      }   
								      %>
									<li>  
										<form action="/J2EE/admin" method="post">
										<label><%= games.getString("name") %></label> <input type="hidden" value= <%= games.getString("name") %> name="name" />
						    				 <%= games.getString("infos") + "   " + games.getString("release")%> 
						 	                 <label > <input type="radio" name="option" value="show" <%=show %>>Show</label>
											<label><input type="radio" name="option" value="hide" <%=hide %>>  Hide</label>
											<label ><input type="radio" name="option" value="delete" >Delete</label>
						                     <button type="submit">Apply</button>       
										</form>
									</li>	 
							  <% } %>
						 </ol>
						</div>
					</div>
				</article>
			</div>

		<!-- Add Game  -->
			<div class="wrapper style2">
				<article id="addGame">
					<header>
						<h2>Add a game ! </h2>
						<p>You can easily add a game here !</p>
					</header>
					<div class="container">	
						<form action="/J2EE/admin" method="post">
						<input type="hidden" value="add" name="option" />
							Game title	 <input type="text" name="name">  <br>
							Release (YYYY-MM-DD) 	<input type="text" name="release"> <br>
							Infos <input type="text" name="infos"> <br>
							<button type="submit">ADD</button>
						</form>
					</div>
					
				</article>
			</div>

		<!-- Table player  -->
			<div class="wrapper style3">
				<article id="players">
					<header>
						<h2> Table of all players </h2>
						<p> You can ban or unbanned player as you please ! </p>
					</header>
					<div class="container">
						<table class="table table-striped table-dark">
							  <thead>
							    <tr>
							    
							    	
							      <th scope="col">#</th>
							      <th scope="col">Pseudo</th>
							      <th scope="col">Subscription Date</th>
							      <th scope="col">Plays</th>
							      <th scope="col">Status</th>
							     
							    </tr>
							  </thead>
							  <tbody>
							  <% int k = 0; 
							  	 String ban;
							  %>
							   <%	while (players.next()) { 
							    k++;
							   %>
							    <%
								ResultSet plays = bdd.getPlays( (String) players.getString("pseudo"));
							    plays.next();
								      if( players.getBoolean("ban")){
								    	ban = "Banned";								    								    	  
								      }else {
								    	ban = "Authorized";								   
								      }   
								      %>
							  
							    <tr>
							      <th scope="row"><%=k %></th>
							      <td> <%= players.getString("pseudo") %> </td>
							         <td> <%= players.getString("subscription") %> </td>
							      
								  <td> <%= 
								  plays.getString("count(*)") %> </td> 
							     <td> 
							     
							     
							     	<form action="/J2EE/admin" method="post">
										<input type="hidden" value= <%= players.getString("pseudo") %> name="pseudo" />
										<input type="hidden" value="ban"  name="option" />
										<button  type="submit" class="btn btn-primary btn-sm" value= <%= players.getString("ban") %> name="ban"  >  <%= ban %> </button>	 	   
										</form>
			    
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
					
					</div>
				
				</article>
			</div>


		<!-- CurrentPlay -->
			<div class="wrapper style1">
				<article id="plays" class="container 75%">
					<header>
						<h2> Current plays  </h2>
						<p>You can find here all current plays and end them as you please  </p>
					</header>
					<div>
					
						<table class="table table-striped table-dark">
							  <thead>
							    <tr>
							    
							    	
							      <th scope="col">#</th>
							      <th scope="col">Game</th>
							      <th scope="col"> Pseudo </th>
							      <th scope="col">Start</th>
							      <th scope="col">Stop</th>
							     
							    </tr>
							  </thead>
							  <tbody>
							  <% int i = 0; 			
							  %>
							   <%	while (matchs.next()) { 
							    i++;
							   %>
							
							    <tr>
							      <th scope="row"><%=i %></th>
							      <td><%= matchs.getString("gameName") %></td>
							      <td><%= matchs.getString("pseudo") %></td>
							          <td><%= matchs.getString("hBegin") %></td>
							      
							     <td>  <form action="/J2EE/admin" method="post">
                    <input type="hidden" value="end"  name="option" />
                    <button  type="submit" class="btn btn-primary btn-sm" value= <%= matchs.getString("idMatch") %> name="id"  >  End the game </button>        
                    </form>
                    </td>
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
					</div>
				</article>
			</div>
			
			<!-- Finished Plays  -->
			<div class="wrapper style2">
				<article id="history" class="container 75%">
					<header>
						<h2> Finished plays  </h2>
						<p>Here is an history of all plays  </p>
					</header>
					<div>
					
						<table class="table table-striped table-dark">
							  <thead>
							    <tr>
			    
							    	
							      <th scope="col">#</th>
							      <th scope="col">Game</th>
							      <th scope="col"> Pseudo </th>
							      <th scope="col">Start</th>
							      <th scope="col">End</th>
							     
							    </tr>
							  </thead>
							  <tbody>
							  <%
							  	int l = 0;
							  %>
							   <%
							   	while (matchsEnd.next()) { 
							   					    l++;
							   %>
							
							    <tr>
							      <th scope="row"><%=l%></th>
							      <td><%=matchsEnd.getString("gameName")%></td>
							      <td><%=matchsEnd.getString("pseudo")%></td>
							      <td><%=matchsEnd.getString("hBegin")%></td>     
							     <td><%=matchsEnd.getString("hEnd")%></td>
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
					</div>
				</article>
			</div>
			


<script>

refreshGo();

function refreshGo() {
    myInterval = setInterval(refresh,60*5000);

};


function refresh(){
	
	var redirection = "http://"+window.location.host+"/J2EE/admin";
	document.location.href=redirection;
   
};

</script>

<%@include file="footer.jsp"%>

		


		


		
						

