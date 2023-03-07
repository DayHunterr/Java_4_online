#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
cd target
java -jar hw_module_2.jar
cd ..
cd SourcePath
echo
echo
echo
cat output.json
echo
echo
echo
echo
read -p "Press [Enter] to close this window"

