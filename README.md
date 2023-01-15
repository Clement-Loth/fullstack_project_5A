
# Fullstack Projet 5A

BERNABE Jimmy 31708438
JACQUE Axel 31814730
LOTH Clément

L'objectif de ce projet est de concevoir un site permettant à un utilisateur de prendre un rendez-vous auprès d'un docteur dans un centre laissé au choix de l'utilisateur. Afin de prendre ce rendez-vous, l'utilisateur devra préciser son nom, prénom, numéros de téléphone et adresse mail afin d'être recontacté.

# Mise en production
Pour démarrer le projet, il suffit de cloner le répertoire de ce git:
```git clone https://github.com/Clement-Loth/fullstack_project_5A.git```
Ensuite, à la racine du projet, on créer l'image Docker du front:
```cd fullstack_project_5A```
```docker build -t front/covid -f frontend/covid-api/Dockerfile .```
Puis, on la run, en utilisant le port 4200 car il est hardcoder pour le CORS *(TO DO: le passer en variable d'environnement)*:
```docker run -p 4200:80 -d front/covid:latest```
Enfin, on va dans le dossier du back pour le docker compose :
```cd covid-api```
```docker compose up```
Et voilà, tout est lancé. On peut alors se connecter sur http://localhost:8080 pour accéder au back, et à http://localhost:4200 pour le front.
# Back-end

Le projet est structuré autour de trois entités : les centres, les utilisateurs se partageant les rôles de docteurs, administrateur de centre et super administrateur et enfin les rendez-vous.

Un docteur est capable de consulter et modifier les rendez-vous lui étant associés, un administrateur de centre peut modifier les utilisateurs associés à son centre, un super administrateur peut créer et désactiver des centres ou d'autres utilisateurs.

La gestion des accès est en deux temps : une séparation du côté public et admin, le côté admin nécessitant une authentification afin d'accéder aux requêtes admin. Le deuxième aspect de la gestion d'accès est lié aux rôles des utilisateurs :

- Les superadministrateurs ont accès à toutes les requêtes tandis que les docteurs et les administrateurs n'ont pas accès aux méthodes POST.

- Un docteur ne peut utiliser un GET sur les rendez-vous seulement si ces rendez-vous lui sont associés.

- Un administrateur peut accéder aux méthodes PUT et DELETE pour d'autres utilisateurs seulement si ces utilisateurs sont liés au même centre que lui.

Lorsqu'un objet est désactivé, il n'est pas supprimé de la base de données, une propriété disabled passe à vrai et l'objet n'apparaît plus lors de requête.

Un système de métrique permet de compter le nombre de rendez-vous pris ainsi que le temps écoulé lors de la prise de rendez-vous. Seul un super administrateur peut accéder à ces métriques. Son end point est dans springSecurity configuration.

À chaque requête, le serveur renvoit un Etag.
