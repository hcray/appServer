@echo off

set "CURRENT_DIR=%cd%"
if exist "%CURRENT_DIR%\appServer.jar" goto okJar
echo not find appClient.jar
:okJar
title appServer
java -jar %CURRENT_DIR%\appServer.jar
echo end