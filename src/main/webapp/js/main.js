//Validate if the browser support WebSockets
if ("WebSocket" in window) {
	var webSocket = new WebSocket('ws://localhost:8080/count/websocket');

	webSocket.onerror = function(event) {
		onError(event);
	};

	webSocket.onopen = function(event) {
		onOpen(event);
	};

	webSocket.onmessage = function(event) {
		onMessage(event);
	};

	webSocket.onclose = function() {
		onClose();
	};

} else {
	document.getElementById('countValue').innerHTML = "This Browser is not supported!";
}
// End validate if the browser support WebSockets

function onMessage(event) {
	var obj = JSON.parse(event.data);
	document.getElementById('countValue').innerHTML = obj.countValue;
}

function onOpen(event) {
	console.log("Connection established");
}

function onClose(event) {
	console.log("Connection closed");
}

function onError(event) {
	alert(event.data);
}

function count() {
	webSocket.send("Count");
	return false;
}