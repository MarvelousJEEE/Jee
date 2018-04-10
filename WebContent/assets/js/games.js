//var compteur = 0  Compteur pour que l'utilisateur ne puisse pas appuyer sur Play ou Stop plus de une fois d'affiler 
	
			function play(pseudo, game, numeroJeu) {
				if (compteurTable[numeroJeu] == 0) {
					var k=1;
					var req = newXMLHttpRequest();
					alert("vous jouez à "+game);
					req.open("POST", "games", true);
					req.onreadystatechange = getReadyStateHandler(req);
					req.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					req.send("variable1=" + pseudo + "&" + "variable2=" + game
							+ "&" + "variable3=play");
					for(k=1;k<=size;k++){
						if(numeroJeu==k){
							compteurTable[k]=1;			// 1 siginifie que le jeu va commencé
						}
						else {
							compteurTable[k]=-1;			// -1 signifie qu'un autre jeu est déjà lancé
						}
					}
				}
				else if(compteurTable[numeroJeu] == -1){
						alert('Vous ne pouvez pas jouer à deux jeux à la fois, stopper le jeu en cours !');
				} else {
					alert('Vous êtes déjà en train de jouer à ' + game + '!');
				}
			}
	
			function stop(pseudo, game, numeroJeu) {
				if (compteurTable[numeroJeu] == 1) {
					var k=1
					var req = newXMLHttpRequest();
					req.open("POST", "games", true);
					req.onreadystatechange = getReadyStateHandler();
					req.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded");
					req.send("variable1=" + pseudo + "&" + "variable2=" + game
							+ "&" + "variable3=stop");
					alert('Vous avez fini de jouer à '+game);
					for(k=1;k<=size;k++){
						compteurTable[k]=0;			// 0 siginifie que le jeu peut être lancé
					};
				} else {
					alert('Vous ne jouez pas à ce jeu, vous jouez déjà à un autre jeu !')				}
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
	
			function getReadyStateHandler(req) {
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