#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
cd target
java -jar hw_9_file_helper.jar
read -p "Press [Enter] to close this window"

