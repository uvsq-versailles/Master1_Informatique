var ws = new WebSocket('wss://' + location.host);

  var $ = document.querySelector.bind(document);

ws.addEventListener('open', function(e) {
  if (sessionStorage['username'] === undefined){
  let sign = prompt("What is your user name?");
  sessionStorage['username']= sign;
  }


  $('#nom').textContent = sessionStorage.getItem('username');
  
  ws.send(JSON.stringify({ type: 'new_connection', username: sessionStorage.getItem('username') }));
});

ws.addEventListener('open', function(e) {
  // When we receive a message from the server, 
  // we show it
  ws.addEventListener('message', function(e) {
    var receive=JSON.parse(e.data);
    let str="";
    //$('#users').textContent = sessionStorage.getItem('username');
    console.log(receive);
    for(let i in receive ){
       str+=receive[i].name+" "; 
    }
    console.log(str);
    $('#users').textContent = str;
    
  });

});




/*if (sessionStorage['username'] === undefined){
  let sign = prompt("What is your user name?");
  sessionStorage['username']= sign;
}

  var $ = document.querySelector.bind(document);
  $('#nom').textContent = sessionStorage.getItem('username');

window.addEventListener("beforeunload", function (event) {
    navigator.sendBeacon("/disconnect", sessionStorage.getItem('username'));
});

  var xhr = new XMLHttpRequest();
  xhr.open("GET", "/connect/"+sessionStorage.getItem('username'));
  xhr.onload = function(event) {
    console.log('Connexion Success');
  }
  xhr.send();*/
