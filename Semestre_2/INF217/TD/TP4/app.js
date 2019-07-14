const express = require('express');
const bodyP = require('body-parser');
const cookieP = require('cookie-parser');
   
const app = express();
app.use(bodyP.urlencoded({ extended: false }))
app.use(cookieP());


app.get('/', function(req, res) {
    res.redirect('/signin');
});

app.get('/signin', function(req, res) {
    res.sendFile(__dirname + '/public/form.html');
});

app.get('/cookie', function(req, res){
  
  let c=0;
  if ('visit' in req.cookies) {
    c=parseInt(req.cookies.visit)+1;
  }
  else {
    c=1;
  }
  res.cookie('visit', c);
  res.render('cookie.html', { 'visit' : c});
});

function listColor(col) {
  if (col==='red') return ['cherry', 'strawberry', 'blood'];
  else return ['sun', 'lemon', 'banana'];
}

app.post('/hello', function(req, res) {
  res.render('hello.html', { 'name' : req.body.name, 
                             'color' : req.body.color,
                             'list' : listColor(req.body.color)});
});

app.post('/bye', function(req, res) {
  res.render('bye.html', { 'namebye' : req.body.namebye});
});

const consolidate = require('consolidate');
app.engine('html', consolidate.nunjucks);
app.set('view engine', 'nunjucks');


app.listen(process.env.PORT);

