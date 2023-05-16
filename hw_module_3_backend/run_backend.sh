#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
cd target
java -jar hw_module_3_backend.jar
read -p "Press [Enter] to close this window"
