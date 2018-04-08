<%@include file="header.jsp"%>

		<!-- Nav -->
			<nav id="nav">
				<ul class="container">
					<li><a href="#signin">SignIn</a></li>
					<li><a href="#signup">SignUp</a></li>
					<li><a href="#copyright">Contactez-nous</a>
				</ul>
			</nav>

		<!-- Sign In  -->
			<div class="wrapper style1 first">
				<article class="container" id="signin">
					<div class="row">
						<form method="post" action="/J2EE/signin">
							<fieldset>
								<h1>SignIn</h1>
								<label for="pseudo">Pseudo</label>
								<input type="text" id="pseudo" name="pseudo" value="" size="20" maxlength="60"/>
								<label for="password">Mot de passe</label>
								<input type="password" id="password" name="password" value="" size="20" maxlength="60"/>
								<input type="submit" value="Connexion"/>
							</fieldset>
						</form>	
					</div>
				</article>
			</div>

		<!-- Sign Up  -->
			<div class="wrapper style2">
				<article class="container" id="signup">
					<form method="post" action="/J2EE/signup">
						<div class="row" align="center">
							<fieldset>
								<h1>SignUp</h1>
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


<%@include file="footer.jsp"%>	
	
	
	
