#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
cd target
java -jar hw_6_collection.jar
read -p "Press [Enter] to close this window"

