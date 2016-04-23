var wsUri = "ws://" + document.location.host + "/cinemaSocket/5";
var websocket = new WebSocket(wsUri);

websocket.onmessage = function(evt) { onMessage(evt) };
websocket.onerror = function(evt) { onError(evt) };
websocket.onopen = function(evt) { onOpen(evt) };



function onMessage(evt) {
 console.log("received over websockets: " + evt.data);
 console.log("looked for room index of: "+ evt.data.indexOf("room"));
 var index = evt.data.indexOf("room");
 writeToScreen(evt.data);
    if (index>1) {
      console.log("found room index of: "+ evt.data.indexOf("room"));   
      updateRoomDetails( evt.data);
    }
}


function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}


// For testing purposes
var output = document.getElementById("output");

function writeToScreen(message) {
    if (output==null) {
        output = document.getElementById("output");
    }
    //output.innerHTML += message + "<br>";
    output.innerHTML = message + "<br>";
}

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
                

