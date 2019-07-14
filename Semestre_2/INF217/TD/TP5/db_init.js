var knex = require('knex')({
    client: 'sqlite3',
    connection: {
        filename: ".data/db.sqlite3"
    },
    debug: true,
});

async function createTable() {  
  try{
    await knex.raw(`CREATE TABLE users (
    login varchar(255) PRIMARY KEY DEFAULT 'Heisenberk',
    password varchar(255) NOT NULL DEFAULT 'password',
    name varchar(255) DEFAULT 'Caumes',
    color1 varchar(10) DEFAULT 'red',
    color2 varchar(10) DEFAULT 'blue'
  )`);
  //await knex.destroy();
  }
  catch(err){
     console.error('Erreur dans la création de la table');  
  }
   
}

async function insertTableValuesDefault(){
  try{
    await knex('users')
              .insert(
                  [{'login':'Heisenberk', 'password':'password1', 'name':'Caumes', 'color1':'red', 'color2':'blue'},
                   {'login':'Toto', 'password':'password2', 'name':'Otot', 'color1':'green', 'color2':'pink'}]
              );
  }
  catch(err){
    console.error('Erreur dans l\'insertion par défaut des éléments de la table');  
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

async function deleteTable() {
   await knex.raw(`DROP TABLE IF EXISTS users`);
   //await knex.destroy();
}

async function printTableContent(){
  try{
    let rows = await knex('users');
    let str;
    for (var r of rows) {
      str="["+r.login+" "+r.password+" "+r.name+" "+r.color1+" "+r.color2+"]";
      console.log(str);
    }
  }
  catch(err){
     console.error('Erreur dans l\'affichage des éléments de la table');  
  }
}

async function main(){
  await deleteTable();
  await createTable();
  await insertTableValuesDefault();
  await printTableContent();
  await knex.destroy();
}

main();






    


