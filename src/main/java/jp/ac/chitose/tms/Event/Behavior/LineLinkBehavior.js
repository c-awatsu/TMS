function onover(id){
	var element = document.getElementById(id);
	var attr = element.getAttribute("class");
	if(attr !== null){
		element.setAttribute("class", attr + " hover");
	}
	else {
		element.setAttribute("class", "hover");
	}
}

function onout(id){
	var element = document.getElementById(id);
	var attr = element.getAttribute("class").split(" ");
	for(var i = 0; i < attr.length; i++){
		if(attr[i] === "hover"){
			attr.splice(i,1);
			break;
		}
	}
	if(attr.length > 0)element.setAttribute("class", attr.join(" "));
	else element.removeAttribute("class");
}