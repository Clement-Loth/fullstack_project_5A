# fullstack_project_5A

L'objectif de ce projet est de concevoir un site permettant à un utilisateur de prendre un rendez-vous auprès d'un docteur dans un centre laissé au choix de l'utilisateur. Afin de prendre ce rendez-vous, l'utilisateur devra préciser son nom, prénom, numéros de téléphone et adresse mail afin d'être recontacté.

Le projet est structuré autour de trois entités : Les centres, les utilisateurs se partageant les rôles de docteurs, administrateur de centre et super administrateur et enfin les rendez-vous.

Les docteurs sont capables de consulter et modifier les rendez-vous leur étant associés, les administrateurs de centre peuvent modifier les utilisateurs associés à leur centre, les supers administrateurs peuvent créer et désactiver des centres ou d'autres utilisateurs.

La gestion des accès est en deux temps: Une séparation du côté public et admin, le côté admin nécessitant une authentification afin d'accéder aux requêtes admin. Le deuxième aspect de la gestion d'accès est lié aux rôles des utilisateurs : 
- Les superadministrateurs ont accès à toutes les requêtes tandis que les docteurs et les administrateurs n'ont pas accès aux méthodes POST.
- Un docteur ne peux utiliser un GET sur les rendez-vous seulement si ces rendez-vous lui sont associés.
- Un administrateur peut accéder aux méthodes PUT et DELETE pour d'autres utilisateurs seulement si ces utilisateur sont liés au même centre que lui.

Lorsqu'un objet est désactivé, il n'est pas supprimé de la base de donnée, une propriété disabled passe à vrai et l'objet n'apparaît plus lors de requête.

Un systeme de métrique permet de compter le nombre de rendez-vous prit ainsi que le temps écoulé lors de la prise de rendez-vous. Seul un super administrateur peux acceder à ces métriques.

A chaque requête, le serveur renvoit un Etag.

