<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="servlets.Admin"%>
<% Admin ad = new Admin();%>



<html>
<html>
	<head>
		<title>Miniport by HTML5 UP</title>
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
					<li><a href="#portfolio">Portfolio</a></li>
					<li><a href="#contact">Contact</a></li>
					<li><a href="#">LogOut</a></li>
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

		<!-- Portfolio -->
			<div class="wrapper style3">
				<article id="portfolio">
					<header>
						<h2>Here’s some stuff I made recently.</h2>
						<p>Proin odio consequat  sapien vestibulum consequat lorem dolore feugiat lorem ipsum dolore.</p>
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
							      <td><%= ad.getPlayers().getString("pseudo") %></td>
							      <td><%= ad.getPlayers().getString("ban") %></td>
							      <td>@mdo</td>
							     <td> <button  type="button" class="btn btn-primary btn-sm">  Ban :  click to unbann </button>
							    </tr>
							    
							    <% } %>
							 
							  </tbody>
						</table>
					
					</div>
					<footer>
						<p>Lorem ipsum dolor sit sapien vestibulum ipsum primis?</p>
						<a href="#contact" class="button big scrolly">Get in touch with me</a>
					</footer>
				</article>
			</div>

		<!-- Contact -->
			<div class="wrapper style4">
				<article id="contact" class="container 75%">
					<header>
						<h2>Have me make stuff for you.</h2>
						<p>Ornare nulla proin odio consequat sapien vestibulum ipsum sed lorem.</p>
					</header>
					<div>
						<div class="row">
							<div class="12u">
								<form method="post" action="#">
									<div>
										<div class="row">
											<div class="6u 12u(mobile)">
												<input type="text" name="name" id="name" placeholder="Name" />
											</div>
											<div class="6u 12u(mobile)">
												<input type="text" name="email" id="email" placeholder="Email" />
											</div>
										</div>
										<div class="row">
											<div class="12u">
												<input type="text" name="subject" id="subject" placeholder="Subject" />
											</div>
										</div>
										<div class="row">
											<div class="12u">
												<textarea name="message" id="message" placeholder="Message"></textarea>
											</div>
										</div>
										<div class="row 200%">
											<div class="12u">
												<ul class="actions">
													<li><input type="submit" value="Send Message" /></li>
													<li><input type="reset" value="Clear Form" class="alt" /></li>
												</ul>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="row">
							<div class="12u">
								<hr />
								<h3>Find me on ...</h3>
								<ul class="social">
									<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
									<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
									<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
									<li><a href="#" class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
									<li><a href="#" class="icon fa-tumblr"><span class="label">Tumblr</span></a></li>
									<li><a href="#" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
									<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
									<!--
									<li><a href="#" class="icon fa-rss"><span>RSS</span></a></li>
									<li><a href="#" class="icon fa-instagram"><span>Instagram</span></a></li>
									<li><a href="#" class="icon fa-foursquare"><span>Foursquare</span></a></li>
									<li><a href="#" class="icon fa-skype"><span>Skype</span></a></li>
									<li><a href="#" class="icon fa-soundcloud"><span>Soundcloud</span></a></li>
									<li><a href="#" class="icon fa-youtube"><span>YouTube</span></a></li>
									<li><a href="#" class="icon fa-blogger"><span>Blogger</span></a></li>
									<li><a href="#" class="icon fa-flickr"><span>Flickr</span></a></li>
									<li><a href="#" class="icon fa-vimeo"><span>Vimeo</span></a></li>
									-->
								</ul>
								<hr />
							</div>
						</div>
					</div>
					<footer>
						<ul id="copyright">
							<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
						</ul>
					</footer>
				</article>
			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/skel-viewport.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>
	
	<section id="two" class="main style2">
	<div class="container">
		<div class="row 150%">
			<div class="6u 12u$(medium)">
				<ul class="major-icons">
					<li><span class="icon style1 major fa-code"></span></li>
					<li><span class="icon style2 major fa-bolt"></span></li>
					<li><span class="icon style3 major fa-camera-retro"></span></li>
					<li><span class="icon style4 major fa-cog"></span></li>
					<li><span class="icon style5 major fa-desktop"></span></li>
					<li><span class="icon style6 major fa-calendar"></span></li>
				</ul>
			</div>
			<div class="6u$ 12u$(medium)">
				<header class="major"> 

				<h2>
	
				</h2>
				</header>
				<p></p>
				<p>Blandit faucibus proin. Ac aliquam integer adipiscing enim
					non praesent vis commodo nunc phasellus cubilia ac risus accumsan.
					Accumsan blandit. Lobortis phasellus non lobortis dit varius mi
					varius accumsan lobortis. Blandit ante aliquam lacinia lorem
					lobortis semper morbi col faucibus vitae integer placerat accumsan
					orci eu mi odio tempus adipiscing adipiscing adipiscing curae
					consequat feugiat etiam dolore.</p>
				<p>Adipiscing a commodo ante nunc accumsan interdum mi ante
					adipiscing. A nunc lobortis non nisl amet vis volutpat aclacus
					nascetur ac non. Lorem curae eu ante amet sapien in tempus ac.
					Adipiscing id accumsan adipiscing ipsum.</p>
			</div>
		</div>
	</div>
	</section>

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