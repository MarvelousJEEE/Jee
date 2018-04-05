(function(){
	var elt = document.getElementById('logout');

    elt.onclick = function() {
    	var redirection = "http://"+window.location.host+"/J2EE/signin";
    	document.location.href=redirection;
    };
})();