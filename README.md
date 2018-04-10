# ProjetJee
Projet JEE 

**Configuration**
- Serveur : TomCat v8.05
- OpenJDK8
- Config BDD : (user=root / mdp=root)


**Installation**
- Installez le serveur Tomcat (de préférence le serveur tomcat8)
- Paramétrez le serveur tomcat (fichier /etc/tomcat8/tomcat-users.xml) afin de vous donner les droits
- Paramétrez le serveur tomcat (fichier /etc/default/tomcat8) afin de configurer votre version du jdk (variable JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd
- Lancer le service : service tomcat start
- Connectez vous au serveur par l'URI : localhost:8080/manager/html (le serveur devrait vous demander votre mdp que vous avez configuré auparavant)
- Dans l'onglet "Deployer" ajoutez votre .war et cliquez sur "Deployer". Votre projet devrait apparaître dans l'onglet "Applications"
- Cliquez sur "Démarrer" devant le projet

**Lancement**
- Entrez l'URI : localhost:8080/J2EE/signin

**Problèmes**
- Si des erreurs de ForeignKey apparaissent vueillez installer les .deb de mysql à l'adresse : https://dev.mysql.com/downloads/mysql/
- Si la connexion d'un utilisateur échoue alors qu'il existe bien, cela peut provenir de la connexion du serveur à la BDD. Vérifier votre mdp et votre identifiant root. Si cela persiste, nous vous conseillons de lancer le serveur sous eclipse.
