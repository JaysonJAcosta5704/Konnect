//var ws;
//
//function connect() {
//    var username = document.getElementById("username").value;
//    var wsserver = document.getElementById("wsserver").value;
//    var url = wsserver + username;
//    //var url = "ws://echo.websocket.org";
//
//    ws = new WebSocket(url);
//
//    ws.onmessage = function(event) { // Called when client receives a message from the server
//        console.log(event.data);
//
//        // display on browser
//        var log = document.getElementById("log");
//        log.innerHTML += "message from server: " + event.data + "\n";
//    };
//
//    ws.onopen = function(event) { // called when connection is opened
//        var log = document.getElementById("log");
//        log.innerHTML += "Connected to " + event.currentTarget.url + "\n";
//    };
//}
//
//function send() {  // this is how to send messages
//    var content = document.getElementById("msg").value;
//    ws.send(content);
//}
var ws;
var typingTimeout; // Timer to manage typing status notifications

function connect() {
    var username = document.getElementById("username").value;
    var wsserver = document.getElementById("wsserver").value;
    var url = wsserver + username;

    ws = new WebSocket(url);

    ws.onmessage = function(event) {
        console.log(event.data);

        // Display on browser
        var log = document.getElementById("log");
        log.innerHTML += "Message from server: " + event.data + "\n";

        // Automatically scroll to the bottom of the log
        log.scrollTop = log.scrollHeight;
    };

    ws.onopen = function(event) {
        var log = document.getElementById("log");
        log.innerHTML += "Connected to " + event.currentTarget.url + "\n";
    };

    // Handle the WebSocket close event
    ws.onclose = function(event) {
        var log = document.getElementById("log");
        log.innerHTML += "Disconnected from the server.\n";
    };

    // Setup event listener for typing indication
    var msgInput = document.getElementById("msg");
    msgInput.addEventListener('input', function() {
        notifyTyping();
    });
}

function send() {
    var content = document.getElementById("msg").value;
    if(content) { // Ensure non-empty message
        ws.send(content);
        document.getElementById("msg").value = ''; // Clear input after sending
    }
}

function notifyTyping() {
    clearTimeout(typingTimeout);

    // Send typing notification if user starts typing but throttle the notifications
    typingTimeout = setTimeout(function() {
        ws.send("/typing");
    }, 500);
}
