#/bin/bash !

cd ./src/

CLASSPATH=.:./../miglayout15-swing.jar 
export CLASSPATH

javac ./gui/*.java ./word/*.java ./listeners/*.java ./main/*.java
java main/Main 