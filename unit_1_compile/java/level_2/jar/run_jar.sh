#!/bin/sh

echo 'variant 1'

javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-codec-1.15.jar src/ua/com/alevel/util/MessageTask.java src/ua/com/alevel/HelloTask.java
cd ./lib
jar xf commons-codec-1.15.jar
cp -rf com ../build/classes
cd ../
jar cvfm build/jar/hellotask.jar resources/TASKMANIFEST.MF -C build/classes .
jar tf build/jar/hellotask.jar
java -jar build/jar/hellotask.jar

echo 'variant 2'
rm -rf lib/com
rm -rf lib/META-INF
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-codec-1.15.jar src/ua/com/alevel/util/MessageTask.java src/ua/com/alevel/HelloTask.java
cp -r lib/*.jar build/jar
jar cvfm build/jar/hellotask.jar resources/TASKMANIFEST.MF -C build/classes .
java -jar build/jar/hellotask.jar
