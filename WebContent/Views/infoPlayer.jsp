<%@include file="header.jsp"%>

		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="/J2EE/games">Games</a></li>
					<li><a href="#copyright">Contactez-nous</a>
					<li><a href="#" id="logout">LogOut</a></li>
				</ul>
			</nav>

	<%System.out.println("Pseudo => "+request.getAttribute("pseudo"));%>
		<!-- Password  -->
			<div class="wrapper style1 first">
				<article class="container" id="password">
					<div class="row">
						<form method="post" action="/J2EE/infoPlayer">
							<fieldset>
								<h1>Change your password</h1>
								<label for="Password">Old password</label>
								<input type="password" id="password" name="password" value="" size="20" maxlength="60"/>
								<label for="NewPassword">New password</label>
								<input type="password" id="NewPassword" name="NewPassword" value="" size="20" maxlength="60"/>
								<label for="RepeatPassword">Repeat new password</label>
								<input type="password" id="RepeatPassword" name="RepeatPassword" value="" size="20" maxlength="60"/>
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
							<fieldset>
								<h1>Change your email</h1>
								<label for="newEmail">email</label><br>
								<input type="text" id="newEmail" name="newEmail" value=<%=request.getAttribute("email")%> size="20" maxlength="60"/><br>
								<input type="hidden" value= <%=request.getAttribute("pseudo")%>  name="pseudo"/>
								<input type="hidden" value="email" name="option" /> 
								<input type="submit" value="Valider"/><br>
							</fieldset>
						</div>
					</form>			
				</article>
			</div>
		 -->


<%@include file="footer.jsp"%>	
	