Pure 2048 Workshop
=

Objectif:
===
Créer une version desktop du Jeu 2048,

tout en gérant la création, les interactions, et les erreurs,

via une bonne dose de Programmation Fonctionnelle, et plus précisément, en se servant de la merveilleuse IO Monad.


Consignes:
===
Se renseigner sur les règles du Jeu (merging, scoring, stop (win, lose))
 - Créer une GUI (avec Swing, Termcaps...)
 - Gestion Input (écoute des flèches du clavier, readLine…)
 - Gestion Output (fenêtre, terminal...)
 - Construire toute l’application autour de l’IO Monad, afin de créer un code composable, facilement modifiable, référentiellement transparent, quasi sans effet de bord.
   - Scala:
     - Cats IO
     - ZIO
   - Kotlin:
     - Arrow-Fx IO

Indications:
===
Vous trouvez des exemples d'implementation:
 - Scala
   - La version imperative du 2048
   - Une version pure fonctionnelle, avec Cats IO, d'une application utilisant Swing
 - Kotlin
   - Une version pure fonctionnelle, avec Arrow-Fx IO, d'une application utilisant Swing

Bonus:
===
 - TDD
 - Gestion d’une configuration (avec fichier de conf)
 - Board customisable à froid, via fichier (taille de grille, taille de fenêtre...)
 - Board customisable à chaud (via le Board lui-même)
 - Gestion avancée des erreurs, avec un type plus complexe (Either...)
 - Gestion erreurs métier
 - Gestion erreurs techniques
 - Utiliser un autre mode d’interaction en la view et controller
 - Actor Model
 - Message Queuing
