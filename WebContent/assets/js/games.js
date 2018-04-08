
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
					for(k=0;k<size;k++){
						if(numeroJeu==k){
							compteurTable[k]=1;			// 1 siginifie que le jeu va commencÃ©
						}
						else {
							compteurTable[k]=-1;			// -1 signifie qu'un autre jeu est dÃ©jÃ  lancÃ©
						}
					}
				}
				else if(compteurTable[numeroJeu] == -1){
					alert('Vous ne pouvez pas jouer Ã  deux jeux Ã  la fois');
				}
				else {
					alert('Vous Ãªtes dÃ©jÃ  en train de jouer Ã  ce jeu');
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
					for(k=0;k<size;k++){
							compteurTable[k]=0;			// 0 siginifie que le jeu peut Ãªtre lancÃ©
						};
				} 
				else {
					alert('Vous ne jouez pas Ã  ce jeu ')
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
