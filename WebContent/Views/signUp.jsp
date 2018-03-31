<%@include file="header.jsp"%>

<form method="post" action="/J2EE/signup">
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
</form>

<%@include file="footer.jsp"%>"