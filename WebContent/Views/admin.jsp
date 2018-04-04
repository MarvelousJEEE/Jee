<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="servlets.Admin"%>
<% Admin ad = new Admin();%>



<html>
	<head>
		<title>Marvellous Admin</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="../assets/css/main.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
	</head>
	<body>

		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="#top">Game List</a></li>
					<li><a href="#work">Add Game </a></li>
					<li><a href="#portfolio"> Players </a></li>
					<li><a href="#contact"> Plays </a></li>
					<li><a href="#">LogOut</a></li>
				</ul>
			</nav>

		<!-- Game List  -->
			<div class="wrapper style1 first">
				<article class="container" id="top">
					<div class="row">
						<div class="4u 12u(mobile)">
							<span class="image fit"><img src="<%=request.getContextPath()%>/images/games.jpg" alt="" /></span>
						</div>
						<div class="8u 12u(mobile)">
				
						<h1> List of Games !  </h1>		
						
								
						<ol>
								<% String show;
							      String hide; %>	
								<%	while (ad.getGames().next()) { %>
								      <%
								      if( "1".equals( ad.getGames().getString("show")  )){
								    	show = "checked";
								    	 hide ="";								    	  
								      }else {
								    	hide = "checked";
								    	show="";
								      }   
								      %>
									<li>  
										<form action="/J2EE/admin" method="post">
										<input type="hidden" value= <%= ad.getGames().getString("name") %> name="name" />
						    				 <% out.print( ad.getGames().getString("infos")) ; 	 %>  
						 	                 <%	 out.print( "   " + ad.getGames().getString("release")) ;	 %>  
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
				<article id="work">
					<header>
						<h2>Add a game ! </h2>
						<p>You can easily add a game here !</p>
					</header>
					<div class="container">	
						<form action="/J2EE/admin" method="post">
						<input type="hidden" value="add" name="option" />
							Id	<input type="text" name="name"/>   <br>  
							Game title	 <input type="text" name="infos">  <br>
							Realese (YYYY-MM-DD) 	<input type="text" name="release"> <br>
							<button type="submit">ADD</button>
						</form>
					</div>
					
				</article>
			</div>

		<!-- Table player  -->
			<div class="wrapper style3">
				<article id="portfolio">
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
							   <%	while (ad.getPlayers().next()) { 
							    k++;
							   %>
							    <%
								      if( "1".equals( ad.getPlayers().getString("ban")  )){
								    	ban = "Banned";								    								    	  
								      }else {
								    	ban = "Athorized";								   
								      }   
								      %>
							  
							    <tr>
							      <th scope="row"><%=k %></th>
							      <td> <%= ad.getPlayers().getString("pseudo") %> </td>
							         <td> <%= ad.getPlayers().getString("subscription") %> </td>
							      
								  <td> N.A </td> 
							     <td> 
							     
							     
							     	<form action="/J2EE/admin" method="post">
										<input type="hidden" value= <%= ad.getPlayers().getString("pseudo") %> name="pseudo" />
										<input type="hidden" value="ban"  name="option" />
										<button  type="submit" class="btn btn-primary btn-sm" value= <%= ad.getPlayers().getString("ban") %> name="ban"  >  <%= ban %> </button>	 	   
										</form>
			    
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
					
					</div>
				
				</article>
			</div>


		<!-- CurrentPlay  -->
			<div class="wrapper style2">
				<article id="work">
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
							   <%
							   	while (ad.getCurrentMatchs().next()) { 
							   					    i++;
							   %>
							
							    <tr>
							      <th scope="row"><%=i%></th>
							      <td><%=ad.getCurrentMatchs().getString("gameName")%></td>
							      <td><%=ad.getCurrentMatchs().getString("pseudo")%></td>
							          <td><%=ad.getCurrentMatchs().getString("hBegin")%></td>
							      
							     <td>
							     	<form action="/J2EE/admin" method="post">
										<input type="hidden" value="end"  name="option" />
										<button  type="submit" class="btn btn-primary btn-sm" value= <%= ad.getCurrentMatchs().getString("idMatch") %> name="id"  >  End the game </button>	 	   
										</form>
							      </td>
							    </tr>
							    
							    <%   	}    %>
							 
							  </tbody>
						</table>
						
						
				</div>
				</article>
				
						
						
					</div>


		<!-- Finished Plays  -->
			<div class="wrapper style4">
				<article id="contact" class="container 75%">
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
							  	int j = 0;
							  %>
							   <%
							   	while (ad.getFinishedMatchs().next()) { 
							   					    j++;
							   %>
							
							    <tr>
							      <th scope="row"><%=j%></th>
							      <td><%=ad.getFinishedMatchs().getString("gameName")%></td>
							      <td><%=ad.getFinishedMatchs().getString("pseudo")%></td>
							      <td><%=ad.getFinishedMatchs().getString("hBegin")%></td>     
							     <td><%=ad.getFinishedMatchs().getString("hEnd")%></td>
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
							
						
						
					</div>
					<footer>
						<ul id="copyright">
							<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
						</ul>
					</footer>
				</article>
			</div>

<!-- Scripts -->
			<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/jquery.scrolly.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/skel.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/skel-viewport.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="<%=request.getContextPath()%>/assets/js/main.js"></script>

	</body>
</html>
	
	
	