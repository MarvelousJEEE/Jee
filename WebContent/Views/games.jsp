<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>page de jeux</title>
</head>
<body>
	<h1>
		Bonjour
		<%=request.getAttribute("pseudo")%>, amusez vous bien !
	</h1>


	<br>
	<br>
	<br>
	<fieldset>
		<h2> Game 1</h2>
		<input type="submit" value="Play"  onclick="play('<%=request.getParameter("pseudo")%>','Game1')" size="300px"/>
		<input type="submit" value="Stop"  onclick="stop('<%=request.getParameter("pseudo")%>','Game1')" size="300px"/>
	</fieldset>
<script type="text/javascript">

var compteur=0  // Compteur pour que l'utilisateur ne puisse pas appuyer sur Play ou Stop plus de une fois d'affiler 

function play(pseudo,game) {
	if(compteur==0) {	
		var req = newXMLHttpRequest();
		req.open("POST", "servletgames", true);
		req.onreadystatechange = getReadyStateHandler();
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("variable1="+pseudo+"&"+"variable2="+game+"&"+"variable3=play");	
		compteur++ }
	else {
		alert('Vous êtes déjà en train de jouer')
	}
}

function stop(pseudo,game) {
	if(compteur==1) {
		var req = newXMLHttpRequest();
		req.open("POST", "servletgames", true);
		req.onreadystatechange = getReadyStateHandler();
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("variable1="+pseudo+"&"+"variable2="+game+"&"+"variable3=stop");	
		compteur--;}
	else {
		alert('Vous devez commencez une partie')
	}
	 

} 


	function newXMLHttpRequest() {
		  var xmlreq = false;
		 
		  // Create XMLHttpRequest object in non-Microsoft browsers
		  if (window.XMLHttpRequest) {
		    xmlreq = new XMLHttpRequest();
		  } else if (window.ActiveXObject) {
		    try {
		      // Try to create XMLHttpRequest in later versions
		      // of Internet Explorer
		      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");		      
		    } catch (e1) {
		      // Failed to create required ActiveXObject		      
		      try {
		        // Try version supported by older versions
		        // of Internet Explorer		      
		        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
		      } catch (e2) {
		        // Unable to create an XMLHttpRequest by any means
		        xmlreq = false;
		      }
		    }
		  }
		return xmlreq;
		}
	
	 function getReadyStateHandler() {

		   // Return an anonymous function that listens to the XMLHttpRequest instance
		   return function () {

		     // If the request's status is "complete"
		     if (req.readyState == 4) {
		       
		       // Check that we received a successful response from the server
		       if (req.status == 200) {

		         // Pass the XML payload of the response to the handler function.
		         //responseXmlHandler(req.responseXML);

		/*var i=0;

		 function play(x,y) {
		 i++
		 if(i>1) {
		 alert("Vous êtes déjà en train de jouer")
		 i--;
		 }
		 else {
		 // ajout de la partie en base 
		 }
		 alert(x+y)
		 }


		 function stop() {
		 /*if(i!=1) {
		 alert("Vous n'avez pas commencé de partie")
		 }
		 else {
		 // modification de la base 
		 i--;
		 }
		 }*/
	</script>
















</body>
</html>
