--Tages-Insert
insert into journal (DATUM, STUNDEN, TASK, bemerkung) 
values(curdate(), 2,  'pdbui', ''); 

-- jdbc:hsqldb:/Users/rschumm/git/TechnischeExperimente/stundenabrechnung/sql/stundenabrechnung
-- jdbc:hsqldb:C:\LocalData\99_git\TechnischeExperimente\stundenabrechnung\sql/stundenabrechnung


--Tageskontrolle
select datum, stunden, TASK, bemerkung
from JOURNAL where DATUM = curdate()

--mit Datum: 
insert into journal (DATUM, STUNDEN, TASK, bemerkung) 
values('2011-7-22', 8.4, 'unbezahlt', 'mini-sabbatical'); 



select * 
from JOURNAL where DATUM = '2011-7-1'
order by datum


select * from task

shutdown

select *
from JOURNAL
where datum > '2011-6-5'




--> !! Wochen�bersicht alle Tage Stunden
select day(datum), dayname(datum), sum(stunden) from journal 
where year(datum)=year(curdate()) and  week(datum)=week(curdate())
group by DAYOFWEEK(datum)
order by DAYOFWEEK(datum)


select datum, dayname(datum), sum(stunden) from journal 
where datum > '2011-1-2'
group by datum
order by datum



--alle Wochen�bersicht Stunden gesamt
select year(datum) as jahr, week(datum)-1 as woche, sum(stunden) 
from journal 
where year(datum)=year(curdate()) and datum <= curdate()
group by jahr, woche order by jahr desc, woche desc



--Wochen�bersicht alle Tage Taks
select dayname(datum), datum, stunden, task, bemerkung
from journal 
where year(datum)=year(curdate()) and week(datum)=week(curdate())




--fuer Planta: was ist zu buchen
select dayname(datum), datum, sum(stunden),name, plantaname, bemerkung
from journal join task on (journal.task = task.name)
where verbuchbar = true and plantaverbucht = false
group by datum, name, bemerkung, plantaname order by datum


where datum >= '2011-3-1' and datum <= '2011-3-21'

--plantaverbucht updaten. 
update JOURNAL
set PLANTAVERBUCHT = true
where PLANTAVERBUCHT = false 


and datum < '2011-3-13' and task = 'claims'
and task != 'gwt' and task != 'seam'


--Aggregation f�r ein Projekt
select sum(stunden) as h, sum(stunden)/8.4 as d
from journal
where task = 'drive2011' and PLANTAverbucht = false

where task = 'review' and datum > '2011-1-1'



--fuer Planta: Nachbuchungen. 
select sum(stunden),name, plantaname
from journal join task on (journal.task = task.name)
where verbuchbar = true and plantaverbucht = false
and year(DATUM) = 2011 and month (datum) = month(curdate())-1 
group by name, PLANTANAME 







 
--aktuelle Monats�bersicht (nicht) verbuchbar total
select verbuchbar, sum(stunden) as stunden, sum(stunden)/8.4 as tage
from journal join task on (journal.task = task.name)
where month(datum)=month(curdate())-1   and year(datum) = year(curdate())
group by VERBUCHBAR; 

-- x Monats�bersicht (nicht) verbuchbar (sap/planta/etc)
select BUCHART, sum(stunden) as stunden, sum(stunden)/8.4 as tage
from journal join task on (journal.task = task.name)
where month(datum)=6  and year(datum) = 2011
group by BUCHART; 


select * from task;


--fuer Planta: was habe ich diesen Monat verbucht
select dayname(datum), datum, sum(stunden),name, plantaname
from journal join task on (journal.task = task.name)
where buchart = 'planta' and journal.PLANTAVERBUCHT = 'true'
and month(datum)=month(curdate()) and year(datum) = year(curdate())
group by datum, name, plantaname order by datum desc




-- x Monat: �bersicht, was ist nicht verbuchbar
select sum(stunden) as h, sum(stunden)/8.4 as tage, task
from journal join task on (journal.task = task.name)
where month(datum)=5 and year(datum) = 2011
and VERBUCHBAR = false
group by task
order by h desc

--aktueller Monat: nicht verbuchbare Sachen im Detail pro Task
select year (datum), week(datum) wochennr, dayname(datum), datum, stunden, task, BEMERKUNG
from journal join task on (journal.task = task.name)
where month(datum)=5 and year(datum) = 2011
and VERBUCHBAR = false
order by datum asc




--Wochen�bersicht tasks
select task, sum(stunden)/8.4 as tag, sum(stunden) as summe, (sum(stunden)/42)*100 as prozent
from journal join task on (journal.task = task.name)
where year(datum)=year(curdate()) and week(datum)=week(curdate())-0
group by task
order by summe desc

--m32 Kontroller
select week(datum) as woc