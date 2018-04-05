<%@ page import="servlets.Admin"%>
<% Admin ad = new Admin();%>
<%@include file="header.jsp"%>


		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="#top">Game List</a></li>
					<li><a href="#work">Add Game </a></li>
					<li><a href="#portfolio"> Players </a></li>
					<li><a href="#contact"> Plays </a></li>
					<li><a href="#copyright"> Contactez-nous </a></li>
					<li><a href="#" id="logout">LogOut</a></li>
				</ul>
			</nav>

		<!-- Game List  -->
			<div class="wrapper style1 first">
				<article class="container" id="top">
					<div class="row">
						<div class="4u 12u(mobile)">
							<span class="image fit"><img src="<%=request.getContextPath()%>/images/pic00.jpg" alt="" /></span>
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
							  %>
							   <%	while (ad.getPlayers().next()) { 
							    k++;
							   %>
							  
							    <tr>
							      <th scope="row"><%=k %></th>
							      <td> <%= ad.getPlayers().getString("pseudo") %> </td>
							         <td> <%= ad.getPlayers().getString("subscription") %> </td>
							      
								  <td> N.A </td> 
							     <td> <button  type="button" class="btn btn-primary btn-sm">  <%= ad.getPlayers().getString("ban") %> </button>
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
					
					</div>
				
				</article>
			</div>



		<!-- Contact -->
			<div class="wrapper style3">
				<article id="contact" class="container 75%">
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
							   <%	while (ad.getMatchs().next()) { 
							    i++;
							   %>
							
							    <tr>
							      <th scope="row"><%=i %></th>
							      <td><%= ad.getMatchs().getString("gameName") %></td>
							      <td><%= ad.getMatchs().getString("pseudo") %></td>
							          <td><%= ad.getMatchs().getString("hBegin") %></td>
							      
							     <td> <button  type="button" class="btn btn-primary btn-sm">  End then game  </button>
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
					</div>
				</article>
			</div>
<%@include file="footer.jsp"%>


	
	