var ws;

function connect() {
    var username = document.getElementById("username").value;
    
    var host = document.location.host;
    var pathname = document.location.pathname;

    var path_arr = pathname.split("/")

    var path = path_arr[1]
    
    ws = new WebSocket("ws://" + host + "/" + path + "/push/" + username);

    ws.onmessage = function(event) {
    //var log = document.getElementById("log");
        // console.log(event.data);
        var message = JSON.parse(event.data);
        // log.innerHTML += message.from + " : " + message.content + "\n";

        alert(message.msg);
    };
}

function send() {
    // var content = document.getElementById("msg").value;
    // var json = JSON.stringify({
    //     "content":content
    // });

    // ws.send(json);

    var itemID = document.getElementById("itemID").value;
    var username = document.getElementById("username").value;

    var xhttp = new XMLHttpRequest();

    xhttp.open("PUT", "http://localhost:8080/marketplace-service/api/items/" + username + "/sell/" + itemID, true)
    xhttp.send();
}