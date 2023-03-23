#!/bin/sh

echo 'run level 1'

echo 'run simple'
cd ./level_1/simple
javac HiMark.java
java HiMark

cd ../

echo 'run package'
cd ./package
javac ua/com/alevel/HelloBilly.java
java ua.com.alevel.HelloBilly

cd ../

echo 'run separate packages'
cd ./separate_packages
javac ua/com/alevel/HelloMentor.java
java ua.com.alevel.HelloMentor

cd ../

echo 'run minimal proj'
cd ./minimal_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/HelloAgain.java
java -cp build/classes ua.com.alevel.HelloAgain

cd ../

echo 'run med proj and create simple jar'
cd ./med_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/HelloMaster.java
jar cvfm build/jar/hello.jar resources/MASTERMANIFEST.MF -C build/classes .
java -jar build/jar/hellomaster.jar

cd ../../

echo 'run level 2 (include libs)'

cd ./level_2

echo 'run simple proj who contains external library (jar)'
cd ./include_libs
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-codec-1.15.jar src/ua/com/alevel/util/MessageTask.java src/ua/com/alevel/HelloTask.java
java -cp build/classes/:./lib/commons-codec-1.15.jar ua.com.alevel.HelloTask

cd ../

echo 'run create jar who contains external library (jar)'
cd ./jar
. ./run_jar.sh

cd ../../

. ./remove-class.sh
