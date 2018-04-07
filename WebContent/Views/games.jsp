
<%@include file="header.jsp"%>
<%@ page import="servlets.SessionTools" %>
<%@ page import="bdd.GestionBDD" %>
<%@ page import="java.sql.ResultSet" %>


		
		<h1>
			Bonjour
			<%=request.getParameter("pseudo")%>, amusez vous bien !
		</h1>
		<br>
		<br>
		<br>
			<% String[] tableGames = (String[])request.getAttribute("Games");
			   int lenTable = tableGames.length;
			   int i=0;
			   for(i=0;i<lenTable;i++){%>

		<fieldset>
			<h2><%=tableGames[i] %></h2>
			<input type="submit" value="Play"
				onclick="play('<%=request.getParameter("pseudo")%>','<%=tableGames[i] %>','<%=i %>')"
				size="300px" /> <input type="submit" value="Stop"
				onclick="stop('<%=request.getParameter("pseudo")%>','<%=tableGames[i] %>','<%=i %>')"
				size="300px" />
		</fieldset>



			 <%  };%>
	
		
		
		<script type="text/javascript">
			var compteurTable = new Array() ; // tableau de compteur pour que l'utilisateur ne puisse pas appuyer sur Play ou Stop plus de une fois d'affiler 
			var j=0;
			for(j=0;j<<%=tableGames.length%>;j++){
				compteurTable[j]=0;			// initialisation de tous les compteurs de jeu à 0.
			}
			function play(pseudo, game, numeroJeu) {
				if (compteurTable[numeroJeu] == 0) {
					var k=0;
					var req = newXMLHttpRequest();
					req.open("POST", "servletgames", true);
					req.onreadystatechange = getReadyStateHandler();
					req.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					req.send("variable1=" + pseudo + "&" + "variable2=" + game
							+ "&" + "variable3=play");
					for(k=0;k<<%=tableGames.length%>;k++){
						if(numeroJeu==k){
							compteurTable[k]=1;			// 1 siginifie que le jeu va commencé
						}
						else {
							compteurTable[k]=-1;			// -1 signifie qu'un autre jeu est déjà lancé
						}
					}
				}
				else if(compteurTable[numeroJeu] == -1){
					alert('Vous ne pouvez pas jouer à deux jeux à la fois');
				}
				else {
					alert('Vous êtes déjà en train de jouer à ce jeu');
				}
			}
	
			function stop(pseudo, game, numeroJeu) {
				if (compteurTable[numeroJeu] == 1) {
					var k=0;
					var req = newXMLHttpRequest();
					req.open("POST", "servletgames", true);
					req.onreadystatechange = getReadyStateHandler();
					req.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					req.send("variable1=" + pseudo + "&" + "variable2=" + game
							+ "&" + "variable3=stop");
					for(k=0;k<<%=tableGames.length%>;k++){
							compteurTable[k]=0;			// 0 siginifie que le jeu peut être lancé
						};
				} 
				else {
					alert('Vous ne jouez pas à ce jeu ')
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
				return function() {
					// If the request's status is "complete"
					if (req.readyState == 4) {
	
						// Check that we received a successful response from the server
						if (req.status == 200) {
							// Pass the XML payload of the response to the handler function.
							//responseXmlHandler(req.responseXML);
						} else {
							// An HTTP problem has occurred
							alert("HTTP error " + req.status + ": "
									+ req.statusText);
						}
					}
				}
			}
		</script>
	
<%@include file="footer.jsp"%>
