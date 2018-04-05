<%@include file="header.jsp"%>


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


		<!-- Add Game  -->
			<div class="wrapper style1 first">
				<article id="games">
					<fieldset>
						<label>Game 1</label>
						<input type="submit" value="Play" onclick="play('<%=request.getParameter("pseudo")%>','Game1')"/>
						<input type="submit" value="Stop" onclick="stop('<%=request.getParameter("pseudo")%>','Game1')"/>
					</fieldset>
				</article>
			</div>



<script src="<%=request.getContextPath()%>/assets/js/games.js"></script>
		
<%@include file="footer.jsp"%>



