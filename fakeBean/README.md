Kleines Proof-of-Concept zum Recorden und Mocken von Schnittstellen. 
====================================================================

Rémy Schumm, 4. 5. 2012


Die Klasse FakeRecorder kann Testdatenpaar von Suchkriterium und Resultat von einem Realen Service aufnehmen und als XML-Files ablegen. 

Die Klasse FakeUnmarshaller kann diese XML-Daten aufgrund der Suchkriterien wieder rekonstruieren und über ein FakeBean ausliefern. 


Zur Verwendung siehe den UnitTest FakeTest. 



Technische Details: 
Zur Umwandlung von und zu XML wird JAXB verwendet. 
Zur Identifikation der Suchkriterien wird ein md5-Hash über die toString() Methode des Suchkriteriums gemacht. 

Das Root-Element der Resultatklasse muss mit @XmlRootElement annotiert sein. 
Alle beiteiligten Klassen müssen Serializable sein. 