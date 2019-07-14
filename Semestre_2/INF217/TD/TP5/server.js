// server.js
// where your node app starts

// init project
var express = require('express');
var bodyParser = require('body-parser');
var asyncError = require('express-async-errors');
var consolidate = require('consolidate');
var expressSession = require('express-session');
var nunjucks = require('nunjucks');
var app = express();
app.use(bodyParser.urlencoded({ extended: true }));

app.engine('html', consolidate.nunjucks);
app.set('view engine', 'nunjucks');

var knex = require('knex')({
    client: 'sqlite3',
    connection: {
        filename: ".data/db.sqlite3"
    },
    debug: true,
});

var session = require('express-session');

app.use(session({
    secret: '12345',
    resave: false,
    saveUninitialized: false,
}));



// we've started you off with Express, 
// but feel free to use whatever libs or frameworks you'd like through `package.json`.

// http://expressjs.com/en/starter/static-files.html
app.use(express.static('public'));

// init sqlite db
var fs = require('fs');
var dbFile = './.data/sqlite.db';
var exists = fs.existsSync(dbFile);
var sqlite3 = require('sqlite3').verbose();
var db = new sqlite3.Database(dbFile);

async function printTableContent(){
  try{
    let rows = await knex('users');
    //let str="";
    let tab = []; 
    let i=0;
    for (var r of rows) {
      tab[i]=r.login; i++;
      tab[i]=r.password; i++;
      tab[i]=r.name; i++;
      tab[i]=r.color1; i++;
      tab[i]=r.color2; i++;
    }
    return tab;
  }
  catch(err){
     console.error('Erreur dans l\'affichage des éléments de la table');  
  }
}

async function testValueExist(login, password){
  try {
    var rows = await knex.raw('SELECT * FROM users WHERE login=\"'+login+ '\" AND password=\"'+password+'\"');
    var test=false;
    for (var r of rows) {
      test=true;
    }
    return test;
  } catch (err) {
    console.log("Impossible de tester l'existence de ce tuple.");
  }
}


async function insertTableValue(login, password, name, color1, color2){
  try{
    await knex('users')
              .insert(
                  [{'login':login, 'password':password, 'name':name, 'color1':color1, 'color2':color2}]
              );
  }
  catch(err){
    console.error('Erreur dans l\'insertion d\' élément de la table');  
  }
}



// if ./.data/sqlite.db does not exist, create it, otherwise print records to console
db.serialize(function(){
  if (!exists) {
    db.run('CREATE TABLE Dreams (dream TEXT)');
    console.log('New table Dreams created!');
    
    // insert default dreams
    db.serialize(function() {
      db.run('INSERT INTO Dreams (dream) VALUES ("Find and count some sheep"), ("Climb a really tall mountain"), ("Wash the dishes")');
    });
  }
  else {
    console.log('Database "Dreams" ready to go!');
    db.each('SELECT * from Dreams', function(err, row) {
      if ( row ) {
        console.log('record:', row);
      }
    });
  }
});


// http://expressjs.com/en/starter/basic-routing.html
app.get('/signin', function(request, response) {
  response.sendFile(__dirname + '/views/signin.html');
});

app.post('/signin', async function(request, response) {
  await insertTableValue(request.body.login, request.body.password, request.body.name, request.body.color1, request.body.color2); 
  response.redirect('/');
});

app.get('/login', function(request, response) {
  response.sendFile(__dirname + '/views/login.html');
});

app.post('/login', async function(request, response) {
  if (await testValueExist(request.body.login, request.body.password)==true){
    request.session.login = request.body.login;
    request.session.password = request.body.password;
    response.redirect('/userlist');
  }
  else {
    response.redirect('/error');
  }
});

app.get('/', function(request, response) {
  
  if (request.session.login) {                  // Retrieve data from the session
    response.redirect('/userlist');
  } else {
    response.redirect('/login');             // If data is absent, redirect to /welcome
  }
});

app.get('/error', function(request, response) {
  response.sendFile(__dirname + '/views/error.html');
});


// http://expressjs.com/en/starter/basic-routing.html
app.get('/logout', function(request, response) {
  request.session.login=null;
  request.session.password=null;
  response.sendFile(__dirname + '/views/logout.html');
});

// http://expressjs.com/en/starter/basic-routing.html
app.get('/userlist', async function(request, response) {
  if (request.session.login) {                  // Retrieve data from the session
    let liste = await printTableContent(); 
    response.render('userlist.html', { 'list' : liste});
  } else {
    response.redirect('/');             // If data is absent, redirect to /welcome
  } 
});


// listen for requests :)
var listener = app.listen(process.env.PORT, function() {
  console.log('Your app is listening on port ' + listener.address().port);
});

