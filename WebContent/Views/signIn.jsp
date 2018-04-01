<%@include file="header.jsp"%>


<form method="post" action="/J2EE/signin">
	<fieldset>
		<legend>SignIn</legend>
		<label for="pseudo">Pseudo</label>
		<input type="text" id="pseudo" name="pseudo" value="" size="20" maxlength="60"/>
		<label for="password">Mot de passe</label>
		<input type="password" id="password" name="password" value="" size="20" maxlength="60"/>
		<input type="submit" value="Connexion"/>
	</fieldset>
</form>
<form method="post" action="/J2EE/Views/signUp.jsp">
	<fieldset>
		<legend>SignUp</legend>
		<input type="submit" value="Inscription"/>
	</fieldset>
</form>





<%@include file="footer.jsp"%>
