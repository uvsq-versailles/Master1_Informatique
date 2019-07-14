var $ = (s) => document.querySelector(s);

var tableau = Array(6);
var turn = 1;
for (var i = 0 ; i < 6 ; i++) {
    tableau[i] = Array(7);
    for (var j = 0 ; j < 7 ; j++) {
        tableau[i][j] = 0;
    }
}


function set(row, column, player){
  tableau[row][column] = player;
}


function append(parent, balise) {
  return parent.appendChild(document.createElement(balise));
}

function play(column){

  for (var i = 5 ; i >= 0 ; i--) {

       if( tableau[i][column] === 0){
         tableau[i][column] = turn + 1;
         turn = (turn+1)%2
         return i;
       }

  }
  return false;
}


function render(){
let div = $('#plat');
div.innerHTML='';
let tab = append(div,'table')

  for (var i = 0 ; i < 6 ; i++) {
    let tr = append(tab,'tr')

    for (var j = 0 ; j < 7 ; j++) {
        let td = append(tr,'td')
        if(tableau[i][j] === 1){
          td.className = 'player1'
          }
        else if(tableau[i][j] === 2){
          td.className = 'player2'

        }
      td.dataset.column = j
    }
  }
}

document.querySelector('#plat').addEventListener('click',function(e) {
    let col = parseInt(event.target.dataset.column)
  play(col); render();
});

render();