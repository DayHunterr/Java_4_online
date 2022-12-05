#!/bin/sh

echo 'run level 1'

echo 'run simple'
cd ./level_1/simple
javac Hello.java
java Hello

cd ../

echo 'run package'
cd ./package
javac ua/com/alevel/Hello.java
java ua.com.alevel.Hello

cd ../

echo 'run separate packages'
cd ./separate_packages
javac ua/com/alevel/Hello.java
java ua.com.alevel.Hello

cd ../

echo 'run minimal proj'
cd ./minimal_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Hello.java
java -cp build/classes ua.com.alevel.Hello

cd ../

echo 'run med proj and create simple jar'
cd ./med_proj
javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/Hello.java
jar cvfm build/jar/hello.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/hello.jar

cd ../../

echo 'run level 2 (include libs)'

cd ./level_2

echo 'run simple proj who contains external library (jar)'
cd ./include_libs
javac -sourcepath ./src -d build/classes/ -cp ./lib/JColor-5.5.1.jar src/ua/com/alevel/util/Message.java src/ua/com/alevel/Hello.java
java -cp build/classes/:./lib/JColor-5.5.1.jar ua.com.alevel.Hello

cd ../

echo 'run create jar who contains external library (jar)'
cd ./jar
. ./run_jar.sh

cd ../../

. ./remove-class.sh
