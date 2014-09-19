cd %cd%\src\
set CLASSPATH=.;.\..\miglayout15-swing.jar
javac .\gui\*.java .\word\*.java .\listeners\*java .\main\*.java
java main/Main