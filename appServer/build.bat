rem "%JAVA_HOME%/bin/java" -cp antlib/ant.jar;antlib/ant-nodeps.jar;%JAVA_HOME%/lib/tools.jar org.apache.tools.ant.Main %1
cd "%~dp0"
call ant
pause;