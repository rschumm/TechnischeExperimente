@echo off 

set DATABASE_DIR=./db/stundenabrechnungDB
set JBOSS_BIN_DIR=./jboss/bin
set JBOSS_PORT_OFFSET=1

echo Sarting Database
start /b java -cp hsqldb.jar org.hsqldb.Server -database.0 file:%DATABASE_DIR% -dbname.0 stundenabrechnung

sleep 3

echo Starting JBoss
cd %JBOSS_BIN_DIR%
standalone.bat -Djboss.socket.binding.port-offset=%JBOSS_PORT_OFFSET%