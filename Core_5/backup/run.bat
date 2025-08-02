@echo off
echo Compiling Java files...
javac src/*.java
echo.
echo Running the application...
java -cp src Main
echo.
pause 