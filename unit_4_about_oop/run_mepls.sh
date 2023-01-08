#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
echo 'Maven ended packaging.....'
echo 'Now app will start working ::)'
sleep 3s
cd target
java -jar hw_4_about_oop.jar
read -p "Press [Enter] to close this window"

