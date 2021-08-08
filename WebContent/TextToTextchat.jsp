<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<title>Text To Text</title>

<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
<link rel="stylesheet" href="TextToTextchat.css">

<script>



    




	
		function getChats(){
			
			document.getElementById( 'bottom' ).scrollIntoView();
		    window.setTimeout( function () { top(); }, 2000 );
			
			var delayInMilliseconds = 1000;
			
			var ajaxRequest = new XMLHttpRequest();
			ajaxRequest.onreadystatechange = function(){
			
				if(ajaxRequest.readyState == 4){
					//the request is completed, now check its status
					if(ajaxRequest.status == 200){
						document.getElementById("chats").innerHTML = ajaxRequest.responseText;
						
						
					}
					else{
						console.log("Status error: " + ajaxRequest.status);
					}
				}
				else{
					console.log("Ignored readyState: " + ajaxRequest.readyState);
				}
			}
			ajaxRequest.open('get', 'TextToText');
			ajaxRequest.send();
			
			//refresh the chats in one second
			setTimeout(getChats, 10);
			
			
			
		}
		
		
		function doSubmit(){
			
			
			
			myForm.submit();
			
			
			
			}
		

		
		
			
			
		
	</script>
</head>

<style>

@import url("https://fonts.googleapis.com/css2?family=Sansita+Swashed:wght@600&display=swap");

.center {
  position: relative;
  padding: 50px 50px;
  background: #fff;
  border-radius: 10px;
}
.center h1 {
  font-size: 2em;
  border-left: 5px solid dodgerblue;
  padding: 10px;
  color: #000;
  letter-spacing: 5px;
  margin-bottom: 60px;
  font-weight: bold;
  padding-left: 10px;
}
.center .inputbox {
  position: relative;
  width: 300px;
  height: 50px;
  margin-bottom: 50px;
}
.center .inputbox input {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  border: 2px solid #000;
  outline: none;
  background: none;
  padding: 10px;
  border-radius: 10px;
  font-size: 1.2em;
}
.center .inputbox:last-child {
  margin-bottom: 0;
}
.center .inputbox span {
  position: absolute;
  top: 14px;
  left: 20px;
  font-size: 1em;
  transition: 0.6s;
  font-family: sans-serif;
}
.center .inputbox input:focus ~ span,
.center .inputbox input:valid ~ span {
  transform: translateX(-13px) translateY(-35px);
  font-size: 1em;
}
.center .inputbox [type="button"] {
  width: 50%;
  background: dodgerblue;
  color: #fff;
  border: #fff;
}
.center .inputbox:hover [type="button"] {
  background: purple;
}


</style>

<body onload="getChats()">


	<h1 style="color:white;" >AI Text to Text Chat</h1>
	<hr />
	<div id="chats"></div>
	<hr />
	<div class="center">
	<form onsubmit="return doSomething();" class="my-form"
		action="TextToText" method="post">
		<div class="inputbox">
      <input type="text" name="message" required="required">
      <span>Say Something...</span>
    </div>
	</form>
	
	<form action="home" method="post" name="myForm">
	<div class="inputbox">
      <input type="button" value="Go to Home" onclick="doSubmit();" />
    </div>
	</form>
	
	</div>
	<div id="bottom"></div>

</body>

</html>