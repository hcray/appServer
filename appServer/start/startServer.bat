@echo off

set "CURRENT_DIR=%cd%"
if exist "%CURRENT_DIR%\appServer.jar" goto okJar
echo not find appClient.jar
:okJar
title appServer
set JAVA_OPTS = -Xms256m -Xmx512m -XX:PermSize=128M -XX:MaxPermSize=256M 
java %JAVA_OPTS% -jar %CURRENT_DIR%\appServer.jar
echo end