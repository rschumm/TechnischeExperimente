stundenabrechnung
=================

8. 5. 2012

Gerüst der Einführungsaufgabe in JSF2 etc. für unseren neuen Lehrling Sven Tschui. 

Aufgabe
-------

Es soll - basierend auf der mitglieferten H2-Datenbank - ein Gui auf die gute alte Stundenabrechnung
gebaut werden. 
Dabei kommen die Technlogien Java EE 6, JSF2, JPA, Richfaces 4 etc zum Einsatz. 
Detaillierte Angaben im Wiki unter:

https://github.com/rschumm/TechnischeExperimente/wiki/Stundenabrechnung:-ein-JSF2-Experiment

Konfiguration / Installation
----------------------------

Um die Stundenabrechnungn utzen zu können muss irgendwo auf dem Lokalen Rechner ein Verzeichnis mit folgendem Inhalt erstellt werden:

- ./jboss (JBoss AS7)
- ./hsqldb.jar (HSQLD JDBC Treiber)
- ./stundenabrechnung.bat (Startscript aus dem Repository)

Der Treiber bzw. der Jboss AS 7 kann auch in einem anderen Verzeichniss liegen, dafür müsste aber das stundenabrechnung.bat angepasst werden.

Beim ersten Start muss die Webapplikation auf den server deployed werden (Siehe Deployment).

Deployment
----------

Datenbanktreiber
________________
Der Datenbanktreiber hsqldb.jar (Vorhanden in src/main/webapp/WEB-INF) muss manuell ins JBoss Deploy-Verzeichnis kopiert werden. Anschliessend kann die Applikation deployed werden.

Der Datenbanktreiber kann im Eclipse auch als Deployable markiert (Mark as deployable) werden.

Die Webapplikation kann direkt aus Eclipse deployed werden oder als .war Datei ins deployments Verzeichniss des JBoss AS7 kopiert werden.

JSFlot
______

Da es mir noch nicht möglich war, JSFlot als Maven dependency zu integrieren, muss beim clone des Projekts die Datei src/main/webapp/WEB-INF/lib/jsflot-0.7.0.jar in den Buildpath mit aufgenommen werden.


URL
---

die URL der Applikation lautet: 
http://localhost:8080/stundenabrechnung/index.jsf

