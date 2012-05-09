--Tages-Insert
insert into journal (DATUM, STUNDEN, TASK, bemerkung) 
values(curdate(), 4.2,  '90%', ''); 


--Tageskontrolle
select datum, stunden, TASK, bemerkung
from JOURNAL where DATUM = curdate()

--mit Datum: 
insert into journal (DATUM, STUNDEN, TASK, bemerkung) 
values('2012-5-4', 2, 'stiften', ''); 

--fur Korrekturen:
select * 
from JOURNAL where DATUM >= '2012-4-23'
order by datum asc


select * from task 

where name = 'broker' 

shutdown

--------------------------------------------------------------------------------

-- jdbc:hsqldb:/Users/rschumm/git/TechnischeExperimente/stundenabrechnung/sql/stundenabrechnung




--------------------------------------------------------------------------------

-- Alle Jahreswochen Stunden gesamt
select year(datum) as jahr, week(datum) as woche, sum(stunden) 
from journal 
where year(datum)=year(curdate()) and datum <= curdate()
group by jahr, woche order by jahr desc, woche desc




--> !! Wochen�bersicht alle Tage Stunden
select day(datum), dayname(datum), sum(stunden) from journal 
where year(datum)=year(curdate()) and  week(datum)=week(curdate())
group by DAYOFWEEK(datum)
order by DAYOFWEEK(datum)

--Wochen�bersicht alle Tage Taks
select dayname(datum), datum, stunden, task, bemerkung
from journal 
where year(datum)=year(curdate()) and week(datum)=week(curdate())



--Wochen�bersicht tasks in Tagen und Prozenten
select task, sum(stunden)/8.4 as tag, sum(stunden) as summe, (sum(stunden)/42)*100 as prozent
from journal join task on (journal.task = task.name)
where year(datum)=year(curdate()) and week(datum)=week(curdate())-0
group by task
order by summe desc



--com2 Kontroller
select month(datum) as monat, sum(stunden) as h, sum(stunden)/8.4 as pt 
from journal
where task like 'broker' and year(datum) = year(curdate())
group by monat order by monat desc


--------------------------------------------------------------------------------





--fuer Planta: was ist zu buchen
select dayname(datum), datum, sum(stunden),name, plantaname, bemerkung
from journal join task on (journal.task = task.name)
where verbuchbar = true and plantaverbucht = false
group by datum, name, bemerkung, plantaname order by name, datum


where datum >= '2011-3-1' and datum <= '2011-3-21'

--plantaverbucht updaten. 
update JOURNAL
set PLANTAVERBUCHT = true
where PLANTAVERBUCHT = false 



and task != 'broker' 



and datum < '2011-3-13' and task = 'claims'
and task != 'gwt' and task != 'seam'


--fuer Planta: Nachbuchungen. 
select month(datum) as monat, sum(stunden),name, plantaname
from journal join task on (journal.task = task.name)
where verbuchbar = true and plantaverbucht = false
and year(DATUM) = 2012 and month(datum) <= month(curdate())
group by monat, name, PLANTANAME
order by monat, name





--------------------------------------------------------------------------------






--Aggregation f�r ein Projekt
select sum(stunden) as h, sum(stunden)/8.4 as d
from journal
where task = 'broker' and plantaverbucht='false'


and datum > '2011-5-1'


where task = 'drive2011' and PLANTAverbucht = false



--------------------------------------------------------------------------------


-- Auswertungen "Lepe-Fragen" 

 
--aktuelle Monats�bersicht (nicht) verbuchbar total
select verbuchbar, sum(stunden) as stunden, sum(stunden)/8.4 as tage
from journal join task on (journal.task = task.name)
where month(datum)=month(curdate())   and year(datum) = year(curdate())
group by VERBUCHBAR; 

-- x Monats�bersicht (nicht) verbuchbar (sap/planta/etc)
select BUCHART, sum(stunden) as stunden, sum(stunden)/8.4 as tage
from journal join task on (journal.task = task.name)
where month(datum)=9  and year(datum) = 2011
group by BUCHART; 



--fuer Planta: was habe ich diesen Monat verbucht
select dayname(datum), datum, sum(stunden),name, plantaname
from journal join task on (journal.task = task.name)
where buchart = 'planta' and journal.PLANTAVERBUCHT = 'true'
and month(datum)=month(curdate()) and year(datum) = year(curdate())
group by datum, name, plantaname order by datum desc




-- x Monat: �bersicht, was ist nicht verbuchbar
select sum(stunden) as h, sum(stunden)/8.4 as tage, task
from journal join task on (journal.task = task.name)
where month(datum)=9 and year(datum) = 2011
and VERBUCHBAR = false
group by task
order by h desc

--aktueller Monat: nicht verbuchbare Sachen im Detail pro Task
select year (datum), week(datum) wochennr, dayname(datum), datum, stunden, task, BEMERKUNG
from journal join task on (journal.task = task.name)
where month(datum)=9 and year(datum) = 2011
and VERBUCHBAR = false
order by datum asc



--------------------------------------------------------------------------------






--m32 Kontroller
select week(datum) as woche, sum(stunden) as h, sum(stunden)/8.4 as pt 
from journal
where task like 'm32%' and year(datum) = year(curdate())
group by woche order by woche desc


-- iqs Wochen�bersicht 
select week(datum) as woche, sum(stunden)/8.4 as pt, sum(stunden) as ph, (sum(stunden)/42)*100 as prozent
from journal join task on (journal.task = task.name)
where year(datum)=year(curdate()) and task = 'iqs'
group by woche
order by woche desc



--hh
select sum(stunden) as h 
from journal
where task = 'hh' and year(datum) = year(curdate())

 
-- 7*8.4h sollten auf m32-refactor gebucht werden. 
select sum(stunden)/8.4 as tag
from journal
where task = 'm32-refactor'

-- Ferien verbraucht und so
select sum(stunden) / 8.4 as verbraucht, (25) - (sum(stunden)/8.4) as uebrig
from journal
where task = 'ferien' and year(datum) = 2011

select sum(stunden) / 8.4 as verbraucht, bemerkung
from journal
where task = 'ferien' and year(datum) = 2011
group by BEMERKUNG


select *
from JOURNAL
where task = 'unbezahlt' and year(datum) = 2011



--90% Kontrolle tempor�r

select DATUM, STUNDEN, TASK
from JOURNAL
where task = '90%temp09'
order by datum

--wie viele nachmittage schon verbraucht. 
select (20 * 4.2) - sum(stunden) as rest_h, sum(stunden) / 4.2 as nachmittage
from journal
where task = '90%temp09'

-- 1. Nov 2009 bis 31. M�rz 2010 sind 20 Nachmittage. 



--Datum korrigieren
update journal
set datum = '2011-5-16' 
where datum = '2011-5-17'

--Task umbenennen
update task
set name = 'claims' 
where name = 'tpsp'



select * from journal where task = 'borkernet'

--Neuer Task
insert into task values('com2','unbekannt', true, 'planta'); 





