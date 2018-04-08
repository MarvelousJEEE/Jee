<%@include file="header.jsp"%>

		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
				<!-- 
					<li><a href="#signin">SignIn</a></li>
					<li><a href="#signup">SignUp</a></li>
					<li><a href="#copyright">Contactez-nous</a>
				 -->
				</ul>
			</nav>

	<%System.out.println("Pseudo => "+request.getAttribute("pseudo"));%>
		<!-- Password  -->
			<div class="wrapper style1 first">
				<article class="container" id="signin">
					<div class="row">
						<form method="post" action="/J2EE/infoPlayer">
							<fieldset>
								<legend>Change your password</legend>
								<label for="Password">Older password</label>
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

		<!-- Sign Up  -->
		<!-- 
			<div class="wrapper style2">
				<article class="container" id="signup">
					<form method="post" action="/J2EE/signup">
						<div class="row" align="center">
							<fieldset>
								<legend>SignUp</legend>
								<label for="pseudo">Login</label><br>
								<input type="text" id="pseudo" name="pseudo" value="" size="20" maxlength="60"/><br>
								<label for="password">Password</label><br>
								<input type=password id="password" name="password" value="" size="20" maxlength="60"/><br>
								<label for="Verif">Password verification</label><br>
								<input type="password" id="Verif" name="Verif" value="" size="20" maxlength="60"/><br>
								<label for="dateOfBirth">Date of birth</label><br>
								<input type="date" id="dateOfBirth" name="dateOfBirth" value="" size="20" maxlength="60"/><br>
								<label for="email">Email</label><br>
								<input type="email" id="email" name="email" value="" size="20" maxlength="60"/><br>
								<input type="submit" value="Inscription"/><br>
							</fieldset>
						</div>
					</form>			
				</article>
			</div>
		 -->


<%@include file="footer.jsp"%>	
	