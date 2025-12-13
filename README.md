TP2
EL BARAJ Anwar _ TP Groupe 2 

N° étudiant:e23022918

Pour ce projet,j'ai réussi à implémenter 4 algorithmes et j'ai modifié l'algorithme bfs pour mélanger les arrètes pour rendre le bfs aléatoire.

Le projet est structuré avec un Makefile et une classe principale interactive.
Si vous lancez le main vous aurez un programme interactif. il vous demandera dans la console :

1.De choisir la famille de graphe (grid, complete ,erdos ,lollipop).

2.De choisir l'algorithme à tester (kruskal ,wilson ,aldous ,edgeInsertion ,bfs ).

Si vous utilisez make ,vous pouvez compiler et lancer le projet avec  la commande suivante :
Dans le bash et dans le dossier parent vous tapez la commande : make exec 

Le programme calculera alors les statistiques moyennes sur 10 échantillons et affichera le labyrinthe généré (uniquement pour les grilles).

Algorithmes implémentés:

-Random Kruskal (Poids Aléatoires) 

-Random Wilson (Marche aléatoire à boucle effacée)

-Random Aldous Border (Marche aléatoire simple)

-Random Edge Insertion (Mélange d'arètes)

-Random BFS (Parcours en largeur avec mélange des voisins)



Le programme calculera alors les statistiques moyennes sur 10 échantillons et affichera le labyrinthe généré (uniquement pour les grilles).

J'ai fais une analyse et j'ai lancé les 5 algorithmes avec tous les types de graphes .(Voir fichier 'analyse/analyse.xlsx' ou 'analyse/analyse.pdf' pour les données brutes )


Interprétation :

Les algorithmes de Kruskal,Wilson ,Aldous-Border et EdgeInsertion produisent des résultats similaires.

-Ils génèrent des arbres avec un grand diamètre (entre 800 et 1000)  

-Ils possèdent un grand nombre de feuilles (environ 30% des sommets)

-Visuellement, cela ressemble à des structures "labyrinthesques" très tortueuses qui remplissent l'espace de manière complexe.

-Ces résultats confirme que ces quatres algorithmes génèrent des arbres couvrants selon une distribution uniforme.


Le cas particulier du Random BFS:Après mélange des voisins,l'algorithme produit des arbres très diffèrents:

-Le diamètre est réduit à moitié (440) .

-L'indice de Wiener (Somme des distances) est également deux fois plus petit .

-Le fait le plus marquant est le très faible nombre de feuilles (195 contre 5000 pour les autres).

-Cela indique que le BFS produit des arbres formés de très longues chemins peu ramifiés partant de la racine vers l'extérieur. Ce n'est pas un générateur uniforme.


Coté performance:

-EdgeInsertion ,Kruskal et Wilson sont très rapides 

Interprétation Lollipop :
Performance critique :
-Kruskal / Edge Insertion sont immédiats (~30ms).
-Aldous-Broder est extrêmement lent (24 secondes !), car la marche aléatoire reste "piégée" dans la clique.
-Wilson souffre du même problème mais moins sévèrement (~12s).
-Le Random BFS s'en sort bien mieux (531ms), n'étant pas basé sur une marche aléatoire mais sur une exploration systématique.
Structure : Le BFS confirme son biais en produisant l'arbre le plus court (Diamètre 177) et le plus feuillu (170 feuilles), optimisant les chemins vers la tige.




