#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
echo 'Maven ended packaging.....'
echo 'Now app will start working :)'
sleep 3s
cd target
java -jar Levchenko_unit_3_start_oop.jar
echo 'Scroll up and look how is this app working step by step.'
read -p "Press [Enter] to close this window"

