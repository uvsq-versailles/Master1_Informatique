# PGLP
Programmation GL et Preuve

# Exercices
## Exercice 3.1)
1) Cette classe ne respecte pas SRP parce que calculSalaire() et afficheCoordonnees() sont deux responsabilités différentes.

2) Si la méthode de calcul change, cela impacte toute la classe employé alors que l'affihage des coordonnées n'est pas concerné par le salaire.

3) Idem, cela impacte la classe qui contient le calcul du salaire sans raison.

4) srp/Employe.java

## Exercice 3.2)
Voir package ocp/

## Exercice 3.3)
RobotStatique => Robot

1) Cette solution ne respecte pas LSP car quand on appelle la méthode avance sur tous les types de Robots, on ne peut pas substituer un Robot par un RobotStatique car celui-ci renvoie une UnsupportedOperationException.

2)3) Voir package lsp/

## Exercice 3.4)
1) Si on ne fait pas de ségrégations des interfaces en des composants les plus simples et petits possibles (ISP), on finit pas par ne pas respecter LSP (méthode non implémentée et donc on ne peut 
pas itérer sur les Printers).
	
2) Cela forcera à modifier la signature dans SimplePrinter alors qu'il n'implémente même pas cette méthode.

## Exercice 3.5)
1) Cette classe ne respecte pas DIP car elle ne dépend d'un affichage (Sys.out.print) alors qu'on se trouve dans un haut niveau d'abstraction. On doit remplacer le Sys.out.print par une méthode généraliste de Logger.

2) dip/

