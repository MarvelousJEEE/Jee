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
	<input type="submit" value="Play" onclick="play()" size="300px" />
	<input type="submit" value="Stop" onclick="stop()" size="300px" />

	<script type="text/javascript">
		function play() {

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
							alert('ok')

						} else {

							// An HTTP problem has occurred
							alert("HTTP error " + req.status + ": "
									+ req.statusText);
						}
					}
				}
			}

			var req = newXMLHttpRequest();

			req.open("POST", "servletgames", true);
			req.onreadystatechange = getReadyStateHandler();
			req.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			req.send("variable1=geoffrey");

		}

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