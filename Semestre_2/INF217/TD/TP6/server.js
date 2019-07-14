var http = require('http');
var ws = require('ws');
var express = require('express');
var app = express();



var bodyParser = require('body-parser');
var asyncError = require('express-async-errors');
var consolidate = require('consolidate');
var expressSession = require('express-session');
var nunjucks = require('nunjucks');
app.use(bodyParser.urlencoded({ extended: true }));

app.engine('html', consolidate.nunjucks);
app.set('view engine', 'nunjucks');

var session = require('express-session');
app.use(express.static('public'));



var connected_users = {};

class User {
  constructor(name) {
    this.name = name;
  }
  
  setName(name) {
   this.name=name; 
  }
}

// We attach express and ws to the same HTTP server
var server = http.createServer(app);
var wsserver = new ws.Server({ 
    server: server,
});


wsserver.on('connection', function(wsconn) {
  
function update(){
    // Broadcast to everyone else.
    wsserver.clients.forEach(function (client) {
      if (/*client !== wsconn && */client.readyState === ws.OPEN) {
        client.send(JSON.stringify(connected_users));
      }
    }); 
  }
  
  var myuser = null;
   console.log('Received new WS connection');
  
   wsconn.on('message', function(data) {
    
    var receive=JSON.parse(data);
     
     if (receive['type']== 'new_connection'){
       myuser = new User(receive['username']);
       console.log(">"+receive['username']+" connecté");
       connected_users[receive['username']] = myuser;
       update();
     }
     console.log(connected_users);
    });
  
  wsconn.on('close', function(data) {
    if (myuser!=null) delete connected_users[myuser.name];
    console.log(">"+myuser.name+ " déconnecté");  
    update();
  });
});


app.get('/', function(request, response) {
  response.sendFile(__dirname + '/views/index.html');
  
});

app.get('/userlist', function(request, response) {
  var tab=Object.values(connected_users);
  response.render('userlist.html', { 'liste' : tab});
});

server.listen(process.env.PORT);


