#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
cd target
java -jar hw_13_crud_hibernate.jar
read -p "Press [Enter] to close this window"

