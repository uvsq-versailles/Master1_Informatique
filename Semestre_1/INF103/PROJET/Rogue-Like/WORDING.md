# Projet CPOO - 2018
Durant ce projet, vous allez développer un jeu d'exploration de donjon ([Rogue-like](https://fr.wikipedia.org/wiki/Rogue-like)).
Le thème que vous utiliserez pour ce projet est libre.
Vous devrez cependant respecter les contraintes ci-dessous.

## Description du jeu
* L'univers du jeu est représenté par un ensemble de cases carrées représentant différents éléments (*sol, mur, objet, personnage; ...*).
* L'univers du jeu est généré aléatoirement en début de partie.
* Le jeu permet de faire évoluer un personnage joueur (PJ) en mode tour par tour.
* Le jeu comporte également des personnages non joueur (PNJ).
* Le PJ possède des caractéristiques (*points de vie, de magie, ...*) et un équipement (*monnaie, armes, ...*).
* Le PJ peut interagir avec son environnement (*ramasser ou utiliser un objet, discuter avec un PNJ, combattre un monstre*).
* Les PNJ obéissent au mêmes règles que le PJ mais sont contrôlés par le jeu.

## Description de l'application à réaliser
* L'interface proposera une visualisation de l'univers en mode texte (une case est représentée par un symbole ASCII).
* Les actions du joueur seront saisies au clavier sous la forme *action objet* (par exemple ``ramasser clé``).
* Une interface spécifique pourra être proposée pour des situations particulières (*création du personnage, sélection d'un sort, discussion, ...*).
* L'état de la partie devra pouvoir être sauvegardée et rechargée à tout moment.

## Contraintes techniques
* Le projet est à réaliser en Java 8 ou ultérieur par **groupe de 2 ou 3 étudiants**.
* Le projet sera à présenter le **jeudi 20 décembre** durant les TDs.
* Le suivi des versions est à réaliser avec ``git``/``bitbucket``.
  * les contributions de chaque membre du groupe devront être clairement visibles dans les *commits*.
* Il devra comporter des tests unitaires (*JUnit 4*) pour une partie significative du projet.
* Il devra être compilable avec ``maven``.
* Il devra être exécutable en tapant ``java -jar mon_projet.jar``.
* Il devra comporter une documentation sous la forme d'un fichier ``README.md``.
  * La documentation devra décrire l'usage de l'application (*manuel utilisateur*) ainsi que la conception du jeu (*manuel technique*).

## Références
* Article Wikipedia [Rogue-like](https://fr.wikipedia.org/wiki/Rogue-like)
* Le tutoriel [roguelike tutorial](http://trystans.blogspot.fr/2016/01/roguelike-tutorial-00-table-of-contents.html)
* La bibliothèque [JAnsi](http://fusesource.github.io/jansi/) pour gérer la couleur dans un terminal 
