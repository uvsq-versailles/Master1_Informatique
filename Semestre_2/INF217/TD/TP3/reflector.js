/****** Configuration *******/

// On charge le framework Express...
var express = require('express');
// On crée l'application web
var app = express();
// On configure Express pour servir les fichiers contenus dans static/
// à l'url /s
app.use('/s', express.static('static'));

// pour la requete post
app.use(require('body-parser').urlencoded({ extended: false }));

// pour les cookies
const cookieP = require('cookie-parser');
app.use(cookieP());




/****** Routes *******/

// On définit une route pour l'url /
app.get('/', function(req, res) {
    /*let string1 = "";
    let jours = { 'mon' : 'Lundi',
              'tue' : 'Mardi',
              'wed' : 'Mercredi',
              'thu' : 'Jeudi',
              'fri' : 'Vendredi',
              'sat' : 'Samedi',
              'sun' : 'Dimanche' };

    for (var property1 in jours) {
      string1 += jours[property1]+'<br>';
    }
    res.send(string1);*/
  res.sendFile('/app/static/form.html');
});

// /query_string_get?user=toto&pwd=12345
app.get('/query_string_get', function(req, res) {
  let string2 ="";
  let x;
  /*for(x in req._parsedUrl.query){
    string2+= x +" = "+ req._parsedUrl.query[x]+'<br>';
  }*/
  for(x in req.query){
    string2+= x +" = "+ req.query[x]+'<br>';
  }
  
  res.send(string2);
});

app.post('/query_string_post', function(req, res) {
  let string2 ="";
  let x;
  for(x in req.body){
    string2+= x +" = "+ req.body[x]+'<br>';
  }
  res.send(string2);
  
});

app.all('/cookie', function(req, res) {
  //let string3 =req.headers+'<br>'+req.cookies;
  res.send(req.headers);
  //res.send(req.cookies);
  
});



/****** *******/

// On lance l'application
// (process.env.PORT est un paramètre fourni par Glitch)
app.listen(process.env.PORT);
