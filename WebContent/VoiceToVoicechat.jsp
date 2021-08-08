<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Voice To Voice</title>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
<link rel="stylesheet" href="TextToTextchat.css">

<script type="text/javascript">




<% String botResponse = (String)request.getAttribute("botResponse"); 

if(botResponse != null){
	
	System.out.println("Bot:"+botResponse); 
}


if(botResponse == null){

	botResponse = "";
	
}%>

var BotResponse = "<%=botResponse%>";

setTimeout(() => playByText("en-US", BotResponse), 300);

    




	
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
			ajaxRequest.open('get', 'VoiceToVoice');
			ajaxRequest.send();
			
		
			setTimeout(getChats, 10);
			
			
		}
		
		let _speechSynth
		let _voices
		const _cache = {}

		/**
		 * retries until there have been voices loaded. No stopper flag included in this example. 
		 * Note that this function assumes, that there are voices installed on the host system.
		 */

		function loadVoicesWhenAvailable (onComplete = () => {}) {
		  _speechSynth = window.speechSynthesis
		  const voices = _speechSynth.getVoices()

		  if (voices.length !== 0) {
		    _voices = voices
		    onComplete()
		  } else {
		    return setTimeout(function () { loadVoicesWhenAvailable(onComplete) }, 100)
		  }
		}

		/**
		 * Returns the first found voice for a given language code.
		 */

		function getVoices (locale) {
		  if (!_speechSynth) {
		    throw new Error('Browser does not support speech synthesis')
		  }
		  if (_cache[locale]) return _cache[locale]

		  _cache[locale] = _voices.filter(voice => voice.lang === locale)
		  return _cache[locale]
		}

		/**
		 * Speak a certain text 
		 * @param locale the locale this voice requires
		 * @param text the text to speak
		 * @param onEnd callback if tts is finished
		 */

		function playByText (locale, text, onEnd) {
		  const voices = getVoices(locale)

		  // TODO load preference here, e.g. male / female etc.
		  // TODO but for now we just use the first occurrence
		  const utterance = new window.SpeechSynthesisUtterance()
		  utterance.voice = voices[0]
		  utterance.pitch = 1
		  utterance.rate = 1
		  utterance.voiceURI = 'native'
		  utterance.volume = 1
		  utterance.rate = .9	
		  utterance.pitch = .1
		  utterance.text = text
		  utterance.lang = locale

		  if (onEnd) {
		    utterance.onend = onEnd
		  }

		  _speechSynth.cancel() // cancel current speak, if any is running
		  _speechSynth.speak(utterance)
		}

		// on document ready
		loadVoicesWhenAvailable(function () {
		 console.log("loaded") 
		})

		
		
		var recognition = new webkitSpeechRecognition();
		recognition.continuous = true;
		//recognition.interimResults = true;
		function start(){
			recognition.onresult = function(event) { 
				console.log(event);
				var output = document.getElementById("output");
				
				
				output.innerHTML = "";
				for(var i=0; i<event.results.length; i++){
					output.innerHTML = output.innerHTML + event.results[i][0].transcript;
					
					
				}
			}
			recognition.start();
		}
		
		
		function process() {
			  document.getElementById("hidden").value = document.getElementById("output").innerHTML;
			  return true;
			}
		
function doSubmit(){
			
			myForm.submit();
			
			}
		
	</script>
</head>

<style>
@import
	url("https://fonts.googleapis.com/css2?family=Sansita+Swashed:wght@600&display=swap")
	;

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

.center .inputbox input:focus ~ span, .center .inputbox input:valid ~
	span {
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

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 7px 14px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
}

.button1 {
	background-color: red; /* Green */
	border: none;
	color: white;
	padding: 7px 13px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
}

.button:hover {
	background-color: purple; /* Green */
	border: none;
	color: white;
	padding: 7px 14px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
}

.button1:hover {
	background-color: purple; /* Green */
	border: none;
	color: white;
	padding: 7px 13px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
}
</style>

<body onload="getChats()">

	<%System.out.println("test"); %>

	<h1 style="color: white;">AI Voice to Voice Chat</h1>
	<hr />
	<div id="chats"></div>
	<hr />
	<div class="center">
		<input id="bStart" class="button1" type="button" value="Start "
			onclick="start();"></input>
		<form method="post" action="VoiceToVoice" class="my-form"
			onsubmit="javascript: return process();">
			<input type="hidden" id="hidden" name="output">
			<div contenteditable="inherit" id="output"></div>
			<input class="button" class="button1 bouncy" value="Send"
				type="submit"></input>
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