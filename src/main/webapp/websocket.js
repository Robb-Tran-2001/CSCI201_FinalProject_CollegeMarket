// var ws;

// function connect() {
//     var username = document.getElementById("username").value;
    
//     var host = document.location.host;
//     var pathname = document.location.pathname;

//     var path_arr = pathname.split("/")

//     var path = path_arr[1]
    
//     ws = new WebSocket("ws://" + host + "/" + path + "/push/" + username);

//     ws.onmessage = function(event) {
//     //var log = document.getElementById("log");
//         // console.log(event.data);
//         var message = JSON.parse(event.data);
//         // log.innerHTML += message.from + " : " + message.content + "\n";

//         alert(message.msg);
//     };
// }

// function send() {
//     // var content = document.getElementById("msg").value;
//     // var json = JSON.stringify({
//     //     "content":content
//     // });

//     // ws.send(json);

//     var itemID = document.getElementById("itemID").value;
//     var username = document.getElementById("username").value;

//     var xhttp = new XMLHttpRequest();

//     xhttp.open("PUT", "http://localhost:8080/marketplace-service/api/items/" + username + "/sell/" + itemID, true)
//     xhttp.send();
// }

// src="resources/js/sockjs-0.3.4.js"
// src="resources/js/stomp.js"


function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
}

function connect() {
    var socket = new SockJS('/push_notif');
    stompClient = Stomp.over(socket);  
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            //showMessageOutput(JSON.parse(messageOutput.body));
            alert("Item with ID " + JSON.parse(messageOutput.body).itemID + " was bought by " + JSON.parse(messageOutput.body).buyer + ". Better luck next time!");
        });
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendBoughtMessage() {
    var itemID = document.getElementById("itemID").value;
    var username = document.getElementById("username").value;
    stompClient.send("/app/push_notif", {}, 
      JSON.stringify({'buyer':username, 'itemID':itemID}));
}

// function showMessageOutput(messageOutput) {
//     var response = document.getElementById('response');
//     var p = document.createElement('p');
//     p.style.wordWrap = 'break-word';
//     p.appendChild(document.createTextNode(messageOutput.from + ": " 
//       + messageOutput.text + " (" + messageOutput.time + ")"));
//     response.appendChild(p);
// }