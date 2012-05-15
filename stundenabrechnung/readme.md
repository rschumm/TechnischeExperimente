stundenabrechnung
=================

8. 5. 2012

Gerüst der Einführungsaufgabe in JSF2 etc. für unseren neuen Lehrling Sven Tschui. 

Aufgabe
-------

Es soll - basierend auf der mitglieferten H2-Datenbank - ein Gui auf die gute alte Stundenabrechnung
gebaut werden. 
Dabei kommen die Technlogien Java EE 6, JSF2, JPA, Richfaces 4 etc zum Einsatz. 
Detaillierte Angaben am Mittwoch in der Einführungsbesprechung. 

Konfiguration
-------------

Die standalone.xml des JBoss Applicationserver muss um folgende Datasource erweitert werden:

<pre>&lt;datasource jndi-name="java:jboss/datasources/stundenabrechnung" pool-name="stundenabrechnung" enabled="true" use-java-context="true"&gt;
    &lt;connection-url&gt;>jdbc:h2:mem:stundenabrechnung&lt;/connection-url&gt;
    &lt;driver&gt;h2&lt;/driver&gt;
    &lt;security&gt;
        &lt;user-name>sa&lt;/user-name&gt;
    &lt;/security&gt;
&lt;/datasource&gt;</pre>


URL
---

die URL der Applikation lautet: 
http://localhost:8080/stundenabrechnung/index.jsf

