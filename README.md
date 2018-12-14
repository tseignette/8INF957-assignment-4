# 8INF957-assignment-4

Equipe : SEIGNETTE Thomas

## Fonctionnement

### Capteurs

Les fichiers de description des capteurs se situent dans le dossier ``sensors`` et doivent respecter la forme des fichiers exemples.

### Filtres

Lorsqu'un nouveau capteur est ajouté, des filtres lui sont ajoutés aléatoirement (3 maximum). 3 filtres ont été implémenté :

* ``Duplication filter`` : il laisse passer la valeur lue seulement si elle est différente de la précédente
* ``Pass-through filter`` : il laisse passer la valeur lue sans la modifier
* ``Transform filter`` : il convertit la valeur lue en "D" ou "N" en fonction de l'état de la place de parking

### Serveur HTTP

Il est possible de faire des requêtes au serveur HTTP (lancé sur le port 8000 par défaut) :

* ``/all`` : retourne l'état de toutes les places de parking
* ``/free`` : retourne les places de parking libres
