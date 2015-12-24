$( document ).ready(function() {
	// onSign();
});

function buttonClicked(){
	var canvas = document.getElementById("sigcanvas");
	alert('it works'+canvas);
	var img    = canvas.toDataURL("image/png");
	alert(img);
	document.write('<img src="'+img+'"/>');
}


function onSign()
{
	debugger;
   var ctx = document.getElementById('sigcanvas').getContext('2d');         
   SetDisplayXSize( 500 );
   SetDisplayYSize( 100 );
   SetJustifyMode(0);
   ClearTablet();
   tmr = SetTabletState(1, ctx, 50) || tmr;
}



function retrieveSignature2(){
	debugger;
	var canvas = document.getElementById("sigcanvas");
//	alert('it works'+canvas);
	var imgURL    = canvas.toDataURL();
	//alert('it works'+imgURL);

	
	$.ajax({
		  type: "POST",
		  url: "imageservlet",
		  data: "base64="+imgURL,
		  success: function(msg){
		        alert( "Image Saved: /temp/abbeyxx.png");
		  },
		  error: function(xhr, textStatus, errorThrown) {
			  alert(xhr.responseText);
		  }
		});
	
}



function retrieveSignature(){
	var canvasServer = document.getElementById("sigcanvas");
    var context = canvasServer.getContext("2d");                    
    var imageDataURL = canvasServer.toDataURL('image/png');
    var ajax = new XMLHttpRequest();
    ajax.open("POST","imageservlet",false);
    ajax.setRequestHeader("Content-Type", "application/upload");
    ajax.send(imageDataURL);

}