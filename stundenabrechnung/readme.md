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

<datasource jndi-name="java:jboss/datasources/stundenabrechnung" pool-name="stundenabrechnung" enabled="true" use-java-context="true">
    <connection-url>jdbc:h2:mem:stundenabrechnung</connection-url>
    <driver>h2</driver>
    <security>
        <user-name>sa</user-name>
    </security>
</datasource>