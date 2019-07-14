# Projet CPOO - 2018 
# (Clément Caumes - Mehdi Merimi - Mehdi Laouiti)

Manuel Utilisateur
==================

## Menu Rogue Like
* Appuyez sur [N] pour démarrer une nouvelle partie. 
* Appuyez sur [G] pour jouer à la dernière partie sauvegardée.

## Choix de la partie Rogue Like
* Appuyez sur les différentes touches pour sélectionner la difficulté et l'arme.
* Appuyez sur [ENTREE] pour démarrer la partie. 

## Jeu Rogue Like
* Appuyez sur les différentes touches fléchées pour déplacer le personnage Joueur. 
* Appuyez sur [A] pour attaquer les PNJ. 
* Appuyez sur [P] pour discuter/échanger avec le PNJ Villageois. 
* Appuyez sur [R] pour ramasser les armes, les fioles de vie et l'argent. 
* Appuyez sur [O] pour ouvrir la porte (et accéder au niveau suivant). 

## But du jeu
* Le but du jeu est d'aller au niveau le plus haut possible. Pour accéder au niveau suivant, il faut récupérer la clef.
* Cette clef est récupérable auprès du villageoisen échange d'une certaine somme d'argent. 

Manuel Technique
================

## Exécution du projet 

Les commandes pour jouer à Rogue Like : 

- `mvn install:install-file -Dfile=lib/asciiPanel.jar -DgroupId=asciipanel -DartifactId=asciipanel -Dversion=1.0 -Dpackaging=jar`
- `mvn package`
- `java -jar target/rogue_like-1.0-jar-with-dependencies.jar`

Pour générer la javadoc : 

- `mvn site`

## Préambule

Le groupe a suivi le tutoriel du Trystan's blog jusqu'au tutoriel 04 sur le joueur. 
En effet, à partir de ce tuto, nous avions toutes les connaissances pour mener 
à bien ce projet. 

## Packages

### Package world : 

On y retrouve les énumérations Arme, Difficulte, Element correspondant aux informations constituant le monde à créer. 
Les classes Monde et MondeBuilder (issues du tutoriel 03) permettent de créer la carte où pourra se déplacer les PNJ ainsi que 
le joueur. 
Ce sera la classe PlayScreen qui initialisera Monde en appelant des méthodes de MondeBuilder. 

### Package creature : 

On retrouve dans ce package la classe mère abstraite Creature dont les classes
Joueur et PNJ héritent. En effet, que ce soit Joueur ou PNJ, ils ont en commun une position sur la carte, 
un caractère pour être représenté, de la vie, etc...
On a également une énumération EnumPNJ représentant les différents PNJ possibles dans le jeu (ici nous avons 
un sorcier ou un zombie) dont l'attaque (points de dégats) et la défense (nombre de points de vie) n'est pas 
la même. 

### Package sauvegarde : 

Ce package contient les classes Sauvegarde permettant de sauvegarder la partie en cours de jeu (carte sur lequel le joueur joue, 
les paramètres choisis par l'utilisateur, le 'stuff' du joueur, les informations sur les PNJ présents sur la map, etc...
Cette classe va donc écrire des types primitifs dans le fichier save/save.txt pour représenter la partie sauvegardée. 
La classe Chargement sera, quand à elle, capable d'interpréter le fichier save/save.txt pour réafficher la partie sauvegardée, 
et ainsi, permettre au joueur de continuer à joueur sur sa partie. 

### Package exception : 

Ce package est dans le seul but de lancer des exceptions en cas de problème lors du jeu Rogue Like. 

* Si un villageois attaque un joueur : VillageoisAgressifException
* Si un spawn de personnage (PNJ ou joueur) est invalide : SpawnException
* S'il n'y a pas de Villageois sur la map, le joueur ne peut pas avoir la clef : VillageoisException
* S'il y a un problème lors de la sauvegarde de la partie : SauvegardeException
* S'il y a un problème lors du chargement de la partie sauvegardée : ChargementException
* S'il n'y a pas assez d'argent sur la carte de jeu, le joueur ne pourra pas forcément faire l'échange avec le villageois : ArgentException 
* S'il n'y a pas d'arme sur la carte de jeu, le joueur sera handicapé : ArmeException
* S'il n'y a pas de porte sur la carte de jeu, le joueur ne pourra pas accéder au niveau suivant : PorteException

### Package screen :

On retrouve les différentes interfaces permettant d'imprimer 
à l'écran les différents affichages : menu de démarrage (StartScreen), menu de paramètres (ChoiceScreen), 
écran du jeu (PlayScreen) et l'écran Game Over (LoseScreen) qui implémentent tous la même interface Screen. 
Sur PlayScreen, on va créer un nouveau Monde qu'on initialisera aléatoirement avec une liste de PNJ agressifs (en fonction 
de la difficulté) et un villageois (pour faire un échange contre la clef). 
Lors de l'appel de la partie sauvegardée, Playscreen créera la carte, le joueur et les PNJ en fonction de ce que 
Chargement va lire dans save/save.txt

## Améliorations à apporter 

* Sachant que le groupe n'a pas voulu plagier le tutoriel ainsi que du code sur Internet, 
nous voulions faire notre propre génération de carte. C'est pour cela que nous avons choisi de créer une 
grande salle, avec quelques murs placés aléatoirement, dans laquelle tous les PNJ se trouvaient avec le joueur. 
Comme amélioration, nous aurions pu apporter une génération de salles complexes. 

* Comme amélioration, nous aurions pu ajouter d'autres armes et d'autres types de PNJ. 

## Difficultés rencontrées 

* Lors de la sauvegarde, nous avions des difficultés car le chargement n'arrivait pas à interpréter tous nos caractères. 
Pour remédier à ce problème, nous avons choisi d'écrire et de lire des types primitifs avec les classes 
DataOutputStream et DataInputStream. 







