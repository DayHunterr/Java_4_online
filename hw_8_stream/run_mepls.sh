#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn clean package
cd target
java -jar hw_8_stream.jar
read -p "Press [Enter] to close this window"

